package com.job.portal.ImageService;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.job.portal.Entity.RecruiterProfiles;
import com.job.portal.Repository.RecruiterProfilesRepository;
import com.job.portal.Service.RecruiterProfileService;

@Service
public class RecruiterProfileStorageService {
	
	@Autowired
	private RecruiterProfilesRepository recruiterProfilesRepository;
	
	@Autowired
	private RecruiterProfileService recruiterProfileService;
	
	public String uploadImage(MultipartFile file) throws IOException
	{
		RecruiterProfiles image=new RecruiterProfiles();
		image.setCompany_logo(file.getContentType());
		image.setCompany_logo(file.getOriginalFilename());
		image.setCompany_logo(file.getOriginalFilename());
	    
		
		RecruiterProfiles savedImage=recruiterProfileService.saveRecruiterProfiles(image);
		if(savedImage!=null)
		{
			return "file uploaded successfully";
		}
		return null;
		
		
	}
	
	
	public String downloadImage(long recruiterprofileId)
	{
		Optional<RecruiterProfiles> image=this.recruiterProfilesRepository.findByrecruiterprofileId(recruiterprofileId);
		String file=image.get().getCompany_logo();
		return file;	}
	

}
