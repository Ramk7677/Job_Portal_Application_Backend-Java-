package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.JobSeekerProfiles;
import com.job.portal.Entity.Jobs;
import com.job.portal.Repository.JobSeekerProfilesRepository;

@Service
public class JobSeekerProfileServiceImp implements JobSeekerProfileService{

	
	
	@Autowired
	private JobSeekerProfilesRepository jobSeekerProfilesRepository;
	
	
	
	@Override
	public JobSeekerProfiles saveJobSeekerProfile(JobSeekerProfiles jobSeekerProfiles) {
		return this.jobSeekerProfilesRepository.save(jobSeekerProfiles);
		
		
	}

	@Override
	public Optional<JobSeekerProfiles> getJobSeekerProfilesById(long jobseekerprofileid) {
		return this.jobSeekerProfilesRepository.findById(jobseekerprofileid);
	}

	@Override
	public List<JobSeekerProfiles> getAllJobSeekerProfiles() {
		 return this.jobSeekerProfilesRepository.findAll();
	}

	@Override
	public JobSeekerProfiles updateJobSeekerprofile(JobSeekerProfiles jobSeekerProfiles , long jobseekerprofileid) {
		Optional<JobSeekerProfiles> job=this.jobSeekerProfilesRepository.findById(jobseekerprofileid);
		if(job.isPresent()) {
			JobSeekerProfiles jobp=job.get();
			jobp.setGender(jobSeekerProfiles.getGender());
			jobp.setDateOfBirth(jobSeekerProfiles.getDateOfBirth());
			jobp.setState(jobSeekerProfiles.getState());
			jobp.setDistric(jobSeekerProfiles.getDistric());
			jobp.setPincode(jobSeekerProfiles.getPincode());
			jobp.setAddress(jobSeekerProfiles.getAddress());
			jobp.setQualification(jobSeekerProfiles.getQualification());
			jobp.setPercentage(jobSeekerProfiles.getPercentage());
			jobp.setCvPath(jobSeekerProfiles.getCvPath());
			jobp.setImage(jobSeekerProfiles.getImage());
			jobp.setShortDescription(jobSeekerProfiles.getShortDescription());
			jobp.setCreatedAt(jobSeekerProfiles.getCreatedAt());
			jobp.setUpdatedAt(jobSeekerProfiles.getUpdatedAt());
			
			this.jobSeekerProfilesRepository.save(jobp);
		}
		return this.jobSeekerProfilesRepository.save(jobSeekerProfiles);

	}

	@Override
	public void deleteJobSeekerProfilesById(long jobseekerprofileid) {
		this.jobSeekerProfilesRepository.deleteById(jobseekerprofileid);
	}

}
