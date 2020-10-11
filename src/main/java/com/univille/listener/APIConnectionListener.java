package com.univille.listener;

import twitter4j.ConnectionLifeCycleListener;

public class APIConnectionListener implements ConnectionLifeCycleListener {

	@Override
	public void onConnect() {
		
		System.out.println("Ready to summarize...");
		
	}

	@Override
	public void onDisconnect() {
		
		System.out.println("Summarization collector is shutdown...");
		
	}

	@Override
	public void onCleanUp() {
		
		System.out.println("Summarization was clean up...");
		
	}

}
