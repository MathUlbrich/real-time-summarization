package com.univille.method.tfidf;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TFIDFCalculator {
	
	public double tfIdf(String term, Collection<String> document, Collection<List<String>> documents) {
		return tf(term, document) * idf(term, documents);
	}
	
	private double idf(String term, Collection<List<String>> documents) {
		double count = 0;
		for (List<String> doc : documents) {
			for (String token : doc) {
				if (term.equalsIgnoreCase(token)) {
					count++;
				}
			}
		}
		return Math.log(documents.size() / count);
	}
	
	private double tf(String term, Collection<String> document) {
		double count = 0;
		for (String token : document) {
			if (term.equalsIgnoreCase(token)) {
				count++;
			}
		}
		return count / document.size();
	}
	
	public static void main(String[] args) {
		
		List<String> doc1 = Arrays.asList("teste", "de", "um", "amigo", "teste");
		List<String> doc2 = Arrays.asList("teste", "completo", "twitter", "neto", "lucas");
		List<String> doc3 = Arrays.asList("spoiler", "one", "piece", "teste", "kaidou", "surra", "vermelho", "luffy");
		List<List<String>> docs = Arrays.asList(doc1, doc2, doc3);
		
		System.out.println(new TFIDFCalculator().tfIdf("teste", doc1, docs));
		
		
	}
	
}
