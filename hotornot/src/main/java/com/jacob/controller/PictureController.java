package com.jacob.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Comment;
import com.jacob.model.Picture;
import com.jacob.model.Upvote;
import com.jacob.model.User;
import com.jacob.service.CommentService;
import com.jacob.service.PictureService;
import com.jacob.service.UpvoteService;
import com.jacob.service.UserService;

@Controller
public class PictureController {
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UpvoteService upvoteService;
	
	@Autowired
	CommentService commentService;
	
	public List<Comment> getCommentsFromPicId(int id){
		return commentService.getAllCommentsForAPicture(id);
	}
	
//	public List<String> getAuthorNamesFromPicId(List<Comment> comments){
//		List<String> commentAuthors = new ArrayList<String>(); 
//		
//		for(int i = 0; i < comments.size(); i++) {
//			User author = userService.findUserById(comments.get(i).getAuthor_id());
//			commentAuthors.add(author.getFirstname());
//		}
//		return commentAuthors;
//	}

	 @RequestMapping(value= {"/picture/{id}"}, method=RequestMethod.GET)
	 public ModelAndView viewPicture(@PathVariable(value = "id",  required =false) int id) {
		 ModelAndView model = new ModelAndView();
		 model.setViewName("picture/picture");
		 Picture ourPic = pictureService.findPictureById(id);
		 
		 List<Comment> comments = getCommentsFromPicId(id);
		 
		 model.addObject("ourPicObj", ourPic);
		 model.addObject("picUrl", ourPic.getPic_url());
		 model.addObject("picExt", ourPic.getExtension());
		 model.addObject("comments", comments);
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
