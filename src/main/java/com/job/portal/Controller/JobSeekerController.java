package com.job.portal.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.DTO.JobSeekerSignupDTO;
import com.job.portal.DTO.JobseekerLoginDTO;
import com.job.portal.Entity.JobSeeker;
import com.job.portal.Entity.Role;
import com.job.portal.Exception.UserIdNotFoundException;
import com.job.portal.Repository.JobSeekerRepository;
import com.job.portal.Repository.RoleRepository;
import com.job.portal.Service.JobSeekerService;

@RestController
@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
public class JobSeekerController {
 
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	  @Autowired
	    private JobSeekerRepository jobSeekerRepository;

	    @Autowired
	    private RoleRepository roleRepository;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	
	@PostMapping("/create/user")
	public ResponseEntity<JobSeeker> addJobSeeker(@RequestBody JobSeeker jobSeekers)
	{
		JobSeeker jobSeeker=this.jobSeekerService.saveJobSeeker(jobSeekers);
		return new ResponseEntity<JobSeeker>(jobSeeker,HttpStatus.CREATED);
		
	}
	@GetMapping("/get/allusers")
	public ResponseEntity<List<JobSeeker>> getAllJobSeeker()
	{
		return new ResponseEntity<List<JobSeeker>>(this.jobSeekerService.getAllJobSeeker(),HttpStatus.OK);
	}
	
	@GetMapping("/get/userbyid/{jobseekerId}")
	 public ResponseEntity<?> getJobSeekerById(@PathVariable("jobseekerId") long jobseekerId)throws UserIdNotFoundException
	 {
		 if(this.jobSeekerService.getJobSeekerById(jobseekerId).isPresent())
		 {
			 JobSeeker jobSeeker=this.jobSeekerService.getJobSeekerById(jobseekerId).get();
				return new ResponseEntity<JobSeeker>(jobSeeker,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<String>("User not found for this id :"+jobseekerId ,HttpStatus.NOT_FOUND);
		 }
	 }
	
	@PutMapping("/update/userbyid/{jobseekerId}")
	public ResponseEntity<?> updateJobSeekerById(@PathVariable ("jobseekerId")long jobseekerId, @RequestBody JobSeeker jobSeekers)
	{
		Optional<JobSeeker> exitJobSeeker=this.jobSeekerService.getJobSeekerById(jobseekerId);
		if(exitJobSeeker.isPresent())
		{
			exitJobSeeker.get().setEmail(jobSeekers.getEmail());
			exitJobSeeker.get().setPassword(jobSeekers.getPassword());
			exitJobSeeker.get().setFirstname(jobSeekers.getFirstname());
			exitJobSeeker.get().setLastname(jobSeekers.getLastname());
			exitJobSeeker.get().setUsername(jobSeekers.getUsername());
			exitJobSeeker.get().setPhone(jobSeekers.getPhone());


			this.jobSeekerService.updateJobSeekerById(exitJobSeeker.get() ,jobseekerId);
			return new ResponseEntity<JobSeeker>(exitJobSeeker.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("User not found for this id :"+jobseekerId, HttpStatus.NOT_FOUND);
		}
			
	}
	
	@DeleteMapping("/delete/userbyid/{jobseekerId}")
	public ResponseEntity<?> deleteJobSeekerById(@PathVariable ("jobseekerId")long jobseekerId)
	{
		if(this.jobSeekerService.getJobSeekerById(jobseekerId).isPresent()) {
			this.jobSeekerService.deleteJobSeekerById(jobseekerId);
			return new ResponseEntity<String>("Record deleted succesfully!!", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("User not found for this id :"+jobseekerId, HttpStatus.NOT_FOUND); 
		}
		
	}
	
	 @PostMapping("/jobseeker/signin")
	    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody JobseekerLoginDTO loginDto){
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getEmail(), loginDto.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        Map<String, String> response=new HashMap<>();
	        response.put("message", "User signed-in successfully!");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	    @PostMapping("/jobseeker/signup")
	    public ResponseEntity<?> registerUser(@RequestBody JobSeekerSignupDTO signUpDto){
	    	 Map<String, String> response=new HashMap<>();
	      
	        // add check for email exists in DB
	        if(jobSeekerRepository.existsByEmail(signUpDto.getEmail())){
	        	response.put("message","Email is already taken!");
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }

	        // create user object
	        JobSeeker jobSeeker = new JobSeeker();
	        jobSeeker.setUsername(signUpDto.getUsername());
	        jobSeeker.setFirstname(signUpDto.getFirstname());
	        jobSeeker.setLastname(signUpDto.getLastname());
	        jobSeeker.setEmail(signUpDto.getEmail());
	        jobSeeker.setPhone(signUpDto.getPhone());
	        jobSeeker.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

	        Role roles = roleRepository.findByName("ROLE_JOBSEEKER").get();
	        jobSeeker.setRoles(Collections.singleton(roles));

	        jobSeekerRepository.save(jobSeeker);
	        
	       
	        response.put("message", "JobSeeker registered successfully");

	        return new ResponseEntity<>(response, HttpStatus.OK);

	    }

	
	 
}
