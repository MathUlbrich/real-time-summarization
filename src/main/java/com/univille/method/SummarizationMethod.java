package com.univille.method;

public enum SummarizationMethod {

	HYBRID_TFIDF {
		@Override
		public void summary(String tweet) {
			System.out.println("Summary with Hybrid TF-IDF of tweet => " + tweet);
		}
	},
	
	SUMBASIC {
		@Override
		public void summary(String tweet) {
			System.out.println("Summary with SumBasic of tweet => " + tweet);
		}
	};
	
	public abstract void summary(String tweet);
	
}
