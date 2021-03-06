package com.example.demo.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	 private UserService userService;

	  
	//presenting user login view
	 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
	 public ModelAndView login() {
	  ModelAndView model = new ModelAndView();
	  
	  model.setViewName("user/login");
	  return model;
	 }

	 
	//presenting user sign up view
	 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
	 public ModelAndView signup() {
	  ModelAndView model = new ModelAndView();
	  User user = new User();
	  model.addObject("user", user);
	  String confirmpassword = "";
	  model.addObject("confirmpassword", confirmpassword);
	  model.setViewName("user/signup");
	  
	  return model;
	 }
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        return matcher.find();
	}
	 
	//signing up, lots of messy auth code that doesnt really provide us enough validation, i plan on pulling in validations to simplify this section
	 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
	 public ModelAndView createUser(@Valid User user, String confirmPassword, String firstname, String lastname) {
		  ModelAndView model = new ModelAndView();
		  
		  User userExists;
		  
		  //part of our validation checks if user email is in db. if it is not, and not empty string, (we need to check if its a good email which we dont do yet), we proceed
		 
		  if(userService.checkIfEmailIsInDb(user.getEmail().toLowerCase())) {
			   userExists = userService.findUserByEmail(user.getEmail());		  
		  } else {
			   userExists = null; 
		  }
		  
		  //if user email is empty string, we supply error message and resubmit signup form
		   if ((user.getEmail().equals("")) || !validate(user.getEmail())) {
			  System.out.println("Confirm password empty");
			   model.addObject("msg", "Something went wrong. Please provide a valid email address and try again :)");
			   model.setViewName("user/signup");
			   return model; 
		  } 	
		   //if user exists we provide error message and resend signup form
		   else if(userExists != null) {
			   System.out.println("email already taken");
			   model.addObject("msg", "Email already taken! Please try again :)");
			   model.setViewName("user/signup");
			   return model; 
			  }  
		   //if passwords dont match we show error in signup page again
		   	else if (confirmPassword.equals("")) {
				  System.out.println("email error");
				   model.addObject("msg", "Password does not match confirm password. Please try again :)");
				   model.setViewName("user/signup");
				   return model; 
			  } else if (!user.getPassword().equals(confirmPassword)){
				  System.out.println("Confirm password empty");
				   model.addObject("msg", "Password does not match confirm password. Please try again :)");
				   model.setViewName("user/signup");
				   return model; 
			  } else if (firstname.trim().length() == 0 || lastname.trim().length() == 0) {
				  System.out.println("First name or last name empty");
				   model.addObject("msg", "First name or last name is empty. Please try again :)");
				   model.setViewName("user/signup");
				   return model; 
			  }
			  //else, we save user and supply login page
			  else {
			   userService.saveUser(user);	
			   model.setViewName("user/login");
			   model.addObject("msg", "User has been registered successfully! Please login :)");
			   model.addObject("user", new User());
			   return model; 
			  }	  
		 }

	 
	 
	 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
	 public ModelAndView accessDenied() {
	  ModelAndView model = new ModelAndView();
	  model.setViewName("error/access_denied");
	  return model;
	 }
}
