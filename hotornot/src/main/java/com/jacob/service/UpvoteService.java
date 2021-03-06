package com.jacob.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacob.dao.UpvoteDao;
import com.jacob.model.Picture;
import com.jacob.model.Upvote;

@Service("upvoteService")
public class UpvoteService implements UpvoteServiceInterface {

	@Autowired
	private UpvoteDao upvoteDao;
	
	@Override
	public void saveUpvote(Upvote upvote) {
		// TODO Auto-generated method stub
		upvoteDao.saveUpvote(upvote);
	}

	@Override
	public List<Upvote> getAllUpvotes() {
		// TODO Auto-generated method stub
		return upvoteDao.getAllUpvotes();
	}

	@Override
	public List<Upvote> getAllUpvotesForAPicture(int pictureId) {
		// TODO Auto-generated method stub
		return upvoteDao.getAllUpvotesForAPicture(pictureId);
	}

	@Override
	public List<Integer> getUpvoteCountsForEachPictureInAList(List<Picture> pictures) {
		// TODO Auto-generated method stub
		List<Integer> ourUpvoteCounts = new ArrayList<Integer>();

		for(int i = 0; i < pictures.size(); i++) {
			ourUpvoteCounts.add(countUpvotes(pictures.get(i).getId()));
		}
		return ourUpvoteCounts;
	}

	@Override
	public Upvote getUpvote(int id) {
		// TODO Auto-generated method stub
		return upvoteDao.getUpvote(id);
	}

	@Override
	public void deleteUpvote(int id) {
		// TODO Auto-generated method stub
		upvoteDao.deleteUpvote(id);
	}

	@Override
	public int countUpvotes(int pictureId) {
		// TODO Auto-generated method stub
		return upvoteDao.countUpvotes(pictureId);
	}

	@Override
	public boolean checkIfUserHasVotedOnThisPictureYet(int userId, int pictureId) {
		// TODO Auto-generated method stub
		return upvoteDao.checkIfUserHasVotedOnThisPictureYet(userId, pictureId);
	}

	@Override
	public int getUserUpvoteByUserIdAndPictureId(int userId, int pictureId) {
		// TODO Auto-generated method stub
		return upvoteDao.getUserUpvoteByUserIdAndPictureId(userId, pictureId);
	}

}
