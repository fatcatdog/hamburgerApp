package com.jacob.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Picture;
import com.jacob.service.PictureService;
import com.jacob.service.S3ServicesInterface;
 
@Controller
public class UploadFileController {
	
	@Autowired
	S3ServicesInterface s3Services;
	
	@Autowired
	PictureService pictureService;
		
    @PostMapping("/food/upload")
    public ModelAndView uploadMultipartFile( @RequestParam("preuploadPicFile") MultipartFile file,  @RequestParam("picName") String picname,  @RequestParam("picDescription") String description, @RequestParam("picBrand") String brand) {
		    	
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		Long time =  (System.currentTimeMillis());
	  	String keyName = time.toString() + picname;
		    	
		Picture tempPicture = new Picture(); 
		
		tempPicture.setUser_id(pictureService.getCurrentAuthUser().getId());

		tempPicture.setPic_url(keyName);

		tempPicture.setPic_name(picname);
		
		tempPicture.setDescription(description);
		
		tempPicture.setBrand(brand);
		
		tempPicture.setExtension(extension);

		s3Services.uploadFile(keyName + "." + extension, file);
		System.out.println("uploaded pic to s3");

		pictureService.savePicture(tempPicture);
		System.out.println("saved pic info to DB");

		return new ModelAndView("redirect:/home"); 
		
    }    
}