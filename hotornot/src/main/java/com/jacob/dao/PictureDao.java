package com.jacob.dao;

import java.util.List;

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
		String sql = "SELECT id, user_id, pic_url, pic_name, description, brand, extension FROM picture WHERE id = ?";
		RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<Picture>(Picture.class);
		Picture picture = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return picture; 
    }
    
    public List<Picture> getAllPictures(){
		String sql = "SELECT id, user_id, pic_url, pic_name, description, brand, extension FROM picture";
    	RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<Picture>(Picture.class);
    	return this.jdbcTemplate.query(sql, rowMapper);
    }
    
    public Picture findPictureByPicUrl(String url) {
		String sql = "SELECT id, user_id, pic_url, pic_name, description, brand, extension FROM picture WHERE pic_url = ?";
		RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<Picture>(Picture.class);
		Picture picture = jdbcTemplate.queryForObject(sql, rowMapper, url);
		return picture; 
    }
    
    public Picture findPictureByPicName(String name) {
		String sql = "SELECT id, user_id, pic_url, pic_name, description, brand, extension FROM picture WHERE pic_name = ?";
		RowMapper<Picture> rowMapper = new BeanPropertyRowMapper<Picture>(Picture.class);
		Picture picture = jdbcTemplate.queryForObject(sql, rowMapper, name);
		return picture; 
    }
    
	 public int getANewId() {
		 String sql = "SELECT MAX(id) from picture";
		 int number = jdbcTemplate.queryForObject(sql, Integer.class);
 		 return (number + 1); 
//		 return 1; 
	 }
	 
	 public void savePicture(Picture picture) {
		 int tempId = getANewId(); 
		 System.out.println("our picture is savePicture (DAO) is: " + tempId);

		  String sql = "INSERT INTO picture (id, user_id, pic_url, pic_name, description, brand, extension) values (?, ?, ?, ?, ?, ?, ?)";
		  jdbcTemplate.update(sql, tempId, picture.getUser_id(), picture.getPic_url(), picture.getPic_name(), picture.getDescription(), picture.getBrand(), picture.getExtension());
		  System.out.println("Saved a picture");
	 }
    
    

}
