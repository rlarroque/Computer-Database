package com.excilys.computer_database.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computer_database.dao.UserDAO;
import com.excilys.computer_database.model.User;

/**
 * Implementation of UserDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO{
	
    @Autowired
    private SessionFactory sessionFactory; 

	@Override
	public List<User> getAll() {
		
		return sessionFactory.getCurrentSession()
                			 .createQuery("from user")
                			 .list();
	}

	@Override
	public User get(String username) {
		return (User) sessionFactory.getCurrentSession()
                					.createQuery("from user where username= :username")
                					.setString("username", username)
                					.uniqueResult();
	}

	@Override
	public long create(User user) {
		return (long) sessionFactory.getCurrentSession()
                					.save(user);
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void delete(String username) {
		sessionFactory.getCurrentSession()
        			  .createQuery("delete from user where username= :username")
        			  .setString("username", username)
        			  .executeUpdate();
	}

}
