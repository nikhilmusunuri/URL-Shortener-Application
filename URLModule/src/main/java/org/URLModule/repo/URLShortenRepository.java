package org.URLModule.repo;

import org.URLModule.model.URLDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLShortenRepository extends JpaRepository<URLDB,Integer> {

	URLDB findByuserName(String username);
}
