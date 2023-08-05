package org.UserModule.service;


import java.net.URISyntaxException;
import org.UserModule.model.UserData;
import org.UserModule.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
		userdata.setPassword(passwordencode.encode(userdata.getPassword()));
		repo.save(userdata);
		
	}

	public UserData getUserDetails(String username) {
		return repo.findByuserName(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return repo.findByuserName(userName);
	}
}
