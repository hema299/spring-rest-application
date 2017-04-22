package org.hema.spring_rest.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hema.spring_rest.entity.User;
import org.hema.spring_rest.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryimpl implements UserRepository{
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<User> findAll() {
		
		TypedQuery<User> query = em.createNamedQuery("User.findAll",User.class);
		
		return query.getResultList();
	}
	
    @Override
	public Optional<User> findOne(String id) {
	
	return Optional.ofNullable(em.find(User.class, id));
	}

	@Override
	public User create(User user) {
		
		 em.persist(user);
		 return user;
	}

	@Override
	public User update(User user){
		
		return em.merge(user);
	}

	@Override
	public void delete(User user) {
		
		em.remove(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail",User.class);
		query.setParameter("pEmail", email);
		List<User> users = query.getResultList();
				
			if(!users.isEmpty())
			{
				return Optional.of(users.get(0));
			}else{
				return Optional.empty();
			}

	}
	}
