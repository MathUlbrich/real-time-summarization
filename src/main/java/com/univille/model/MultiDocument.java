package com.univille.model;

import java.util.ArrayList;
import java.util.List;

public class MultiDocument {

	private final List<String> documents;
	
	public MultiDocument() {
		documents = new ArrayList<>();
	}
	
	public void add(String document) {
		documents.add(document);
	}
	
	public List<String> getDocuments() {
		return documents;
	}
	
}
