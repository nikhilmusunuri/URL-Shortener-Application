package org.URLModule.controller;

import org.URLModule.dto.CreateURLUser;
import org.URLModule.dto.URLShortenDTO;
import org.URLModule.service.URLShortenService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLShortenerController {
	
	@Autowired
	URLShortenService service;

	@PostMapping("/url-shortener/shortenURL")
	public String urlShorten(@RequestBody URLShortenDTO urlshortenbody) throws ParseException {
		//put/patch mapping
		//check response not null, password match, if yes then save urls to db 
		return service.shortenURL(urlshortenbody.getURL(), urlshortenbody.getShortenURL());
	}
	
	@PostMapping("/url-shortener/signup")
	public void userSignup(@RequestBody CreateURLUser createuser) {
		service.create(createuser.buildUser());
	}
	
	@GetMapping("/testapicall")
	public String testapicall() throws ParseException {
		return service.getAuthenticatedUserDetails();
	}
}
