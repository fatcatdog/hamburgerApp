package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;


@Transactional
@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
	 public User findUserByEmail(String email) {
			String sql = "SELECT id, email, firstname, lastname, password, active FROM user WHERE email = ?";
	    	 RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
			 User user = jdbcTemplate.queryForObject(sql, rowMapper, email);
			return user; 
	 }
	 
	 public boolean checkIfEmailIsInDb(String email) {
		 	boolean result = false; 
			String sql = "SELECT count(*) FROM user WHERE email = ?";
			int count = jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
		    if (count > 0) {
		      result = true;
		    }  
		 return result; 
	 }

	 public User findUserById(int id) {
			String sql = "SELECT id, email, firstname, lastname, password, active FROM user WHERE id = ?";
	    	 RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
			 User user = jdbcTemplate.queryForObject(sql, rowMapper, id);
			return user; 
	 }
	  
	 public int getANewId() {
		 String sql = "SELECT MAX(id) from user";
		 int number = jdbcTemplate.queryForObject(sql, Integer.class);
// 		 return (number + 1); 
		 return 1; 
	 }
	 
	 public void saveUser(User user) {
		 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		 user.setActive(1);
		 int tempId = getANewId(); 
		 System.out.println("our number is: " + tempId);
		  String sql = "INSERT INTO user (id, email, firstname, lastname, password, active) values (?, ?, ?, ?, ?, ?)";
		  jdbcTemplate.update(sql, tempId, user.getEmail(), user.getFirstname(), user.getLastname(), user.getPassword(),  user.getActive());
//		  RowMapper<Role> rowMapper = new BeanPropertyRowMapper<Role>(Role.class);
		  String roleSql = "INSERT INTO user_role (user_id, role_id) values (?, ?)";
		  jdbcTemplate.update(roleSql, tempId, 1);
	 }
}
