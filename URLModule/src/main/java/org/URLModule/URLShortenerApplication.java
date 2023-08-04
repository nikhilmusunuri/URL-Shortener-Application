package org.URLModule;


import org.URLModule.model.URLDB;
import org.URLModule.repo.URLShortenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class URLShortenerApplication implements CommandLineRunner 
{
	@Autowired
	URLShortenRepository repo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(URLShortenerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repo.save(URLDB.builder().userName("UserService").password(passwordEncoder.encode("usrsvc")).authorities("service").build());
	}
}
