package com.univille;

import com.univille.listener.APIConnectionListener;
import com.univille.listener.SummarizationListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class App {
	
	public static void main(String[] args) throws TwitterException {
		
		// Recupera a instância da Stream API do Twitter
		TwitterStream stream = TwitterStreamFactory.getSingleton();
		
		// Adiciona o Listener para monitorar o ciclo de vida da conexão 
		stream.addConnectionLifeCycleListener(new APIConnectionListener());
		
		// Adiciona o Listener da sumarização
		stream.addListener(new SummarizationListener());
		
		// Realiza o filtro de acordo com os tópicos escolhidos
		stream.filter(new FilterQuery(topics()).language("pt"));
		
		// Aguarda por 30 minutos até fechar a conexão
		sleep(30);

		// Fecha a stream API
		stream.shutdown();
	
	}
	
	private static String[] topics() {
		return new String[] { 
			"noronha"
		};
	}
	
	private static void sleep(long timeInMinutes) {
		try {
			Thread.sleep(timeInMinutes * 60_000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
