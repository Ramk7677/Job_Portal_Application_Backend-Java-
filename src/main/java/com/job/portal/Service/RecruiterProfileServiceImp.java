package com.job.portal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.Entity.RecruiterProfiles;
import com.job.portal.Repository.RecruiterProfilesRepository;

@Service
public class RecruiterProfileServiceImp implements RecruiterProfileService {

	@Autowired
	private RecruiterProfilesRepository recruiterProfilesRepository;

	
	@Override
	public RecruiterProfiles saveRecruiterProfiles(RecruiterProfiles recruiterProfiles) {
		return this.recruiterProfilesRepository.save(recruiterProfiles);
	}

	@Override
	public Optional<RecruiterProfiles> getRecruiterProfilesById(long id) {
		return this.recruiterProfilesRepository.findById(id);
	}

	@Override
	public List<RecruiterProfiles> getAllRecruiterProfiles() {
		return this.recruiterProfilesRepository.findAll();
	}

	@Override
	public void updateRecruiterProfiles(RecruiterProfiles recruiterProfiles, long id) {
		Optional<RecruiterProfiles> recp = this.recruiterProfilesRepository.findById(id);
		if (recp.isPresent()) {
			RecruiterProfiles rp = recp.get();
			rp.setWebsite(recruiterProfiles.getWebsite());
			rp.setPhone(recruiterProfiles.getPhone());
			rp.setCompany_logo(recruiterProfiles.getCompany_logo());
			rp.setDescription(recruiterProfiles.getDescription());
			rp.setCompany_address(recruiterProfiles.getCompany_address());
			rp.setCreatedAt(recruiterProfiles.getCreatedAt());
			rp.setUpdated(recruiterProfiles.getUpdated());

			this.recruiterProfilesRepository.save(rp);
		}

	}

	@Override
	public void deleteRecruiterProfilesById(long id) {
		this.recruiterProfilesRepository.deleteById(id);
	}

	

}
