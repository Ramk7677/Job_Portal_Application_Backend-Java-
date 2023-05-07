package com.job.portal.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jobseekerprofiles_tb")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class JobSeekerProfiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobseekerprofileid;

	@Column(name = "Gender", nullable = false, length = 255)
	@NotNull(message = "Gender can not be empty")
	private String gender;

	@Column(name = "Date_Of_Birth", nullable = false, length = 255)
	@NotNull(message = "Date of birth can not empty")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	@Column(name = "state", nullable = false, length = 255)
	@NotNull(message = "State name can not be empty")
	private String state;

	@Column(name = "Distric", nullable = false, length = 255)
	@NotNull(message = "Dristric name can not be empty")
	private String distric;

	@Column(name = "pin_code", nullable = false, length = 255)
	@NotNull(message = "Pin code/Zip code can not be empty")
	private int pincode;

	@Column(name = "Address", nullable = false, length = 255)
	@NotNull(message = "Address can not be empty")
	private String address;

	@Column(name = "Qualification", nullable = false, length = 255)
	@NotNull(message = "Highest qualification can not be empty")
	private String qualification;

	@Column(name = "Percentage", nullable = false, length = 255)
	@NotNull(message = "Percentage can not be empty")
	private Double percentage;

	@Column(name = "CV_Path", length = 255)
	@NotNull(message = "CV must be pdf , png , jpg etc.")
	private String cvPath;

	@Column(name = "image", unique = false, length = 100000)
	@Lob
	private String image;

	@Column(name = "Short_Description", nullable = false, length = 1000)
	private String shortDescription;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "Created_Time", nullable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "Updated_Time", nullable = false)
	private Date updatedAt;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "jobseeker_id")
	private JobSeeker jobseeker;

}
