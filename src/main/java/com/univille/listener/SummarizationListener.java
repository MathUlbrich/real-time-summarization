package com.univille.listener;

import com.univille.method.SummarizationMethod;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class SummarizationListener implements StatusListener {

	public void onException(Exception ex) {
		// TODO Implementar
	}

	public void onStatus(Status status) {
		if (shouldIgnore(status)) {
			return;
		}
		
		for (SummarizationMethod method : SummarizationMethod.values()) {
			method.summary(status.getText());
		}
	}

	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		// Método não implementado
	}

	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		// Método não implementado
	}

	public void onScrubGeo(long userId, long upToStatusId) {
		// Método não implementado
	}

	public void onStallWarning(StallWarning warning) {
		// Método não implementado
	}
	
	private boolean shouldIgnore(Status status) {
		return status.isRetweet();
	}

}
