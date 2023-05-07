package com.job.portal.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RecruiterSignupDTO {

	// Recruiter signup properties

	private String username;

	private String recruiterName;

	@Email(message = "Incorrect format")
	private String email;

	private Long recruiterPhone;

	@Size(message = "lenght must be min size 8 and max size 16 characters")
	private String RecruiterPassword;

	private String companyname;

}
