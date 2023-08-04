package org.URLModule.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.URLModule.model.URLDB;
import org.URLModule.repo.URLShortenRepository;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class URLShortenService implements UserDetailsService {

	@Autowired
	URLShortenRepository repository;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	@Autowired
	RestTemplate resttemplate;
	
	public void create(URLDB urldetails) {
		urldetails.setPassword(passwordencoder.encode(urldetails.getPassword()));
		repository.save(urldetails);
	}
	
	public String shortenURL(String URL, String shortURL) throws ParseException {
		//	private String mainURL; private String shortenedURL; private int clicksCount;
		return getAuthenticatedUserDetails();
	}
	
	public String getAuthenticatedUserDetails() throws ParseException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("URLService", "urlsvc");
        String URL = "http://localhost:8080/details/userdetails/"+userName;
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = resttemplate.exchange(URL, HttpMethod.GET, request, String.class);
		JSONObject obj = (JSONObject)new JSONParser().parse(response.getBody());
		URLDB dbobj = (URLDB) loadUserByUsername(userName);
//		return dbobj.getPassword() + " " + (String)obj.get("password");
		if(passwordencoder.matches("abcNikhil", (String)obj.get("password"))) {
			return "Yes";
		}else {
			return "No";
		}
//		return (String)obj.get("username") + (String)obj.get("password");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return repository.findByuserName(username);
	}
}
