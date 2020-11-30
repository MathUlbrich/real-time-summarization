package com.univille.repository;

import java.util.HashMap;
import java.util.Map;
import com.univille.model.MultiDocument;

public class DocumentRepository {

	private static Map<String, MultiDocument> documents = new HashMap<>();
	
	public static void addDocument(String session, String document) {
		MultiDocument docs = documents.computeIfAbsent(session, (doc) -> new MultiDocument());
		docs.add(document);
	}
	
	public static MultiDocument get(String session) {
		return documents.get(session) != null ? documents.get(session) : new MultiDocument();
	}
	
}
