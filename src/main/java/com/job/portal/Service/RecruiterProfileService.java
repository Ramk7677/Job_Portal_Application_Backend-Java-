package com.job.portal.Service;

import java.util.List;
import java.util.Optional;


import com.job.portal.Entity.RecruiterProfiles;


public interface RecruiterProfileService {
	
	
	RecruiterProfiles saveRecruiterProfiles(RecruiterProfiles recruiterProfiles); 
	
	public Optional<RecruiterProfiles> getRecruiterProfilesById(long id);
	
	public List<RecruiterProfiles> getAllRecruiterProfiles();
	
	void updateRecruiterProfiles(RecruiterProfiles recruiterProfiles,long id);
	
	void deleteRecruiterProfilesById(long id);
	
	
}
