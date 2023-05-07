package com.job.portal.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class JobSeekerSignupDTO {

	// jobseeker(USER) Signup Properties

	private String username;

	private String firstname;

	private String lastname;

	@Email(message = "Incorrect email format")
	private String email;

	private Long phone;

	private String password;

}
