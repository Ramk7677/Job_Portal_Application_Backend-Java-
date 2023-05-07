package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.Jobs;
import com.job.portal.Repository.JobsRepository;

@Service
public class JobServiceImp implements JobService {

	@Autowired
	private JobsRepository jobsRepository;
	
	
	@Override
	public List<Jobs> getAllJobs() {
		return jobsRepository.findAll();
	}

	@Override
	public void deleteJobById(long jobId) {
		this.jobsRepository.deleteById(jobId);
	}

	@Override
	public Jobs saveJobs(Jobs jobs) {
		return this.jobsRepository.save(jobs);
	}

	@Override
	public void updateJobs(Jobs jobs, long jobId) {
		Optional<Jobs> job=this.jobsRepository.findById(jobId);
		
		if(job.isPresent()) {
			Jobs j=job.get();
			j.setJobDescription(jobs.getJobDescription());
			j.setJobTitle(jobs.getJobTitle());
			j.setActive(true);
			j.setLastDate(jobs.getLastDate());
			j.setCreatedAt(jobs.getCreatedAt());
			j.setUpdatedAt(jobs.getUpdatedAt());
			
			this.jobsRepository.save(j); 
		}
	}

	@Override
	public Optional<Jobs> getJobById(long jobId) {
		return this.jobsRepository.findById(jobId);
	}

	@Override
	public Optional<Jobs> getJobByJobTitle(String job_title) {
		return this.jobsRepository.getJobByJobTitle(job_title);
	}

}
