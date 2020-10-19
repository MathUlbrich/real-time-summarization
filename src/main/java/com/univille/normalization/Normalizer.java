package com.univille.normalization;

import java.util.ArrayList;
import java.util.List;
import com.univille.normalization.filters.Filter;
import com.univille.normalization.tokenizer.Tokenizer;

public class Normalizer {

	private final List<Filter> filters = new ArrayList<>();
	private final Tokenizer tokenizer = new Tokenizer();
	
	public Normalizer addFilter(Filter filter) {
		filters.add(filter);
		return this;
	}
	
	public List<String> normalize(String document) {
		List<String> normalized = new ArrayList<>();
		for (String token : tokenizer.tokenize(document)) {
			String transformed = transformToken(token);
			if (!transformed.isEmpty()) {
				normalized.add(transformed);
			}
		}
		return normalized;
	}
	
	private String transformToken(String token) {
		for (Filter filter : filters) {
			token = filter.filter(token);
		}
		return token;
	}
	
}
