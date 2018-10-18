package com.jacob.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacob.model.Picture;

@Transactional
@Repository
public class PictureDao {
	
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PictureDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
    public Picture findPictureById(int id) {
		String sql = "SELECT id, user_id, pic_url, pic_name FROM picture WHERE id = ?";
		RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<Picture>(Picture.class);
		Picture picture = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return picture; 
    }
    
    public Picture findPictureByPicUrl(String url) {
		String sql = "SELECT id, user_id, pic_url, pic_name FROM picture WHERE pic_url = ?";
		RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<Picture>(Picture.class);
		Picture picture = jdbcTemplate.queryForObject(sql, rowMapper, url);
		return picture; 
    }
    
    public Picture findPictureByPicName(String name) {
		String sql = "SELECT id, user_id, pic_url, pic_name FROM picture WHERE pic_name = ?";
		RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<Picture>(Picture.class);
		Picture picture = jdbcTemplate.queryForObject(sql, rowMapper, name);
		return picture; 
    }
    
	 public int getANewId() {
//		 String sql = "SELECT MAX(id) from picture";
//		 int number = jdbcTemplate.queryForObject(sql, Integer.class);
// 		 return (number + 1); 
		 return 1; 
	 }
	 
	 public void savePicture(Picture picture) {
		 int tempId = getANewId(); 
		 System.out.println("our number is: " + tempId);

		  String sql = "INSERT INTO picture (id, user_id, pic_url, pic_name) values (?, ?, ?, ?)";
		  jdbcTemplate.update(sql, tempId, picture.getUser_id(), picture.getPic_url(), picture.getPic_name());
		  System.out.println("Saved a picture");
	 }
    
    

}