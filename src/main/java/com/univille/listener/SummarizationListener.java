package com.univille.listener;

import com.univille.method.SummarizationMethod;
import com.univille.model.MultiDocument;
import com.univille.normalization.Normalizer;
import com.univille.normalization.filters.LowercaseFilter;
import com.univille.normalization.filters.SpecialCharacterFilter;
import com.univille.normalization.filters.StemmingFilter;
import com.univille.normalization.filters.StopwordFilter;
import com.univille.storage.FileSummaryStorage;
import com.univille.storage.SummaryStorage;

import twitter4j.Status;
import twitter4j.StatusAdapter;

public class SummarizationListener extends StatusAdapter {

	@Override
	public void onStatus(Status status) {
		
		if (shouldIgnore(status)) {
			return;
		}
		
		Normalizer normalizer = new Normalizer();
		normalizer.addFilter(new LowercaseFilter());
		normalizer.addFilter(new StopwordFilter());
		normalizer.addFilter(new StemmingFilter());
		normalizer.addFilter(new SpecialCharacterFilter());
		
		System.out.println("Normalized => " + normalizer.normalize(status.getText()));
		
//		MultiDocument doc = new MultiDocument();
//		doc.add(status.getText());
		
//		SummaryStorage storage = new FileSummaryStorage();
//		
//		for (SummarizationMethod method : SummarizationMethod.values()) {
//			String summarized = method.algorithm().summarize(doc);
//			storage.store(summarized);
//		}
		
	}

	private boolean shouldIgnore(Status status) {
		return status.isRetweet();
	}

}
