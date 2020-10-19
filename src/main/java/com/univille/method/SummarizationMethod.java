package com.univille.method;

public enum SummarizationMethod {

	HYBRID_TFIDF {
		@Override
		public Summarizer algorithm() {
			return new HybridTFIDFSummarizer();
		}
	};
	
	public abstract Summarizer algorithm();
	
}
