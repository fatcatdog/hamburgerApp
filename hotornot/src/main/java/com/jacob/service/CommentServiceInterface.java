package com.jacob.service;

import java.util.List;

import com.jacob.model.Comment;

public interface CommentServiceInterface {
	public Comment getComment(int id);
	public void deleteComment(int id);
	public void saveComment(Comment comment);
	public List<Comment> getAllCommentsForAPicture(int id);
	public List<String> getAllAuthorsOfCommentsByPicId(int id);
}
