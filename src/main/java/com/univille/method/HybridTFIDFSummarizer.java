package com.univille.method;

import java.util.List;
import java.util.stream.Collectors;
import com.univille.method.tfidf.TFIDFCalculator;
import com.univille.model.MultiDocument;
import com.univille.normalization.Normalizer;
import com.univille.normalization.filters.LowercaseFilter;
import com.univille.normalization.filters.SpecialCharacterFilter;
import com.univille.normalization.filters.StemmingFilter;
import com.univille.normalization.filters.StopwordFilter;

public class HybridTFIDFSummarizer implements Summarizer {

	@Override
	public String summarize(MultiDocument doc) {

		List<List<String>> normalizedDocuments = doc.getDocuments().stream()
				.map(createNormalizer()::normalize)
				.collect(Collectors.toList());
		
		TFIDFCalculator calculator = new TFIDFCalculator();
		
		for (List<String> document : normalizedDocuments) {
			for (String token : document) {
				calculator.tfIdf(token, document, normalizedDocuments);
			}
		}
		
		return null;
	}
	
	private Normalizer createNormalizer() {
		return new Normalizer()
			.addFilter(new LowercaseFilter())
			.addFilter(new StopwordFilter())
			.addFilter(new StemmingFilter())
			.addFilter(new SpecialCharacterFilter());
	}
	
}
