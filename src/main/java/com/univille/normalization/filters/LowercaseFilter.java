package com.univille.normalization.filters;

public class LowercaseFilter implements Filter {

	@Override
	public String filter(String token) {
		return token.toLowerCase();
	}
	
}
