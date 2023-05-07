package com.job.portal.ImageController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.job.portal.ImageService.JobSeekerProfileStorageService;

@RestController
@RequestMapping("/api/job/")
@CrossOrigin(origins = "http://localhost:4200")
public class JobProfileImageController {
	
	@Autowired
	private JobSeekerProfileStorageService jobSeekerProfileStorageService;

	@PostMapping("/user/images")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException
	{
		String uploadImage=this.jobSeekerProfileStorageService.uploadImage(file);
		return new ResponseEntity<>(uploadImage,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/image")
	public ResponseEntity<?> downloadImage(@RequestParam("fileName")long jobseekerprofileId)
	{
		String imageData=this.jobSeekerProfileStorageService.downloadImage(jobseekerprofileId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.IMAGE_JPEG)
				.body(imageData);
	}
}
