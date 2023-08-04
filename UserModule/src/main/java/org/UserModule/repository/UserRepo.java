package org.UserModule.repository;

import org.UserModule.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserData,Integer> {

	UserData findByuserName(String name);
}
