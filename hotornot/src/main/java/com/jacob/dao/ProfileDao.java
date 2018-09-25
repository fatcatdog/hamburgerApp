package com.jacob.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacob.model.Profile;

@Transactional
@Repository
public class ProfileDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProfileDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
	 public int getANewId() {
		 String sql = "SELECT MAX(id) from profile";
		 int number = jdbcTemplate.queryForObject(sql, Integer.class);
		 return (number + 1); 
	 }
	 
	 public boolean isUserIdInDb(int user_id) {
		 	String sql = "SELECT count(*) FROM profile WHERE user_id = ?";
		    boolean result = false;
		    int count = jdbcTemplate.queryForObject(sql, new Object[] { user_id }, Integer.class);
		    if (count > 0) {
		      result = true;
		    }
		    return result;
	 }
	 
	 public Profile findProfileById(int id) {
			String sql = "SELECT id, user_id, username, favorite_food, favorite_food_brand, profile_picture FROM profile WHERE id = ?";
	    	 RowMapper<Profile> rowMapper = new BeanPropertyRowMapper<Profile>(Profile.class);
	    	 Profile profile = jdbcTemplate.queryForObject(sql, rowMapper, id);
			return profile; 
	 }
	 
	 public Profile findProfileByUserId(int user_id) {
			String sql = "SELECT id, user_id, username, favorite_food, favorite_food_brand, profile_picture FROM profile WHERE user_id = ?";
	    	 RowMapper<Profile> rowMapper = new BeanPropertyRowMapper<Profile>(Profile.class);
	    	 Profile profile = jdbcTemplate.queryForObject(sql, rowMapper, user_id);
			return profile; 
	 }
	 
	 public void saveProfile(Profile profile) {
		 int tempId = getANewId(); 
		  String sql = "INSERT INTO profile (id, user_id, username, favorite_food, favorite_food_brand, profile_picture) values (?, ?, ?, ?, ?, ?)";
		  jdbcTemplate.update(sql, tempId, profile.getUser_id(), profile.getUsername(), profile.getFavorite_food(), profile.getFavorite_food_brand(), profile.getProfile_picture());
	 }
	 
	 public void updateProfile(Profile profile) {
		  String sql = "INSERT INTO profile (id, user_id, username, favorite_food, favorite_food_brand, profile_picture) values (?, ?, ?, ?, ?, ?)";
		  jdbcTemplate.update(sql, profile.getId(), profile.getUser_id(), profile.getUsername(), profile.getFavorite_food(), profile.getFavorite_food_brand(), profile.getProfile_picture());
	 }
	 
	 
	 
	 
}