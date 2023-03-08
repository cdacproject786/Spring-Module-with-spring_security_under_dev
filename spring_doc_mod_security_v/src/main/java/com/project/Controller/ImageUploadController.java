package com.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

import com.project.Service.CloudUploadService;
import com.project.Service.FileUploadService;
import com.project.payload.FileResponse;

@RestController
public class ImageUploadController {

	@Value("${spring.servlet.multipart.location}")
	private String path;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private CloudUploadService cloudUploadService;
	
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image)
	{
		try
		{
			System.out.println("Entered controller");
			String fileName = this.fileUploadService.uploadImage(path, image);
			
			String uploadedUrl = this.cloudUploadService.uploadOnCloud(fileName);
			System.out.println(uploadedUrl);
			
			return new ResponseEntity<>(new FileResponse(fileName, "Image uploaded successfully"),HttpStatus.OK);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null, "Internal Server Error"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		
		
	}
}
