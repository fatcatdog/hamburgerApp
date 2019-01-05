package com.jacob.controller;

import java.io.IOException;
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
    public ModelAndView uploadMultipartFile( @RequestParam("preuploadPicFile") MultipartFile file,  @RequestParam("picName") String picname,  @RequestParam("picDescription") String description, @RequestParam("picBrand") String brand) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
 
    	if (file == null ||picname.equals("") || description.equals("")|| brand.equals("") || extension.equals("")) {
    		return new ModelAndView("redirect:/home"); 
    	}

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

		pictureService.savePicture(tempPicture);

		return new ModelAndView("redirect:/home"); 
		
    }    
}