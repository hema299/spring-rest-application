package org.hema.spring_rest.controller;


import java.util.List;

import org.hema.spring_rest.constants.URI;
import org.hema.spring_rest.entity.User;
import org.hema.spring_rest.service.UserService;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@Controller
//@ResponseBody
//request mapping --optional of media we can remove mediatype


@RestController
@RequestMapping(value = URI.USERS)
@Api(tags="users")
public class UserController {
	
	//Constructor based injection
	private UserService service;
	
	public UserController(UserService service)
	{
		this.service = service;
	}
	
	//methods
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value="Find All Users", notes="Returns a list of users in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
    public List<User> findAll()
	{
		return service.findAll();
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value=URI.ID_PATH_VAR)
	@ApiOperation(value="Find User By Id", notes="Returns a user by id if it exists in the app")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Server Error"),
			@ApiResponse(code=404,message="Not Found")
	})
	public User findOne(@PathVariable("id") String id)
	{
		return service.findOne(id);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT , value=URI.ID_PATH_VAR)
	@ApiOperation(value="Update user", notes="Updates an existing user")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Server Error"),
			@ApiResponse(code=404,message="Not Found")
	})
	public User update(@PathVariable("id") String id, @RequestBody User user)
	{
		return service.Update(id, user);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value="Create User", notes="Create a user in the app with unique email")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Server Error"),
			@ApiResponse(code=404,message="Not Found")
	})
	public User create(@RequestBody User user)
	{
		return service.create(user);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE, value=URI.ID_PATH_VAR)
	@ApiOperation(value="Delete User", notes="Deletes an existing User")
	@ApiResponses(value={
			@ApiResponse(code=200,message="Success"),
			@ApiResponse(code=500,message="Internal Server Error"),
			@ApiResponse(code=404,message="Not Found")
	})
	public void delete(@PathVariable("id") String id)
	{
		service.delete(id);
	}

}
