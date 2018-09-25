package com.jacob.service;

import com.jacob.model.Profile;

public interface ProfileServiceInterface {

	 public boolean isUserIdInDb(int user_id);
	 public Profile findProfileById(int id);
	 public Profile findProfileByUserId(int user_id);
	 public void saveProfile(Profile profile);
	 public void updateProfile(Profile profile);
	 
}
