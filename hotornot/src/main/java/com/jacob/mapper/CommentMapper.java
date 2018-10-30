package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.Comment;

final class CommentMapper implements RowMapper<Comment> {

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Comment comment = new Comment(); 
		comment.setId(rs.getInt("id"));
		comment.setAuthor_id(rs.getInt("author_id"));
		comment.setPicture_id(rs.getInt("picture_id"));
		comment.setContent(rs.getString("content"));
		return comment; 
	}
	

}