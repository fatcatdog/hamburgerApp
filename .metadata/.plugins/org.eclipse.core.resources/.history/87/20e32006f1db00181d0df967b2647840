package com.jacob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.CommentDao;
import com.jacob.dao.PictureDao;
import com.jacob.model.Comment;

@Service("commentService")
public class CommentService implements CommentServiceInterface {

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public Comment getComment(int id) {
		// TODO Auto-generated method stub
		return commentDao.getComment(id);
	}

	@Override
	public void deleteComment(int id) {
		// TODO Auto-generated method stub
		commentDao.deleteComment(id);
		System.out.println("Deleted comment: " + id);
	}

	@Override
	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		commentDao.saveComment(comment);
		System.out.println("Saved a comment?");
	}

	@Override
	public List<Comment> getAllCommentsForAPicture(int id) {
		// TODO Auto-generated method stub
		List<Comment> tempCommentsForAPicture = commentDao.getAllCommentsForAPicture(id);
		return tempCommentsForAPicture;
	}

}
