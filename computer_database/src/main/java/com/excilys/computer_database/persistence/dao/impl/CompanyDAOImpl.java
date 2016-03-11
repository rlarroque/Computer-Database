package com.excilys.computer_database.persistence.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computer_database.persistence.dao.CompanyDAO;
import com.excilys.computer_database.persistence.model.Company;

/**
 * Implementation of CompanyDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class CompanyDAOImpl implements CompanyDAO {
    
    @Autowired
    private SessionFactory sessionFactory;    

    @Override
    public List<Company> getAll() {
        
        return sessionFactory.getCurrentSession()
                             .createQuery("from company")
                             .list();
    }

    @Override
    public Company get(long id) {

        return (Company) sessionFactory.getCurrentSession()
                                       .createQuery("from company where id= :id")
                                       .setLong("id", id)
                                       .uniqueResult();
    }

    @Override
    public Company get(String name) {

        return (Company) sessionFactory.getCurrentSession()
                                       .createQuery("from company where name= :name")
                                       .setString("name", name)
                                       .uniqueResult();
    }

    @Override
    public void delete(long id) {
        sessionFactory.getCurrentSession()
                      .createQuery("delete from company where id= :id")
                      .executeUpdate();
    }
}
