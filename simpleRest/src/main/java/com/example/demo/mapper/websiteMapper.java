package com.example.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.model.Website;

public class websiteMapper implements RowMapper<Website> {

	@Override
	public Website mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Website website = new Website(); 
		website.setId(rs.getInt("id"));
		website.setUrl(rs.getString("url"));
		website.setTitle(rs.getString("title"));
		
		return website; 
	}

}
