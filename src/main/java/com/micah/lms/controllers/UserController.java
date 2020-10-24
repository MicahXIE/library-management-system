package com.micah.lms.controllers;


import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.micah.lms.entity.User;
import com.micah.lms.service.UserServiceImpl;


@RestController
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value="/api/users", method=RequestMethod.GET, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<Object> getAllUsers() {
        List<User> userList = userServiceImpl.getAllUsers();
        List<JSONObject> users = new ArrayList<JSONObject>();

	    for (User u : userList) {
	        JSONObject user = new JSONObject();
	        user.put("id", u.getId());
	        user.put("name", u.getUserName());
	        
	        users.add(user);
	    }

        return new ResponseEntity<Object>(users, HttpStatus.OK);
    }

	@RequestMapping(value="/api/addUser", method=RequestMethod.POST, headers="Accept=application/json")
    @ResponseBody
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userServiceImpl.addUser(user);
        HttpHeaders header=new HttpHeaders();

        return new ResponseEntity<User>(header, HttpStatus.CREATED);
    }
	
}
