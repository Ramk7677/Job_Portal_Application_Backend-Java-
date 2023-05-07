package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.JobApplications;
import com.job.portal.Repository.JobApplicationsRepository;

@Service
public class JobApplicationServiceImp implements JobApplicationService{

	@Autowired
	private JobApplicationsRepository jobApplicationsRepository;
	

	@Override
	public JobApplications saveJobApplications(JobApplications jobApplications) {
		 return this.jobApplicationsRepository.save(jobApplications);
	}

	@Override
	public List<JobApplications> getAllJobApplications() {
		return this.jobApplicationsRepository.findAll();
	}

	@Override
	public Optional<JobApplications> getJobApplicationsById(long jobapplicationId) {
		return this.jobApplicationsRepository.findById(jobapplicationId);
	}

	
	@Override
	public void updateJobApplications(JobApplications jobApplications, long jobapplicationId) {
		Optional<JobApplications> uj= this.jobApplicationsRepository.findById(jobapplicationId);
		if(uj.isPresent()) {
			JobApplications ja=uj.get();
			ja.setJobType(jobApplications.getJobType());
			ja.setCreatedAt(jobApplications.getCreatedAt());
			ja.setUpdatedAt(jobApplications.getUpdatedAt());			
			
			this.jobApplicationsRepository.save(ja);
		}
	
	}

	@Override
	public void deleteJobApplicationsById(long jobapplicationId) {
      this.jobApplicationsRepository.deleteById(jobapplicationId);		
	}
	
	
	
}
