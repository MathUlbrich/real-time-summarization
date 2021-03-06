package com.univille.normalization.filters;

import java.text.Normalizer;

public class SpecialCharacterFilter implements Filter {

	private static final String SPECIAL_CHAR_REGEX = "[^a-zA-Z0-9]";

	@Override
	public String filter(String token) {
		return Normalizer.normalize(token, Normalizer.Form.NFD)
				.replaceAll(SPECIAL_CHAR_REGEX, "");
	}
	
}
