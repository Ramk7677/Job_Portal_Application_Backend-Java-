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

import com.job.portal.Entity.Jobs;
import com.job.portal.Service.JobService;

@RestController
@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
public class JobsController {

	
	@Autowired
	private JobService  jobService;
	
	@PostMapping("/post/job")
	public ResponseEntity<Jobs> addJobs(@RequestBody Jobs jobs)
	{
		Jobs job=this.jobService.saveJobs(jobs);
		return new ResponseEntity<Jobs>(job,HttpStatus.CREATED);
		
	}
	@GetMapping("/get/alljobs")
	public ResponseEntity<List<Jobs>> getAllJob()
	{
		return new ResponseEntity<List<Jobs>>(this.jobService.getAllJobs(),HttpStatus.OK);
	}
	
	@GetMapping("/get/jobbyid/{jobId}")
	 public ResponseEntity<?> getJobsById(@PathVariable("jobId") long jobId )
	 {
		 if(this.jobService.getJobById(jobId).isPresent())
		 {
			 	Jobs job=this.jobService.getJobById(jobId).get();
				return new ResponseEntity<Jobs>(job,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
		 }
	 }
	
	
	@PutMapping("/update/jobbyid/{jobId}")
	public ResponseEntity<?> updateJob(@PathVariable ("jobId")long jobId, @RequestBody Jobs jobs)
	{
		Optional<Jobs> existingJob=this.jobService.getJobById(jobId);
		if(existingJob.isPresent())
		{
			existingJob.get().setJobDescription(jobs.getJobDescription());
			existingJob.get().setJobTitle(jobs.getJobTitle());
			existingJob.get().setActive(true);
			existingJob.get().setLastDate(jobs.getLastDate());

			this.jobService.updateJobs(existingJob.get() ,jobId);
			return new ResponseEntity<Jobs>(existingJob.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Id not found!!", HttpStatus.NOT_FOUND);
		}
			
	}
	
	
	@DeleteMapping("/delete/jobbyid/{jobId}")
	public ResponseEntity<?> deleteJobsById(@PathVariable ("jobId")long jobId)
	{
		
		if(this.jobService.getJobById(jobId).isPresent()) {
		this.jobService.deleteJobById(jobId);
	     return new ResponseEntity<String>("Record Delated",HttpStatus.OK);
	}
	
	else {
		 return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
	}
	}
	
	
	

}
