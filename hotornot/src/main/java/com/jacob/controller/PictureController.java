package com.jacob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Picture;
import com.jacob.model.Upvote;
import com.jacob.service.PictureService;
import com.jacob.service.UpvoteService;

@Controller
public class PictureController {
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	UpvoteService upvoteService;

	 @RequestMapping(value= {"/picture/{id}"}, method=RequestMethod.GET)
	 public ModelAndView viewPicture(@PathVariable(value = "id",  required =false) int id) {
		 System.out.println("Our pic id is: " + id);
		 ModelAndView model = new ModelAndView();
		 model.setViewName("picture/view_picture");
		 Picture ourPic = pictureService.findPictureById(id);
		 model.addObject("pic", ourPic);
		 
		 int upvoteCount = upvoteService.countUpvotes(id);
		 model.addObject("picUpvoteCount", upvoteCount);
		 
		 return model;
	 }
	 

	 @RequestMapping(value= {"/vote/{id}"}, method=RequestMethod.GET)
	 public ModelAndView upvoteMethod(@PathVariable(value = "id",  required =false) int id) {
								
		 int currentUserId = pictureService.getCurrentAuthUser().getId();
		 Picture currentPic = pictureService.findPictureById(id);
		 
		 if(!upvoteService.checkIfUserHasVotedOnThisPictureYet(currentUserId, currentPic.getId())) {
			Upvote tempUpvote = new Upvote();
			tempUpvote.setAuthor_id(currentUserId);
			tempUpvote.setPicture_id(currentPic.getId());
			upvoteService.saveUpvote(tempUpvote);
		 } else {
			 int tempUpvoteId = upvoteService.getUserUpvoteByUserIdAndPictureId(currentUserId, currentPic.getId());
			 upvoteService.deleteUpvote(tempUpvoteId);
		 }
		
		  return new ModelAndView("redirect:/picture/" + id);

	 }
	 
}
