package com.jacob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Comment;
import com.jacob.model.User;
import com.jacob.service.CommentService;
import com.jacob.service.PictureService;
import com.jacob.service.UserService;

@Controller
@RequestMapping("")
public class CommentController {

	@Autowired
	PictureService pictureService;
	
	 @Autowired
	 private UserService userService;
	
	@Autowired
	CommentService commentService;
	
	 private User getCurrentAuthUser() {
		  //this variable will be used to get current user Authentication(where we can get there user id from) from spring security 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 //getting user object from spring security Authentication object 
		  User tempUser = userService.findUserByEmail(auth.getName());
		 return tempUser;
	 }
	 
//	 @RequestMapping(value="saveComment/{id}", method=RequestMethod.POST)
//	 public ModelAndView save(@PathVariable(value = "id", required =false) int id, @ModelAttribute("tempComment") Comment comment) {

	 
	 @RequestMapping(value="saveComment/{id}", method=RequestMethod.POST)
	 public ModelAndView save(@PathVariable(value = "id", required = false) int id, @ModelAttribute("content") Comment comment) {
		
		 //getting auth information 
		 User tempAuthor = getCurrentAuthUser();

		 //check if comment is empty string if so just reshow blog
		 if(comment.getContent().trim().length()==0) {
			  return new ModelAndView("redirect:/picture/" + id);
		 }
		 
		 //saving comment 
		 comment.setAuthor_id(tempAuthor.getId());
		 comment.setPicture_id((id));
		 commentService.saveComment(comment);
			 return new ModelAndView("redirect:/picture/" + (id));
	  
	 }
	
}
