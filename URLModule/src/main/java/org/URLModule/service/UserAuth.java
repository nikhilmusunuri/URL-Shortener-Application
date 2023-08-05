package org.URLModule.service;

import org.URLModule.model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAuth implements UserDetailsService{

	@Autowired
	RestTemplate resttemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("URLService", "urlsvc");
        String URL = "http://localhost:8080/details/userdetails/"+username;
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = resttemplate.exchange(URL, HttpMethod.GET, request, String.class);
        JSONObject obj = null;
		try {
			obj = (JSONObject)new JSONParser().parse(response.getBody());
		} 
		catch (ParseException e) {
		}
		JSONArray authoritiesArray = (JSONArray) obj.get("authorities");
		JSONObject authorityObject = (JSONObject) authoritiesArray.get(0);
	    String authorityValue = (String) authorityObject.get("authority");
		return User.builder().userName((String)obj.get("username")).password((String)obj.get("password")).authorities(authorityValue).build();
	}

}
