package org.UserModule.controller;

import java.net.URISyntaxException;

import org.UserModule.dto.CreateUser;
import org.UserModule.model.UserData;
import org.UserModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	UserService userservice;

	@PostMapping("/url-shortener/signup")
	public String createUser(@RequestBody CreateUser createuser) throws URISyntaxException {
		userservice.create(createuser.to());
		return "Sign-Up successful";
	}
	
	@GetMapping(value="/details/userdetails/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserData getUserCredentials(@PathVariable("username") String username) {
		return userservice.getPasswordforService(username);
	}
}
