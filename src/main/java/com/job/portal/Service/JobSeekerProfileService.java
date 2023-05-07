package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import com.job.portal.Entity.JobSeekerProfiles;


public interface JobSeekerProfileService {

	JobSeekerProfiles saveJobSeekerProfile(JobSeekerProfiles jobSeekerProfiles);

	Optional<JobSeekerProfiles> getJobSeekerProfilesById(long jobseekerprofileid);

	List<JobSeekerProfiles> getAllJobSeekerProfiles();

	JobSeekerProfiles updateJobSeekerprofile(JobSeekerProfiles jobSeekerProfiles, long jobseekerprofileid);

	void deleteJobSeekerProfilesById(long jobseekerprofileid);
	
	
	/*
	 * Employee saveEmployee(Employee employee); Optional<Employee>
	 * getEmployeeById(long id); List<Employee> getAllEmployee(); Employee
	 * updateEmployee(Employee employee); void deleteEmployee(long id);
	 */

	
}
