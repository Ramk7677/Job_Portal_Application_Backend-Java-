package com.job.portal.ImageController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.job.portal.ImageService.RecruiterProfileStorageService;

public class RecruiterProfileImgeController {

	@Autowired
	private RecruiterProfileStorageService recruiterProfileStorageService;
	
	@PostMapping("/recruiter/images")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException
	{
		String uploadImage=this.recruiterProfileStorageService.uploadImage(file);
		return new ResponseEntity<>(uploadImage,HttpStatus.CREATED);
	}
	
	@GetMapping("/recruiters/image")
	public ResponseEntity<?> downloadImage(@RequestParam("fileName")long recruiterprofileId)
	{
		String imageData=this.recruiterProfileStorageService.downloadImage(recruiterprofileId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.IMAGE_JPEG)
				.body(imageData);
		
	}
}

