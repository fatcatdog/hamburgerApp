package com.jacob.model;

public class Picture {
int id; 
int user_id; 
String pic_url;
String pic_name;


public Picture(int id, int user_id, String pic_url, String pic_name) {
	super();
	this.id = id;
	this.user_id = user_id;
	this.pic_url = pic_url;
	this.pic_name = pic_name;

} 


public Picture() {
	super();
}


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


public String getPic_url() {
	return pic_url;
}


public void setPic_url(String pic_url) {
	this.pic_url = pic_url;
}

public String getPic_name() {
	return pic_name;
}

public void setPic_name(String pic_name) {
	this.pic_name = pic_name;
}

}