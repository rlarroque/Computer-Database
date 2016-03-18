package com.excilys.computer_database.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.computer_database.dao.UserDAO;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.User;
import com.excilys.computer_database.services.UserService;
import com.excilys.computer_database.services.mapper.UserMapper;

/**
 * This class is the implementation of the UserService interface. 
 * @author rlarroque
 *
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
	
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserMapper userMapper;

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public User get(String username) {
		if (username == null || "".equals(username)) {
            throw new IntegrityException("A name is mandatory for a user.");
        }

        return userDAO.get(username);
	}

	@Override
	public long create(User user) {
		return userDAO.create(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void delete(String username) {
		userDAO.delete(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = get(username);
		
		return userMapper.buildUserForAuthentification(user);
	}

}
