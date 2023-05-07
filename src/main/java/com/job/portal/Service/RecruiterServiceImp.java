package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.Recruiters;
import com.job.portal.Repository.RecruitersRepository;

@Service
public class RecruiterServiceImp implements RecruiterService{
   
	@Autowired
	private RecruitersRepository recruitersRepository;
	
	@Override
	public Recruiters createRecruiters(Recruiters recruiters) {
		return this.recruitersRepository.save(recruiters);
	}

	@Override
	public void deleteRecruiterById(long recruiterId) {
		this.recruitersRepository.deleteById(recruiterId);
		
	}

	@Override
	public void updateRecruiterById(Recruiters recruiters, long recruiterId) {

		Optional<Recruiters>ur=this.recruitersRepository.findById(recruiterId);
		
		if(ur.isPresent()) {
			Recruiters r=ur.get();
			r.setUsername(recruiters.getUsername());
			r.setCompanyname(recruiters.getCompanyname());
			r.setEmail(recruiters.getEmail());
			r.setRecruiterPhone(recruiters.getRecruiterPhone());
			r.setRecruiterName(recruiters.getRecruiterName());
			r.setRecruiterPassword(recruiters.getRecruiterPassword());
			r.setCreatedAt(recruiters.getCreatedAt());
			r.setUpdatedAt(recruiters.getUpdatedAt());
			
			this.recruitersRepository.save(r);
			}
		this.recruitersRepository.save(recruiters);
	}

	@Override
	public Optional<Recruiters> getRecruitersById(long recruiterId) {
		return this.recruitersRepository.findById(recruiterId);
	}

	@Override
	public List<Recruiters> getAllRecruiters() {
		return this.recruitersRepository.findAll();
	}
	

	
}
