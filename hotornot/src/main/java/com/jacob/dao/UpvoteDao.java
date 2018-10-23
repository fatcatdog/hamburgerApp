package com.jacob.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jacob.model.Upvote;

@Transactional
@Repository
public class UpvoteDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UpvoteDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
    
    public int getANewId() {
		 String sql = "SELECT MAX(id) from upvote";
		 Integer number = jdbcTemplate.queryForObject(sql, Integer.class);
		 return (number + 1); 
//    	return 1; 
	 }
    
    public void saveUpvote(Upvote upvote) {
    	System.out.println("vote id = " + upvote.getId());
    	System.out.println("author id = " + upvote.getAuthor_id());
    	System.out.println("pic id = " + upvote.getPic_id());

		String sql = "INSERT INTO upvote (id, author_id, picture_id) values (?, ?, ?)";
		int tempUpvoteId = getANewId();
	   jdbcTemplate.update(sql, tempUpvoteId, upvote.getAuthor_id(), upvote.getPic_id());    	
    }
    
    public List<Upvote> getAllUpvotes(){
    	String sql = "SELECT id, author_id, picture_id FROM upvote";
    	RowMapper<Upvote> rowMapper = new BeanPropertyRowMapper<Upvote>(Upvote.class);
  	   return this.jdbcTemplate.query(sql, rowMapper);
	}
    
    public List<Upvote> getAllUpvotesForAPicture(int id){
		String sql = "SELECT id, author_id, picture_id FROM upvote WHERE picture_id = ? ";
    	RowMapper<Upvote> rowMapper = new BeanPropertyRowMapper<Upvote>(Upvote.class);
  	   return this.jdbcTemplate.query(sql, rowMapper, id);
	}
    
    public Upvote getUpvote(int id) {
		String sql = "SELECT id, author_id, picture_id FROM upvote WHERE id = ?";
   	 	RowMapper<Upvote> rowMapper = new BeanPropertyRowMapper<Upvote>(Upvote.class);
		 Upvote upvote = jdbcTemplate.queryForObject(sql, rowMapper, id);
		return upvote; 
	}
    
	public void deleteUpvote(int id) {
		String sql = "DELETE FROM upvote WHERE id=?";
    	jdbcTemplate.update(sql, id);
	}
	
	public int countUpvotes(int picId) {
		int count = 0; 
		
		List<Upvote> allVotes = getAllUpvotes();
		
		for(int i = 0; i < allVotes.size(); i++) {
			if(allVotes.get(i).getPic_id() == picId) {
				count++;
			}
		}
		return count; 
	} 
	
	public boolean checkIfUserHasVotedOnThisPictureYet(int userId, int pictureId) {
		boolean haveTheyVotedYet = false; 
		List<Upvote> allUpvotesWithThisPicture = getAllUpvotesForAPicture(pictureId);
		
		if (allUpvotesWithThisPicture.size() == 0) {
			haveTheyVotedYet = false; 
			return false;
		}
		
		
		for(int i = 0; i < allUpvotesWithThisPicture.size(); i++) { 
			if(allUpvotesWithThisPicture.get(i).getAuthor_id() == userId) {
				haveTheyVotedYet = true; 
				return true; 
			}
		}
		
		return haveTheyVotedYet;
	}
	
	public int getUserUpvoteByUserIdAndPictureId(int userId, int pictureId) {
		List<Upvote> allVotes = getAllUpvotes();
		List<Upvote> allUpvotesWithThisPicture = new ArrayList<Upvote>();

		for(int i = 0; i < allVotes.size(); i++) { 
			if (allVotes.get(i).getPic_id() == pictureId) {
				allUpvotesWithThisPicture.add(allVotes.get(i));
			}
		}
		
		for(int i = 0; i < allUpvotesWithThisPicture.size(); i++) { 
			if(allUpvotesWithThisPicture.get(i).getAuthor_id() == userId) {
				return allUpvotesWithThisPicture.get(i).getId();
			}
		}
		
		System.out.println("Something went very wront UpvoteDAO.getUserUpvoteByUserIdAndPictureId(int userId, int pictureId)");
		return -1; 
	}
	
}
