package org.URLModule.dto;

import org.URLModule.model.URLDB;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateURLUser {

	private String userName;
	private String password;
	
	public URLDB buildUser() {
		return URLDB.builder().userName(this.userName).password(this.password).authorities("user").build();
	}
}
