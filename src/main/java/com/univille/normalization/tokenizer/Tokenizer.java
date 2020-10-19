package com.univille.normalization.tokenizer;

import java.util.Arrays;
import java.util.List;

public final class Tokenizer {

	private static final String TOKENIZER_REGEX = "\\s+|,\\s*|\\.\\s*|\\-\\s*";
	
	public List<String> tokenize(String text) {
		return Arrays.asList(text.split(TOKENIZER_REGEX));
	}
	
}
