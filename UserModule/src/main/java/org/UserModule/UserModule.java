package org.UserModule;

import org.UserModule.model.UserData;
import org.UserModule.repository.UserRepo;
import org.UserModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserModule implements CommandLineRunner
{
	@Autowired
	UserRepo repo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(UserModule.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repo.save(UserData.builder().userName("URLService").password(passwordEncoder.encode("urlsvc")).authorities("service").build());
	}
}
