package com.job.portal.Entity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jobseeker_tb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobseekerId;

	@Column(nullable = false, length = 255, name = "User_Name")
	@NotNull(message = "User name must be 3 characters")
	private String username;

	@Column(nullable = false, length = 255, name = "First_Name")
	@NotNull(message = "First name must be 3 characters")
	private String firstname;

	@Column(nullable = false, length = 255, name = "Last_Name")
	@NotNull(message = "Last name must be 3 characters")
	private String lastname;

	@Column(nullable = false, length = 255, unique = true, name = "jobseeker_Email")
	@NotNull(message = "Email can not be empty")
	@Email(message = "Incorrect email format")
	private String email;

	@Column(name = "Phone_No", nullable = false, length = 255)
	@NotNull(message = "Phone number must be 10 digits")
	private Long phone;

	@Column(nullable = false, length = 255, name = "jobseeker_password")
	@NotNull(message = "Password can not be empty")
	private String password;

	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	@Column(nullable = false, name = "Created_Time")
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "Updated_Time")
	private Date updatedAt;


	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "JobSeeker_roles", joinColumns = @JoinColumn(name = "JobSeeker_id", referencedColumnName = "jobseekerId"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

}
