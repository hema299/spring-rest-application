package org.hema.spring_rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.hema.spring_rest.entity.User;
import org.hema.spring_rest.exception.BadRequestException;
//import org.hema.spring_rest.exception.BadRequestException;
import org.hema.spring_rest.exception.NotFoundExeption;
import org.hema.spring_rest.repository.UserRepository;
import org.hema.spring_rest.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	
	public UserServiceImpl(UserRepository repository)
	{
		this.repository=repository;
	}

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public User findOne(String id) {
	
		return repository.findOne(id).
				orElseThrow(()-> new NotFoundExeption("User with id " +id +"does not exist"));
		
	}

	@Override
	@Transactional     //select it from spring version
	public User create(User user) {
		
	 Optional<User> mayExists = repository.findByEmail(user.getEmail());
	 if(mayExists.isPresent())
	 {
		 throw new BadRequestException("User with email"+user.getEmail()+"already exists");
	 }
	 return repository.create(user);
	}

	@Override
	@Transactional
	public User Update(String id, User user){
		
		repository.findOne(id).
				orElseThrow(()-> new NotFoundExeption("User with id " +id +"does not exist"));
		return repository.update(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		
		User existing=repository.findOne(id).
				orElseThrow(()-> new NotFoundExeption("User with id " +id +"does not exist"));
		
		repository.delete(existing);
	}

}

