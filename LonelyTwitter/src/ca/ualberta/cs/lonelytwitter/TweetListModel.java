package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

public class TweetListModel {
	private ArrayList<LonelyTweetModel> tweets;
	
	public TweetListModel(){
		super();
		tweets = new ArrayList<LonelyTweetModel>();
	}
	
	public void add (LonelyTweetModel lonelyTweetModel){
		if (tweets.contains(lonelyTweetModel)){
			throw new IllegalArgumentException();
		} else {
			tweets.add(lonelyTweetModel);
		}
	}
	
	public int getCount(){
		return tweets.size();
	}

	public LonelyTweetModel getItem(int i) {
		
		return tweets.get(i-1); //The tweetList is 1 index not 0 index, so we have to subtract 1
	}

	public  ArrayList<LonelyTweetModel> getTweets() {
		return tweets;
	}

	public boolean hasTweet(LonelyTweetModel aTweet) {
		if (tweets.contains(aTweet)){
			return true;
		} else {
			return false;
		}
	}


	
} 
