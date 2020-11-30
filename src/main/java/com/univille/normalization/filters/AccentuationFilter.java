package com.univille.normalization.filters;

import java.text.Normalizer;

public class AccentuationFilter implements Filter {

	private static final String ASCII_REGEXP = "[^\\p{ASCII}]";
	
	@Override
	public String filter(String token) {
		return Normalizer.normalize(token, Normalizer.Form.NFD)
				.replaceAll(ASCII_REGEXP, "");
	}

}
