package org.UserModule.dto;


import javax.validation.constraints.NotBlank;
import org.UserModule.model.UserData;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUser {

	@NotBlank(message="User name can't be empty")
	private String userName;
	@NotBlank
	private String password;
	
	public UserData to() {
		return UserData.builder().userName(this.userName).password(this.password).authorities("user").build();
	}
}
