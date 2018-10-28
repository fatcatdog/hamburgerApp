package com.jacob.model;

public class Upvote {
int id; 
int author_id; 
int picture_id;

public Upvote(int id, int author_id, int picture_id) {
	super();
	this.id = id;
	this.author_id = author_id;
	this.picture_id = picture_id;
}

public Upvote() {
	super();
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getAuthor_id() {
	return author_id;
}
public void setAuthor_id(int author_id) {
	this.author_id = author_id;
}
public int getPicture_id() {
	return picture_id;
}
public void setPicture_id(int picture_id) {
	this.picture_id = picture_id;
} 


}
