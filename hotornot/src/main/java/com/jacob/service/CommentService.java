package com.jacob.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.CommentDao;
import com.jacob.model.Comment;
import com.jacob.model.User;

@Service("commentService")
public class CommentService implements CommentServiceInterface {

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private UserService userService;
	
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

	@Override 
	public List<String> getAllAuthorsOfCommentsByPicId(int id){
		List<String> authorNames = new ArrayList<String>();
		List<Comment> comments = getAllCommentsForAPicture(id);
		
		for(int i = 0; i < comments.size(); i++) {
			User user = userService.findUserById(comments.get(i).getAuthor_id());
			authorNames.add(user.getFirstname() + " " + user.getLastname());
		}
		
		return authorNames;
	}
}
