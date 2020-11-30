package com.univille.subevent.listener;

import com.univille.subevent.detector.SubEventDetector;
import twitter4j.Status;
import twitter4j.StatusAdapter;

public class TweetRateListener extends StatusAdapter {

	private SubEventDetector detector = new SubEventDetector();
	
	@Override
	public void onStatus(Status status) {
		//System.out.println("Get twitter with text => " + status.getText());
		detector.addTweet(status.getText());
	}
	
}
