package com.excilys.computer_database.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.excilys.computer_database.model.User;
import com.excilys.computer_database.model.UserRole;

/**
 * Mapper used to convert users into authentified user or dtos
 * @author rlarroque
 */
@Component
public class UserMapper {
		
	public UserDetails buildUserForAuthentification(User user) {
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
