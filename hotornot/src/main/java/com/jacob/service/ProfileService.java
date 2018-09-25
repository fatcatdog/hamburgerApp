package com.jacob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.ProfileDao;
import com.jacob.model.Profile;

@Service("profileService")
public class ProfileService implements ProfileServiceInterface {

	@Autowired
	private ProfileDao profileDao;
	
	@Override
	public boolean isUserIdInDb(int user_id) {
		return profileDao.isUserIdInDb(user_id);
	}
	
	@Override
	public Profile findProfileById(int id) {
		return profileDao.findProfileById(id);
	}

	@Override
	public Profile findProfileByUserId(int user_id) {
		return profileDao.findProfileByUserId(user_id);
	}
	
	@Override
	public void saveProfile(Profile profile) {
		profileDao.saveProfile(profile);
	}

	@Override
	public void updateProfile(Profile profile) {
		profileDao.updateProfile(profile);
	}
}