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

import com.job.portal.Entity.JobApplications;
import com.job.portal.Repository.JobApplicationsRepository;
import com.job.portal.Repository.JobSeekerRepository;
import com.job.portal.Service.JobApplicationService;

@RestController
@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;
	
	@Autowired
	private JobApplicationsRepository jobApplicationsRepository;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	@PostMapping("/jobappication")
	public ResponseEntity<JobApplications> addJobApplication(@RequestBody JobApplications jobApplications)
	{
		JobApplications jobApplication=this.jobApplicationService.saveJobApplications(jobApplications);
		return new ResponseEntity<JobApplications>(jobApplication,HttpStatus.CREATED);
		
	}
	@GetMapping("/jobappications")
	public ResponseEntity<List<JobApplications>> getAllJobApplication()
	{
		return new ResponseEntity<List<JobApplications>>(this.jobApplicationService.getAllJobApplications(),HttpStatus.OK);
	}
	
	@GetMapping("/jobappication/{jobapplicationId}")
	 public ResponseEntity<?> getJobApplicationById(@PathVariable("jobapplicationId") long jobapplicationId )
	 {
		 if(this.jobApplicationService.getJobApplicationsById(jobapplicationId).isPresent())
		 {
			 JobApplications jobApplication=this.jobApplicationService.getJobApplicationsById(jobapplicationId).get();
				return new ResponseEntity<JobApplications>(jobApplication,HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
		 }
	 }
	
	@PutMapping("/update/jobappication/{jobapplicationId}")
	public ResponseEntity<?> updateJobApplication(@PathVariable ("jobapplicationId")long jobapplicationId, @RequestBody JobApplications jobApplications)
	{
		Optional<JobApplications> exitJobApplication=this.jobApplicationService.getJobApplicationsById(jobapplicationId);
		if(exitJobApplication.isPresent())
		{
			exitJobApplication.get().setJobType(jobApplications.getJobType());
			exitJobApplication.get().setCreatedAt(jobApplications.getCreatedAt());
			exitJobApplication.get().setUpdatedAt(jobApplications.getUpdatedAt());

			

			this.jobApplicationService.updateJobApplications(exitJobApplication.get() ,jobapplicationId);
			return new ResponseEntity<JobApplications>(exitJobApplication.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Id not found!!", HttpStatus.NOT_FOUND);
		}
			
	}
	
	@DeleteMapping("delete/jobappication/{jobapplicationId}")
	public ResponseEntity<?> deleteJobApplicationById(@PathVariable ("jobapplicationId")long jobapplicationId)
	{
		if(this.jobApplicationService.getJobApplicationsById(jobapplicationId).isPresent()) {
		this.jobApplicationService.deleteJobApplicationsById(jobapplicationId);
		return new ResponseEntity<String>("Record deleted!!", HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<String>("Id not found!!", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/get/all/applyjob?/{jobseekerId}")
	public int getAllJobApplicationByJoseekerId(@PathVariable("jobseekerId") long jobseekerId ) {
		 if(this.jobSeekerRepository.findById(jobseekerId).isPresent()) {

	    return jobApplicationsRepository.totalId();
		 }
		 else {
			 return 0;
		 }
	}

}
