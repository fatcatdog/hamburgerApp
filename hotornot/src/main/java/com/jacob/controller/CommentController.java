package com.jacob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jacob.model.Comment;
import com.jacob.model.User;
import com.jacob.service.CommentService;
import com.jacob.service.PictureService;
import com.jacob.service.UserService;

@Controller
@RequestMapping("comment")
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
	 
	 @RequestMapping(value="save", method=RequestMethod.POST)
	 public ModelAndView save(@ModelAttribute("picId") String picId, @ModelAttribute("commentContent") String commentContent) {
		 System.out.println("save comment called in comment controller");
		 System.out.println("pic id is " + picId);

		 //getting auth information 
		 User tempAuthor = getCurrentAuthUser();

		 //check if comment is empty string if so just reshow blog
		 if(commentContent.trim().length()==0) {
			  return new ModelAndView("redirect:/picture/" + picId);
		 }
		 
		 //saving comment 
		 Comment tempComment = new Comment(); 
		 
		 tempComment.setAuthor_id(tempAuthor.getId());
		 tempComment.setPicture_id(Integer.parseInt(picId));
		 tempComment.setContent(commentContent);
		 commentService.saveComment(tempComment);
			 
	  return new ModelAndView("redirect:/picture/" + picId);
	  
	 }
	
}
