package com.excilys.computer_database.persistence.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ComputerDAOImpl implements ComputerDAO {
        
    @Autowired
    private SessionFactory sessionFactory; 

    @Override
    public List<Computer> getAll() {

        return sessionFactory.getCurrentSession()
                             .createQuery("from computer as computer left join fetch computer.company as company")
                             .list();
    }

    @Override
    public List<Computer> getPage(Page page) {
        
        String hqlQuery = "select computer from computer computer left join computer.company as company";
        
        /* if (page.getFilter() != null && !"".equals(page.getFilter())) {
            hqlQuery = hqlQuery.concat(" where computer.name like '%")
                               .concat(page.getFilter())
                               .concat("%' or company.name like '%")
                               .concat(page.getFilter())
                               .concat("%'");
        }
        
        if (page.getOrder() != null) {
            hqlQuery = hqlQuery.concat(" order by computer.")
                               .concat(page.getOrder().getCol().toString())
                               .concat(" " + page.getOrder().getType().toString());
        } */
        
        return (List<Computer>) sessionFactory.getCurrentSession()
                             .createQuery(hqlQuery)
                             .setFirstResult(page.getStartIndex())
                             .setMaxResults(page.getOffset())
                             .list();
    }

    @Override
    public Computer get(long id) {

        return (Computer) sessionFactory.getCurrentSession()
                                        .createQuery("from computer as computer left join fetch computer.company as company where computer.id= :id")
                                        .setLong("id", id)
                                        .uniqueResult();
    }

    @Override
    public Computer get(String name) {

        return (Computer) sessionFactory.getCurrentSession()
                                        .createQuery("from computer as computer left join fetch computer.company as company where computer.name= :name")
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

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession()
                      .createQuery("delete from computer where id= :id")
                      .setLong("id", id)
                      .executeUpdate();
    }

    @Override
    public void deleteByCompany(long id) {
        sessionFactory.getCurrentSession()
                      .createQuery("delete from computer where company_id= :id")
                      .setLong("id", id);
    }

    @Override
    public int count(Page page) {
        
        return (int) sessionFactory.getCurrentSession()
                                   .createQuery("select count(*) from computer as computer left join fetch computer.company as company " +
                                                "where computer.name like :computer_name or company.name like :company_name")
                                   .setString("computer_name", "%" + page.getFilter() + "%")
                                   .setString("company_name", "%" + page.getFilter() + "%")
                                   .uniqueResult();
    }
}
