package com.jacob.service;

import java.util.List;

import com.jacob.model.Picture;
import com.jacob.model.Upvote;

public interface UpvoteServiceInterface {
	
	public void saveUpvote(Upvote upvote);
	
	public List<Upvote> getAllUpvotes();
	
	public List<Upvote> getAllUpvotesForAPicture(int pictureId);
	
	public List<Integer> getUpvoteCountsForEachPictureInAList(List<Picture> pictures);
		
	public Upvote getUpvote(int id);
	
	public void deleteUpvote(int id);
	
	public int countUpvotes(int pictureId); 
		
	public boolean checkIfUserHasVotedOnThisPictureYet(int userId, int pictureId);
	
	public int getUserUpvoteByUserIdAndPictureId(int userId, int pictureId);

}
