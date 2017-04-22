package org.hema.spring_rest.service;

import java.util.List;

import org.hema.spring_rest.entity.User;


public interface UserService {
	
	public List<User> findAll();
	
	public User findOne(String id);
	
	public User create(User user);
	
	public User Update(String id,User user);
	
	public void delete(String id);
	

}
