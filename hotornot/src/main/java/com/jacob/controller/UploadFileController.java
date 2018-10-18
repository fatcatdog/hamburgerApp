package com.jacob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.service.S3ServicesInterface;
 
@Controller
public class UploadFileController {
	
	@Autowired
	S3ServicesInterface s3Services;
	
	
//	@RequestParam("keyname") String keyName,
	
    @PostMapping("/food/upload")
    public ModelAndView uploadMultipartFile( @RequestParam("uploadfile") MultipartFile file) {
		String keyName = "hello world.jpg";
    	s3Services.uploadFile(keyName, file);
//		return "Upload Successfully. -> KeyName = " + keyName;
    	
		  return new ModelAndView("redirect:/home");
 
		
    }    
}