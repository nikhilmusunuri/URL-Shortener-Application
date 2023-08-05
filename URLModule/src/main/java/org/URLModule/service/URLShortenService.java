package org.URLModule.service;


import java.util.UUID;
import org.URLModule.model.URLDB;
import org.URLModule.repo.URLShortenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class URLShortenService {
	
	@Autowired
	URLShortenRepository repo;
	
	public String createURLdetails(URLDB urldb) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		urldb.setUserName(userName);
		String shorturl = urldb.getShortenedURL();
		if(shorturl==null || shorturl == "") {
			shorturl = UUID.randomUUID().toString().substring(0,4);
		}
		urldb.setShortenedURL(shorturl);
		repo.save(urldb);
		return shorturl;
	}
	
	public ResponseEntity<Void> redirectToMainurl(String shorturl){
		String originalURL = getmainURL(shorturl);
		return ResponseEntity.status(302).header("Location", originalURL).build();
	}
	public String getmainURL(String shorturl) {
		int count = repo.findByshortenedURL(shorturl).getClicksCount();
		count=count+1;
		repo.updateClicksCount(shorturl, count);
		return repo.findByshortenedURL(shorturl).getMainURL();
	}
	
	public int getClicksCount(String shorturl) {
		return repo.findByshortenedURL(shorturl).getClicksCount();
	}
}
