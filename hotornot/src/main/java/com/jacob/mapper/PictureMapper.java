package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.Picture;

final class PictureMapper implements RowMapper<Picture> {

	@Override
	public Picture mapRow(ResultSet rs, int rowNum) throws SQLException {
		Picture picture = new Picture(); 
		picture.setId(rs.getInt("id"));
		picture.setUser_id(rs.getInt("user_id"));
		picture.setPic_url(rs.getString("pic_url"));
		picture.setPic_name(rs.getString("pic_name"));
		picture.setDescription(rs.getString("description"));
		picture.setBrand(rs.getString("brand"));
		picture.setBrand(rs.getString("extension"));
		return picture; 
	}


}
