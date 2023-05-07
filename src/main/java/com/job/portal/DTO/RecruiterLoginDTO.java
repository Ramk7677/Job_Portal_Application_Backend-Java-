package com.job.portal.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RecruiterLoginDTO {

	@NotNull(message = "Email not matches")
	@Email(message = "Incorrect email format")
	private String email;
	
	
	@NotNull(message = "Password not matches")
	private String recruiterPassword;
}
