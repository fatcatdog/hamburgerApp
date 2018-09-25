package com.jacob.model;

public class Profile {
	
	private int id; 
	private int user_id; 
	private String username; 
	private String favorite_food; 
	private String favorite_food_brand; 
	private String profile_picture;
	
	public Profile(int id, int user_id, String username, String favorite_food, String favorite_food_brand,
			String profile_picture) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.username = username;
		this.favorite_food = favorite_food;
		this.favorite_food_brand = favorite_food_brand;
		this.profile_picture = profile_picture;
	}
	
	public Profile() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFavorite_food() {
		return favorite_food;
	}
	public void setFavorite_food(String favorite_food) {
		this.favorite_food = favorite_food;
	}
	public String getFavorite_food_brand() {
		return favorite_food_brand;
	}
	public void setFavorite_food_brand(String favorite_food_brand) {
		this.favorite_food_brand = favorite_food_brand;
	}
	public String getProfile_picture() {
		return profile_picture;
	}
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	} 
	
	

}
