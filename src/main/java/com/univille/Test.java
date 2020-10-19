package com.univille;

import com.univille.normalization.Normalizer;
import com.univille.normalization.filters.LowercaseFilter;
import com.univille.normalization.filters.SpecialCharacterFilter;
import com.univille.normalization.filters.StemmingFilter;
import com.univille.normalization.filters.StopwordFilter;

public class Test {

	public static void main(String[] args) {
		
		Normalizer normalizer = new Normalizer();
		normalizer.addFilter(new LowercaseFilter());
		normalizer.addFilter(new StopwordFilter());
		normalizer.addFilter(new StemmingFilter());
		normalizer.addFilter(new SpecialCharacterFilter());
		
		System.out.println(normalizer.normalize("Um comunicado para toda a comunidade do sítio. Nossa comunicação está excelente! Felipe Neto é um otário!"));
		
	}
	
}
