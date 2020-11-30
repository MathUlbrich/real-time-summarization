package com.univille.method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.univille.model.MultiDocument;
import com.univille.normalization.Normalizer;
import com.univille.normalization.filters.LowercaseFilter;
import com.univille.normalization.filters.SpecialCharacterFilter;
import com.univille.normalization.filters.StemmingFilter;
import com.univille.normalization.filters.StopwordFilter;

public class HybridTFIDFSummarizer implements Summarizer {

	private static final int MINIMUM_THRESHOLD = 4;
	
	@Override
	public String summarize(MultiDocument doc) {

		List<List<String>> normalizedDocuments = doc.getDocuments().stream()
				.map(createNormalizer()::normalize)
				.collect(Collectors.toList());
		
		int wordsInTotal = wordsInTotal(normalizedDocuments);
		
		String idealSummary = null;
		Double score = 0.0;
		
		for (int i = 0; i < normalizedDocuments.size(); i++) {
			List<String> document = normalizedDocuments.get(i);
			double totalPoints = 0d;
			for (String token : document) {
				totalPoints += calculateHybridTfIdf(token, document, normalizedDocuments, wordsInTotal);
			}
			//System.out.println("Doc => " + doc.getDocuments().get(i));
			//System.out.println("Score => " + totalPoints);
			if (totalPoints > score) {
				score = totalPoints;
				idealSummary = doc.getDocuments().get(i);
			}
		}
		
		return idealSummary; // the document that's represents the most aproximated summary
	}

	private double calculateHybridTfIdf(String word, List<String> wordsInPost, List<List<String>> all, int wordsInTotal) {
		Map<List<String>, Integer> occurrences = findOccurrence(word, all);
		return (tf(occurrences, wordsInTotal) * Math.log(idf(all.size(), occurrences.keySet().size()))/Math.log(2)) / normalizationFactor(MINIMUM_THRESHOLD, wordsInPost.size());
		
	}
	
	private double tf(Map<List<String>, Integer> occurrences, int wordsInTotal) {
		int occurrenceOfWordInAllPosts = 0;
		for (Integer occurrence : occurrences.values()) {
			occurrenceOfWordInAllPosts += occurrence;
		}
		return occurrenceOfWordInAllPosts / ((double)wordsInTotal);
	}
	
	private double idf(int posts, int postsWhichWordOccurs) {
		return posts / ((double)postsWhichWordOccurs);
	}
	
	private int normalizationFactor(int minimumThreshold, int wordsInPostSize) {
		return Math.max(minimumThreshold, wordsInPostSize);
	}
	
	private int wordsInTotal(List<List<String>> all) {
		int count = 0;
		for (List<String> post : all) {
			count += post.size();
		}
		return count;
	}
	
	private Map<List<String>, Integer> findOccurrence(String word, List<List<String>> all) {
		Map<List<String>, Integer> occurrences = new HashMap<>();
		for (List<String> post : all) {
			int count = 0;
			for (String postWord : post) {
				if (postWord.equals(word)) {
					count++;
				}
			}
			if (count != 0) {
				occurrences.put(post, count);
			}
		}
		return occurrences;
		
	}
	
	private static Normalizer createNormalizer() {
		return new Normalizer()
			.addFilter(new LowercaseFilter())
			.addFilter(new StopwordFilter())
			.addFilter(new StemmingFilter())
			.addFilter(new SpecialCharacterFilter());
	}
	
}
