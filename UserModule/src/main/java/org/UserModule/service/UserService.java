package org.UserModule.service;

import org.UserModule.dto.CreateUser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import org.UserModule.model.UserData;
import org.UserModule.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	PasswordEncoder passwordencode;
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	RestTemplate restTemplate;

	
	public void create(UserData userdata) throws URISyntaxException {
		String userpwd = userdata.getPassword();
		userdata.setPassword(passwordencode.encode(userdata.getPassword()));
		repo.save(userdata);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBasicAuth("UserService", "usrsvc");
		CreateUser createuser = new CreateUser(userdata.getUsername(), userpwd);
		HttpEntity<CreateUser> entity = new HttpEntity<>(createuser, headers);
//		String url = "http://localhost:9090/url-shortener/signup";
		URI url = new URI("http://localhost:9090/url-shortener/signup");
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
	}

	public UserData getPasswordforService(String username) {
		return repo.findByuserName(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return repo.findByuserName(userName);
	}
}
