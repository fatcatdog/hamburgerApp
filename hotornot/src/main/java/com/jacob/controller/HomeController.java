package com.jacob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Picture;
import com.jacob.service.PictureService;

@Controller
public class HomeController {
	
	@Autowired
	PictureService pictureService;

	//presenting user login view
	 @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
	 public ModelAndView home() {
	  ModelAndView model = new ModelAndView();
	  List<Picture> allOfOurPics = pictureService.getAllPictures(); 
	  model.setViewName("home");
	  model.addObject("sizeOfPicList", allOfOurPics.size());
	  model.addObject("pics", allOfOurPics);
	  
	  
	  for(int i = 0; i < allOfOurPics.size(); i++) {
		  System.out.println(allOfOurPics.get(i).getPic_url());
	  }
	  
	  return model;
	 }

}
