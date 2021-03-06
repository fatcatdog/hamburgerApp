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
//    	System.out.println("vote id = " + upvote.getId());
//    	System.out.println("author id = " + upvote.getAuthor_id());
//    	System.out.println("pic id = " + upvote.getPicture_id());

		String sql = "INSERT INTO upvote (id, author_id, picture_id) values (?, ?, ?)";
		int tempUpvoteId = getANewId();
	   jdbcTemplate.update(sql, tempUpvoteId, upvote.getAuthor_id(), upvote.getPicture_id());
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

	public int countUpvotes(int tempPicId) {
		int count = 0;

		List<Upvote> allVotes = getAllUpvotes();

		for(int i = 0; i < allVotes.size(); i++) {

			// System.out.println("upvote picid = " + allVotes.get(i).getPicture_id());
			// System.out.println("upvote id = " + allVotes.get(i).getId());
			// System.out.println("upvote author = " + allVotes.get(i).getAuthor_id());

			if(allVotes.get(i).getPicture_id() == tempPicId) {
				count++;
			}
		}
		System.out.println(count);
		return count;

	}

	public boolean checkIfUserHasVotedOnThisPictureYet(int userId, int pictureId) {
		boolean haveTheyVotedYet = false;
		List<Upvote> allUpvotesWithThisPicture = getAllUpvotesForAPicture(pictureId);
		System.out.println("User id is = " + userId + " Pic id is = " + pictureId);

		System.out.println("First loop to get all pic votes");
		if (allUpvotesWithThisPicture.size() == 0) {
			haveTheyVotedYet = false;
			return false;
		}

		System.out.println();
		System.out.println("Second loop to get all pic votes with user id");

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
			if (allVotes.get(i).getPicture_id() == pictureId) {
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
