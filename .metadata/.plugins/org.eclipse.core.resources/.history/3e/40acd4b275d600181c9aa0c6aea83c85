package com.jacob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Picture;
import com.jacob.service.PictureService;

@Controller
public class PictureController {
	
	@Autowired
	PictureService pictureService;

	 @RequestMapping(value= {"/picture/{id}"}, method=RequestMethod.GET)
	 public ModelAndView viewPicture(@PathVariable(value = "id",  required =false) int id) {
		 ModelAndView model = new ModelAndView();
		 model.setViewName("picture/view_picture");
		 Picture ourPic = pictureService.findPictureById(id);
		 model.addObject("selectedPicture", ourPic);
		 return model;
	 }
}
