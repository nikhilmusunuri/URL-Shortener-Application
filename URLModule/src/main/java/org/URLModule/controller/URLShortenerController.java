package org.URLModule.controller;


import org.URLModule.dto.URLShortenDTO;
import org.URLModule.service.URLShortenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLShortenerController {
	
	@Autowired
	URLShortenService service;

	@PostMapping("/url-shortener/shortenurl")
	public String shortenurl(@RequestBody URLShortenDTO shortenDto) {
		String shorturl = service.createURLdetails(shortenDto.to());
		return "URL Shortening is successful, here is your shortened URL: http://localhost:9090/US/short/" + shorturl;
	}

	
	@GetMapping("/US/short/{shorturl}")
	public ResponseEntity<Void> redirectingToMainURL(@PathVariable("shorturl") String shorturl){
		return service.redirectToMainurl(shorturl);
	}
	
	@GetMapping("/US/getmyUrlClicks/{shorturl}")
	public int getClicksCount(@PathVariable("shorturl") String shorturl) {
		int count = service.getClicksCount(shorturl);
		return count;
	}

}
