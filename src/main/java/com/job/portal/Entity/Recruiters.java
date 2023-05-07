package com.job.portal.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recruiters_tb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Recruiters {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recruiterId;
	
	@Column(name="User_Name",nullable = false,length = 255)
	@NotNull(message = "User name can not be empty")
	private String username;
	
	
	@Column(nullable = false,length = 255,name = "Recruiter_Name")
	@NotNull(message = "Recruiter name can not be empty")
	private String recruiterName;
	
	
	@Column(name = "Recruiter_Email",nullable = false,length =255,unique = true)
	@NotNull(message = "Recruiter email Id can not be empty")
	@Email(message = "Incorrect format")
	private String email;
	
	
	@Column(nullable = false,length = 12,name = "Recruiter_PhoneNo")
	@NotNull(message = "Recruiter phone number can not be empty")
	private Long recruiterPhone;
	
	
	@Column(nullable = false,length = 255,unique = true,name = "Recruiter_Password")
	@NotNull(message = "Password can not be empty")
    @Size(message = "lenght must be min size 8 and max size 16 characters")
	private String recruiterPassword;

	
	@Column(name="Company_Name",nullable = false,length = 255)
	@NotNull(message = "Company name can not be empty")
	private String companyname;

	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(nullable = false,name = "Created_Time")
	private Date createdAt;
	
	
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(nullable = false,name = "Updated_Time")
	private Date updatedAt;
	
	
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "recruiter_roles",
        joinColumns = @JoinColumn(name = "recruiter_id", referencedColumnName = "recruiterId"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
	 
}
