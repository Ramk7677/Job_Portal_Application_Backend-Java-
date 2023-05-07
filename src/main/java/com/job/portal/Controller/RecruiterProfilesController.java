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

import com.job.portal.Entity.RecruiterProfiles;
import com.job.portal.Service.RecruiterProfileService;

@RestController
@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
public class RecruiterProfilesController {

	@Autowired
	private RecruiterProfileService  recruiterProfileService;
	
	@PostMapping("/post/recruiterProfile")
	public ResponseEntity<RecruiterProfiles> addRecruiterProfile(@RequestBody RecruiterProfiles recruiterProfiles)
	{
		RecruiterProfiles recruiterProfile=this.recruiterProfileService.saveRecruiterProfiles(recruiterProfiles);
		return new ResponseEntity<RecruiterProfiles>(recruiterProfile,HttpStatus.CREATED);
		
	}
	@GetMapping("/get/allrecruiterProfiles")
	public ResponseEntity<List<RecruiterProfiles>> getAllRecruiterProfile()
	{
		return new ResponseEntity<List<RecruiterProfiles>>(this.recruiterProfileService.getAllRecruiterProfiles(),HttpStatus.OK);
	}
	
	@GetMapping("/get/recruiterProfilebyid/{recruiterprofileId}")
	 public ResponseEntity<?> getRecruiterProfileById(@PathVariable("recruiterprofileId") long recruiterprofileId )
	 {
		 if(this.recruiterProfileService.getRecruiterProfilesById(recruiterprofileId).isPresent())
		 {
			 RecruiterProfiles recruiterProfile=this.recruiterProfileService.getRecruiterProfilesById(recruiterprofileId).get();
				return new ResponseEntity<RecruiterProfiles>(recruiterProfile,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
		 }
	 }
	
	
	@PutMapping("/update/recruiterProfilebyid/{recruiterprofileId}")
	public ResponseEntity<?> updateRecruiterProfile(@PathVariable ("recruiterprofileId")long recruiterprofileId, @RequestBody RecruiterProfiles recruiterProfiles)
	{
		Optional<RecruiterProfiles> exitRecruiterProfile=this.recruiterProfileService.getRecruiterProfilesById(recruiterprofileId);
		if(exitRecruiterProfile.isPresent())
		{
			exitRecruiterProfile.get().setWebsite(recruiterProfiles.getWebsite());
			exitRecruiterProfile.get().setPhone(recruiterProfiles.getPhone());
			exitRecruiterProfile.get().setCompany_logo(recruiterProfiles.getCompany_logo());
			exitRecruiterProfile.get().setDescription(recruiterProfiles.getDescription());
			exitRecruiterProfile.get().setCompany_address(recruiterProfiles.getCompany_address());
			exitRecruiterProfile.get().setCountryname(recruiterProfiles.getCountryname());


			this.recruiterProfileService.updateRecruiterProfiles(exitRecruiterProfile.get() ,recruiterprofileId);
			return new ResponseEntity<RecruiterProfiles>(exitRecruiterProfile.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Id not found!!", HttpStatus.NOT_FOUND);
		}
			
	}
	
	
	@DeleteMapping("/delete/recruiterProfilebyid/{recruiterprofileId}")
	public ResponseEntity<?> deleteRecruiterProfileById(@PathVariable ("recruiterprofileId")long recruiterprofileId)
	{
		if(this.recruiterProfileService.getRecruiterProfilesById(recruiterprofileId).isPresent()) {
		this.recruiterProfileService.deleteRecruiterProfilesById(recruiterprofileId);
		return new ResponseEntity<String>("Record deleted!!", HttpStatus.OK);
		
		}
		else {
			return new ResponseEntity<String>("Id not found!!", HttpStatus.NOT_FOUND);
		}
	}
	


}
