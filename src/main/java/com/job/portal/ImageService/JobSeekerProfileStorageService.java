package com.job.portal.ImageService;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.job.portal.Entity.JobSeekerProfiles;
import com.job.portal.Repository.JobSeekerProfilesRepository;

@Service
public class JobSeekerProfileStorageService {

	@Autowired
	private JobSeekerProfilesRepository jobSeekerProfilesRepository;
	
	
	
	public String uploadImage(MultipartFile file) throws IOException
	{
		JobSeekerProfiles image=new JobSeekerProfiles();
        image.setImage(file.getContentType());	
        image.setImage(file.getOriginalFilename());
        image.setImage(file.getName());
		
		image.setCvPath(file.getContentType());
		image.setCvPath(file.getOriginalFilename());
        image.setCvPath(file.getName());
	    
		
		JobSeekerProfiles savedImage=jobSeekerProfilesRepository.save(image);
		if(savedImage!=null)
		{
			return "file uploaded successfully";
		}
		return null;
		
		
	}
	
	
	public String downloadImage(long jobseekerprofileId)
	{
		Optional<JobSeekerProfiles> image=this.jobSeekerProfilesRepository.findById(jobseekerprofileId);
		String ima=image.get().getImage();
		return ima;
		
	}
	
	public String downloadCvPath(long jobseekerprofileId)
	{
		Optional<JobSeekerProfiles> image=this.jobSeekerProfilesRepository.findById(jobseekerprofileId);
		String file=image.get().getCvPath();
		return file;
		
	}
	
}
