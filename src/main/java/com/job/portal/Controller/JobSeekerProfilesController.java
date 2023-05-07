package com.job.portal.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.Entity.JobSeeker;
import com.job.portal.Entity.JobSeekerProfiles;
import com.job.portal.Exception.UserIdNotFoundException;
import com.job.portal.Repository.JobSeekerRepository;
import com.job.portal.Service.JobSeekerProfileService;

@RestController
@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
public class JobSeekerProfilesController {

	@Autowired
	private JobSeekerProfileService jobSeekerProfileService;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	
	
	@PostMapping("/create/userprofile")
	public ResponseEntity<JobSeekerProfiles> addJobSeekerProfile(@RequestBody JobSeekerProfiles jobSeekerProfiles)
	{
		return new ResponseEntity<JobSeekerProfiles>(this.jobSeekerProfileService.saveJobSeekerProfile(jobSeekerProfiles),HttpStatus.CREATED);
		
	}
	@GetMapping("/get/alluserprofiles")
	public ResponseEntity<List<JobSeekerProfiles>> getAllJobSeekerProfile()
	{
		return new ResponseEntity<List<JobSeekerProfiles>>(this.jobSeekerProfileService.getAllJobSeekerProfiles(),HttpStatus.OK);
	}
	
	@GetMapping("/get/userprofilebyid/{jobseekerprofileid}")
	 public ResponseEntity<?> getJobSeekerProfileById(@PathVariable("jobseekerprofileid") long jobseekerprofileid )
	 {
		 if(this.jobSeekerProfileService.getJobSeekerProfilesById(jobseekerprofileid).isPresent())
		 {
			 JobSeekerProfiles jobSeekerProfile=this.jobSeekerProfileService.getJobSeekerProfilesById(jobseekerprofileid).get();
				return new ResponseEntity<JobSeekerProfiles>(jobSeekerProfile,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<String>("User not found for this id :"+jobseekerprofileid,HttpStatus.NOT_FOUND);
		 }
	 }
	
	@PutMapping("/update/userprofilebyid/{jobseekerprofileId}")
	public ResponseEntity<?> updateJobSeekerProfiles(@PathVariable ("jobseekerprofileId")long jobseekerprofileid, @RequestBody JobSeekerProfiles jobSeekerProfiles)
	{
		Optional<JobSeekerProfiles> exitJobSeekerProfile=this.jobSeekerProfileService.getJobSeekerProfilesById(jobseekerprofileid);
		if(exitJobSeekerProfile.isPresent())
		{
			exitJobSeekerProfile.get().setGender(jobSeekerProfiles.getGender());
			exitJobSeekerProfile.get().setDateOfBirth(jobSeekerProfiles.getDateOfBirth());
			exitJobSeekerProfile.get().setState(jobSeekerProfiles.getState());
			exitJobSeekerProfile.get().setDistric(jobSeekerProfiles.getDistric());
			exitJobSeekerProfile.get().setPincode(jobSeekerProfiles.getPincode());
			exitJobSeekerProfile.get().setAddress(jobSeekerProfiles.getAddress());
			exitJobSeekerProfile.get().setQualification(jobSeekerProfiles.getQualification());
			exitJobSeekerProfile.get().setPercentage(jobSeekerProfiles.getPercentage());
			exitJobSeekerProfile.get().setCvPath(jobSeekerProfiles.getCvPath());
			exitJobSeekerProfile.get().setImage(jobSeekerProfiles.getImage());
			exitJobSeekerProfile.get().setShortDescription(jobSeekerProfiles.getShortDescription());


			this.jobSeekerProfileService.updateJobSeekerprofile(exitJobSeekerProfile.get() ,jobseekerprofileid);
			return new ResponseEntity<JobSeekerProfiles>(exitJobSeekerProfile.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("User not found for this id :"+jobseekerprofileid, HttpStatus.NOT_FOUND);
		}
			
	}
	
	@DeleteMapping("/delete/userprofilebyid/{jobseekerprofileId}")
	public ResponseEntity<?> deleteJobSeekerProfileById(@PathVariable ("jobseekerprofileId")long jobseekerprofileid)
	{
		if(this.jobSeekerProfileService.getJobSeekerProfilesById(jobseekerprofileid).isPresent()) {
		this.jobSeekerProfileService.deleteJobSeekerProfilesById(jobseekerprofileid);
		return new ResponseEntity<String>("Record deleted succesfully!!", HttpStatus.OK);
		
		}
		
		else {
			return new ResponseEntity<String>("User not found for this id :"+jobseekerprofileid , HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping("/get/userbyemail/{email}")
	 public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) throws UserIdNotFoundException
	 {
	
		JobSeeker jobSeeker=this.jobSeekerRepository.findByEmail(email)
			.orElseThrow(()-> new UserIdNotFoundException("User not found for this email id :"+email));
				return new ResponseEntity<JobSeeker>(jobSeeker,HttpStatus.OK);
		 
	 }
	
	@GetMapping("/get/user?byid/{jobseekerId}")
	 public ResponseEntity<?> getUserById(@PathVariable("jobseekerId") long jobseekerId) throws UserIdNotFoundException
	 {
	
		JobSeeker jobSeeker=this.jobSeekerRepository.findById(jobseekerId)
			.orElseThrow(()-> new UserIdNotFoundException("User not found for this user-id :"+jobseekerId));
				return new ResponseEntity<JobSeeker>(jobSeeker,HttpStatus.OK);
		 
	 }

	

}
