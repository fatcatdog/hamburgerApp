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
	  List<List<Picture>> ourRows = new ArrayList<List<Picture>>();
	  
	  //We still need to send to send these rows of pics to view and this isn't working properly
	  for(int i = 0; i < allOfOurPics.size() / 3; i++) {
		  int counter = 0; 

		  List<Picture> aRowOf3Pics = new ArrayList<Picture>();
		  for(int j = counter; j < counter + 2; j++) {
			  aRowOf3Pics.add(allOfOurPics.get(counter));
		  }
		  ourRows.add(aRowOf3Pics);
		  counter = counter + 2;
	  }
	  
	  System.out.println( "ourRows size: " + ourRows.size());
	  
	  for(int i = 0; i < ourRows.size() / 3; i++) {
		 System.out.println("We have a row of 3 pics");
		 for(int j = 0; j < 3; j++) {
			 System.out.println("Pic id is: " + ourRows.get(i).get(j).getId());
		 }
		 
	  }
	  
	  model.setViewName("home");
	  model.addObject("sizeOfPicList", allOfOurPics.size());
	  model.addObject("pics", allOfOurPics);
	  model.addObject("counts", getAllUpvoteCounts(allOfOurPics));
	  
	  
	  for(int i = 0; i < allOfOurPics.size(); i++) {
		  System.out.println(allOfOurPics.get(i).getPic_url());
	  }
	  return model;
	 }

}
