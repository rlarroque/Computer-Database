package com.excilys.computer_database.dao.impl;

import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.model.Page;
import com.excilys.computer_database.model.utils.OrderColumn;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
@SuppressWarnings("unchecked")
public class ComputerDAOImpl implements ComputerDAO {
        
    @Autowired
    private SessionFactory sessionFactory;

    private static final String GET_ALL_QUERY = "select computer from computer computer left join computer.company as company";

    @Override
    public List<Computer> getAll() {

        return sessionFactory.getCurrentSession()
                             .createQuery(GET_ALL_QUERY)
                             .list();
    }

    private static final String GET_BY_PAGE_QUERY = "select computer from computer computer left join computer.company as company";

    @Override
    public List<Computer> getPage(Page page) {
        
        String hqlQuery = GET_BY_PAGE_QUERY;
        
         if (page.getFilter() != null && !"".equals(page.getFilter())) {
            hqlQuery = hqlQuery.concat(" where computer.name like '%")
                               .concat(page.getFilter())
                               .concat("%' or company.name like '%")
                               .concat(page.getFilter())
                               .concat("%'");
        }
        
        if (page.getOrder() != null) {

            if(page.getOrder().getCol() == OrderColumn.COMPANY) {
                hqlQuery = hqlQuery.concat(" order by ISNULL(company.name), company.name")
                                   .concat(" " + page.getOrder().getType().toString());
            } else {
                hqlQuery = hqlQuery.concat(" order by ISNULL(computer."
                                   .concat(page.getOrder().getCol().toString())
                                   .concat("), computer."))
                                   .concat(page.getOrder().getCol().toString())
                                   .concat(" " + page.getOrder().getType().toString());
            }
        }
        
        return (List<Computer>) sessionFactory.getCurrentSession()
                                              .createQuery(hqlQuery)
                                              .setFirstResult(page.getStartIndex())
                                              .setMaxResults(page.getOffset())
                                              .list();
    }

    private static final String GET_BY_ID_QUERY = "select computer from computer computer left join computer.company as company where computer.id= :id";

    @Override
    public Computer get(long id) {

        return (Computer) sessionFactory.getCurrentSession()
                                        .createQuery(GET_BY_ID_QUERY)
                                        .setLong("id", id)
                                        .uniqueResult();
    }

    private static final String GET_BY_NAME_QUERY = "select computer from computer computer left join computer.company as company where computer.name= :name";

    @Override
    public Computer get(String name) {

        return (Computer) sessionFactory.getCurrentSession()
                                        .createQuery(GET_BY_NAME_QUERY)
                                        .setString("name", name)
                                        .uniqueResult();
    }

    @Override
    public long create(Computer computer) {
    	
        return (long) sessionFactory.getCurrentSession()
                                    .save(computer);
    }

    @Override
    public void update(Computer computer) {
        sessionFactory.getCurrentSession().update(computer);
    }


    private static final String DELETE_QUERY = "delete from computer where id= :id";

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession()
                      .createQuery(DELETE_QUERY)
                      .setLong("id", id)
                      .executeUpdate();
    }

    private static final String DELETE_BY_COMPANY_QUERY = "delete from computer where company_id= :id";

    @Override
    public void deleteByCompany(long id) {
        sessionFactory.getCurrentSession()
                      .createQuery(DELETE_BY_COMPANY_QUERY)
                      .setLong("id", id);
    }

    private static final String COUNT_QUERY = "select count(*) from computer computer left join computer.company as company " +
                                              "where computer.name like :computer_name or company.name like :company_name";

    @Override
    public int count(Page page) {
        
        String filter = "%";
        filter = filter.concat(((page.getFilter() == null) ? "" : page.getFilter()) + "%");
        
        Number number = (Number) sessionFactory.getCurrentSession()
                                               .createQuery(COUNT_QUERY)
                                               .setString("computer_name", filter)
                                               .setString("company_name", filter)
                                               .uniqueResult();
        
        return number.intValue();
    }
}
