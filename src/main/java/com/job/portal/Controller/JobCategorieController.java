package com.job.portal.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.Entity.JobCategories;
import com.job.portal.Entity.Jobs;
import com.job.portal.Entity.Recruiters;
import com.job.portal.Exception.UserIdNotFoundException;
import com.job.portal.Repository.JobCategoriesRepository;
import com.job.portal.Repository.JobsRepository;
import com.job.portal.Repository.RecruitersRepository;


@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class JobCategorieController {

	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private RecruitersRepository recruitersRepository;
	
	@Autowired
	private JobCategoriesRepository jobCategoriesRepository; 
	
	
	@GetMapping("/jobcategory/{jobTitle}")
	 public ResponseEntity<?> getJobByJobTitle(@PathVariable("jobTitle") String jobTitle) throws UserIdNotFoundException
	 {
	
			Jobs jobs=this.jobsRepository.getJobByJobTitle(jobTitle)
			.orElseThrow(()-> new UserIdNotFoundException("User not found for this job title :"+jobTitle));
				return new ResponseEntity<Jobs>(jobs,HttpStatus.OK);
		 
	 }
	
	@GetMapping("/jobcategory/{companyname}")
	 public ResponseEntity<?> getJobByCompanyName(@PathVariable("companyname") String companyname ) throws UserIdNotFoundException
	 {
		 
		 
			Recruiters recruiters=this.recruitersRepository.getJobByCompanyname(companyname)
					 .orElseThrow(()-> new UserIdNotFoundException("User not found for this company name :"+companyname));
				return new ResponseEntity<Recruiters>(recruiters,HttpStatus.OK);
		 }
		
	@GetMapping("/jobcategory/{categoryName}")
	 public ResponseEntity<?> getJobCategoryByName(@PathVariable("categoryName") long categoryName ) throws UserIdNotFoundException
	 {
		 
			 JobCategories jobCategorie=this.jobCategoriesRepository.getJobByCategoryName(categoryName)
				.orElseThrow(()-> new UserIdNotFoundException("User not found for this category name :"+categoryName));
				return new ResponseEntity<JobCategories>(jobCategorie,HttpStatus.OK);
		
	 }
	
	
}
