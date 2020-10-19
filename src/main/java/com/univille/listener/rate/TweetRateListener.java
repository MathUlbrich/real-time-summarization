package com.univille.listener.rate;

import twitter4j.Status;
import twitter4j.StatusAdapter;

public class TweetRateListener extends StatusAdapter {

	private long lastReceivedTweetMillis;
	
	@Override
	public void onStatus(Status status) {
		
		TweetRateDetector detector = TweetRateDetector.getInstance();
		
		
	}
	
}
