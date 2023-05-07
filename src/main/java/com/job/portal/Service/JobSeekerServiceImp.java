package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.JobSeeker;
import com.job.portal.Repository.JobSeekerRepository;

@Service
public class JobSeekerServiceImp implements JobSeekerService{

	@Autowired
	private JobSeekerRepository jobSeekerRepository;

	@Override
	public JobSeeker saveJobSeeker(JobSeeker jobSeekers) {
		return this.jobSeekerRepository.save(jobSeekers);
		
	}

	@Override
	public void deleteJobSeekerById(long jobseekerId){
     this.jobSeekerRepository.deleteById(jobseekerId);		
	}

	@Override
	public void updateJobSeekerById(JobSeeker jobSeeker, long jobseekerId) {
   
		Optional<JobSeeker> uj=this.jobSeekerRepository.findById(jobseekerId);
		if(uj.isPresent()) {
			JobSeeker js=uj.get();
			js.setEmail(jobSeeker.getEmail());
			js.setPassword(jobSeeker.getPassword());
			js.setFirstname(jobSeeker.getFirstname());
			js.setLastname(jobSeeker.getLastname());
			js.setUsername(jobSeeker.getUsername());
			js.setPhone(jobSeeker.getPhone());
			js.setCreatedAt(jobSeeker.getCreatedAt());
			js.setUpdatedAt(jobSeeker.getUpdatedAt());
			
			this.jobSeekerRepository.save(js);
		}
		this.jobSeekerRepository.save(jobSeeker);
		
	}

	@Override
	public Optional<JobSeeker> getJobSeekerById(long jobseekerId) {
		Optional<JobSeeker> ExitJobSeeker=this.jobSeekerRepository.findById(jobseekerId);
		if(ExitJobSeeker.isPresent()) {
		return this.jobSeekerRepository.findById(jobseekerId);
		
		}
		return ExitJobSeeker;
		
	}

	@Override
	public List<JobSeeker> getAllJobSeeker() {
		return this.jobSeekerRepository.findAll();
	}

}
