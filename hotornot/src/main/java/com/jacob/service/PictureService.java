package com.jacob.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jacob.dao.PictureDao;
import com.jacob.model.Picture;
import com.jacob.model.User;

@Service("pictureService")
public class PictureService implements PictureServiceInterface {

	@Autowired
	private PictureDao pictureDao;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Picture findPictureById(int id) {
		// TODO Auto-generated method stub
		return pictureDao.findPictureById(id);
	}

	@Override
	public void savePicture(Picture picture) {
		// TODO Auto-generated method stub
		pictureDao.savePicture(picture);
	}

	@Override
	public Picture findPictureByPicUrl(String url) {
		// TODO Auto-generated method stub
		return pictureDao.findPictureByPicUrl(url);
	}
	
	 private User getCurrentAuthUser() {
		  //this variable will be used to get current user Authentication(where we can get there user id from) from spring security
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		 //getting user object from spring security Authentication object
		  User tempUser = userService.findUserByEmail(auth.getName());
		 return tempUser;
	 }
	
    public String generateKeynameForPic(String pic_name) {
    	String time = Instant.now().toString();
    	String tempKey = time + "_" + pic_name;
    	return tempKey;
    }


}