package com.jacob.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Picture;
import com.jacob.service.PictureService;
import com.jacob.service.UpvoteService;

@Controller
public class HomeController {
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	UpvoteService upvoteService;
	
	public List<Integer> getAllUpvoteCounts(List<Picture> pics) {
		List<Integer> listOfCounts = new ArrayList<Integer>(); 
		
		for(int i = 0; i < pics.size(); i++) {
			listOfCounts.add(upvoteService.countUpvotes(pics.get(i).getId())); 
		}
		
		return listOfCounts; 
	}

	//presenting user login view
	 @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
	 public ModelAndView home() {
	  ModelAndView model = new ModelAndView();
	  List<Picture> allOfOurPics = pictureService.getAllPictures(); 
	  
	  model.setViewName("home");
	  model.addObject("sizeOfPicList", allOfOurPics.size());
	  model.addObject("pics", allOfOurPics);
	  model.addObject("counts", getAllUpvoteCounts(allOfOurPics));
	  
	  return model;
	 }
	 
	 

}
