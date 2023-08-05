package org.URLModule.repo;

import javax.transaction.Transactional;
import org.URLModule.model.URLDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface URLShortenRepository extends JpaRepository<URLDB,Integer> {

	URLDB findByshortenedURL(String shorturl);
	
	@Transactional
	@Modifying
	@Query("update URLDB url set url.clicksCount = ?2 where url.shortenedURL = ?1")
    void updateClicksCount(String shortenurl, int count);
}
