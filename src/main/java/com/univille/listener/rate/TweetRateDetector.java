package com.univille.listener.rate;

public class TweetRateDetector {

	/**
	 * Janela de tempo dos frames assim como utilizado por Arroyo e Cabrera (2014).
	 */
	private static final int DEFAULT_TIME_FRAME_IN_SECONDS = 60;
	
	private int learnedFrequency;
	private int timeFrameInSeconds;
	
	private static final TweetRateDetector INSTANCE = new TweetRateDetector();
	
	public static TweetRateDetector getInstance() {
		return INSTANCE;
	}
	
	public TweetRateDetector() {
		this(DEFAULT_TIME_FRAME_IN_SECONDS);
	}
	
	public TweetRateDetector(int timeFrameInSeconds) {
		this.timeFrameInSeconds = timeFrameInSeconds;
	}
	
	public synchronized int incrementLearn() {
		return learnedFrequency++;
	}
	
	
	
}
