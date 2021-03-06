package com.jacob.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacob.model.Comment;

@Transactional
@Repository
public class CommentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
	 public int getANewId() {
		 String sql = "SELECT MAX(id) from comment";
		 Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
		 return (number + 1); 
//		 return 1; 
	 }
    
	public Comment getComment(int id) {
		String sql = "SELECT id, author_id, picture_id, content FROM comment WHERE id = ?";
   	 	RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
   	 	Comment comment = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return comment; 
	}
	
	public void deleteComment(int id) {
		String sql = "DELETE FROM comment WHERE id=?";
    	jdbcTemplate.update(sql, id);
	}
	
    public void saveComment(Comment comment) {
		String sql = "INSERT INTO comment (id, author_id, picture_id, content) values (?, ?, ?, ?)";
		int tempCommentId = getANewId();
	   jdbcTemplate.update(sql, tempCommentId, comment.getAuthor_id(), comment.getPicture_id(), comment.getContent());  
    }
    
	public List<Comment> getAllCommentsForAPicture(int id){
		String sql = "SELECT id, author_id, picture_id, content FROM comment WHERE picture_id = ?";
    	RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
  	   return this.jdbcTemplate.query(sql, rowMapper, id);
	}
	
	public List<Comment> getAllComments(){
		String sql = "SELECT id, author_id, picture_id, content FROM comment";
    	RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
  	   return this.jdbcTemplate.query(sql, rowMapper);
	}
	
}
