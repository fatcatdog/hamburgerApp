package com.jacob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import com.jacob.model.Profile;
import com.jacob.model.User;
import com.jacob.service.ProfileService;
import com.jacob.service.UserService;

@Controller
//@RequestMapping("")
public class UserController {
	
	@Autowired
	 private UserService userService;
	
	@Autowired
	 private ProfileService profileService;
	 
 

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
	 
	 private User getCurrentAuthUser() {
		  //this variable will be used to get current user Authentication(where we can get there user id from) from spring security
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		 //getting user object from spring security Authentication object
		  User tempUser = userService.findUserByEmail(auth.getName());
		 return tempUser;
	 }
	 
	 @RequestMapping(value= {"/profile"}, method=RequestMethod.GET)
	 public ModelAndView profile() {
	  ModelAndView model = new ModelAndView();
	  
	  User currentUser = getCurrentAuthUser(); 
	  
	  if(profileService.isUserIdInDb(currentUser.getId())) {
		  System.out.println("Profile does exist");  

		  Profile profile = profileService.findProfileByUserId(currentUser.getId());	  
		  model.setViewName("user/profile");
		  
		  model.addObject("tempProfile", profile);

		  model.addObject("email", currentUser.getEmail());
		  model.addObject("first", currentUser.getFirstname());
		  model.addObject("last", currentUser.getLastname());
		  
	  } else {
		  System.out.println("Profile does not exist");  
		  
		  model.setViewName("user/edit");
		  
		  model.addObject("tempProfile", new Profile());
		  model.addObject("email", currentUser.getEmail());
		  model.addObject("first", currentUser.getFirstname());
		  model.addObject("last", currentUser.getLastname());
	  }	  
	  
	  return model;
	 }
	 
	 @RequestMapping(value= {"/edit/profile"}, method=RequestMethod.GET)
	 public ModelAndView editProfile() {
	  ModelAndView model = new ModelAndView();
	  model.setViewName("user/edit");
	  
	  User currentUser = getCurrentAuthUser(); 
	  
	  model.addObject("tempProfile", profileService.findProfileByUserId(currentUser.getId()));
	  model.addObject("email", currentUser.getEmail());
	  model.addObject("first", currentUser.getFirstname());
	  model.addObject("last", currentUser.getLastname());
	  
	  return model;
	 }
	 
	 @RequestMapping(value="/profile/saveProfile", method=RequestMethod.POST)
	 public ModelAndView save(@ModelAttribute("tempProfile") Profile profile) {
		 
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User tempAuthor = userService.findUserByEmail(auth.getName());
	  int tempAuthorId = tempAuthor.getId();
	  
	  profile.setUser_id(tempAuthorId);
	  profileService.saveProfile(profile);
	  return new ModelAndView("redirect:/home");
	 }
	 
	 
	 
	 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
	 public ModelAndView accessDenied() {
	  ModelAndView model = new ModelAndView();
	  model.setViewName("error/access_denied");
	  return model;
	 }
}
