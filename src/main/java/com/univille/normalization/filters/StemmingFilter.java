package com.univille.normalization.filters;

import org.tartarus.snowball.SnowballProgram;
import org.tartarus.snowball.ext.PortugueseStemmer;

public class StemmingFilter implements Filter {

	private SnowballProgram stemmer;

	public StemmingFilter() {
		this(new PortugueseStemmer());
	}
	
	public StemmingFilter(SnowballProgram stemmer) {
		this.stemmer = stemmer;
	}
	
	@Override
	public String filter(String token) {
		stemmer.setCurrent(token);
		stemmer.stem();
		return stemmer.getCurrent();
	}

}
