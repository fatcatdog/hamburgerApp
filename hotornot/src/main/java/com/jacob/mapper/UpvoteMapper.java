package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.Upvote;

final class UpvoteMapper implements RowMapper<Upvote> {

	@Override
	public Upvote mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Upvote upvote = new Upvote(); 
		upvote.setId(rs.getInt("id"));
		upvote.setAuthor_id(rs.getInt("author_id"));
		upvote.setPicture_id(rs.getInt("picture_id"));
		
		return upvote;
	}

}
