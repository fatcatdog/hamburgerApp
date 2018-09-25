package com.jacob.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jacob.model.Profile;

final class ProfileMapper implements RowMapper<Profile> {
	@Override
	public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {

		Profile profile = new Profile();
		profile.setId((rs.getInt("id")));
		profile.setUser_id(rs.getInt("user_id"));
		profile.setUsername(rs.getString("username"));
		profile.setFavorite_food(rs.getString("favorite_food"));
		profile.setFavorite_food_brand(rs.getString("favorite_food_brand"));
		profile.setProfile_picture((rs.getString("profile_picture")));
		return profile;
	}
}
