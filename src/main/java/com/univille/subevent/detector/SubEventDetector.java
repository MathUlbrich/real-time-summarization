package com.univille.subevent.detector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.stream.Collectors;

import com.univille.method.SummarizationMethod;
import com.univille.repository.DocumentRepository;

public class SubEventDetector {

	private static final Long SUB_EVENT_WINDOW_IN_MILLIS = 5_000l;
	private static final Double PERCENT_RATE_TO_NEW_SUBEVENT = 0.9d;
	
	private static final Object LOCK = new Object();
	private static final Timer TIMER = new Timer();
	
	private Map<String, Integer> tweetWindow = new LinkedHashMap<>();
	private Map<String, List<String>> tempDocuments = new HashMap<>();
	private String currentSession;

	
	public void addTweet(String tweet) {
		String session = getCurrentSession();
		
		if (session == null) {
			setSession(UUID.randomUUID().toString());
			session = getCurrentSession();
			System.out.println("Iniciando janela de sub-eventos para a sessão: " + session);
			
			TIMER.schedule(new TimerTask() {
				
				public void run() {
				
					double mean = getRateMean();
					String innerSession = getCurrentSession();
					int qtTweets = tempDocuments.get(innerSession).size();
					
					boolean shouldSummarize = false;
					
					System.out.println("Janela de identificação de sub-eventos finalizada... (" + innerSession + ")");
					
					if (qtTweets > (mean + (mean * PERCENT_RATE_TO_NEW_SUBEVENT))) {
						
						for (String doc : tempDocuments.get(innerSession)) {
							DocumentRepository.addDocument(innerSession, doc);
						}
						
						shouldSummarize = true;
						
					}
					
					tweetWindow.put(innerSession, qtTweets);
					setSession(null);
					
					if (shouldSummarize) {
						System.out.println("O sumário é => " + SummarizationMethod.HYBRID_TFIDF.algorithm().summarize(DocumentRepository.get(innerSession)));
					}
					
				}
				
			}, SUB_EVENT_WINDOW_IN_MILLIS);
			
		}
		
		addTempDocument(session, tweet);
		
	}
	
	private void addTempDocument(String session, String doc) {
		synchronized(LOCK) {
			List<String> docs = tempDocuments.computeIfAbsent(session, (v) -> { return new ArrayList<>(); });
			docs.add(doc);
		}
	}
	
	private String getCurrentSession() {
		synchronized(LOCK) {
			return currentSession;
		}
	}
	
	private void setSession(String session) {
		synchronized(LOCK) {
			this.currentSession = session;
		}
	}
	
	private double getRateMean() {
		int from = tweetWindow.size() < 5 ? 0 : tweetWindow.size() - 5;
		List<Integer> newList = tweetWindow.values().stream().collect(Collectors.toList()).subList(from, tweetWindow.size());
		int size = Math.min(newList.size() != 0 ? tweetWindow.size() : 1, 5);
		return newList.stream()
				.mapToInt(Integer::intValue)
				.sum() / ((size) * 1.0d);
	}
	
}
