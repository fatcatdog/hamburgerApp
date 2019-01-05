package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Website;

@Transactional
@Repository
public class websiteDao {

	private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public websiteDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }

    
    public int getANewId() {
		 String sql = "SELECT MAX(id) from Website";
		 Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
		 return (number + 1); 
//		 return 1; 
	 }
    
    public List<Website> findAll() {
    	String sql = "SELECT id, url, title FROM Website";
    	RowMapper<Website> rowMapper = new BeanPropertyRowMapper<Website>(Website.class);
    	return this.jdbcTemplate.query(sql, rowMapper);
    }

    public Website findById(Integer id) {        
        String sql = "SELECT id, url, title FROM Website WHERE id = ?";
		RowMapper<Website> rowMapper = new BeanPropertyRowMapper<Website>(Website.class);
		Website website = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return website; 
    }
    public void add(Website website) {   
        String sql = "INSERT INTO Website (id, title, url) values (?, ?, ?)";
		int tempId = getANewId();
	    jdbcTemplate.update(sql, tempId, website.getTitle(), website.getUrl());
    }
    public void update(Website website) {
    	        String sql = "UPDATE Website SET title=?, url=? WHERE id=?";
    	        jdbcTemplate.update(sql, website.getTitle(), website.getUrl(), website.getId());
    }
    
    public void delete(Integer id) {
    	String sql = "DELETE FROM Website WHERE id=?";
    	jdbcTemplate.update(sql, id);
    }

    
}
