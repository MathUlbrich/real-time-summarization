package com.univille;

import java.util.Arrays;
import java.util.Scanner;

import com.univille.subevent.listener.TweetRateListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class App {
	
	public static void main(String[] args) throws TwitterException {
		
		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.print("Informe os tópicos a serem escutados (separados por espaço): ");
			
			String[] topics = scanner.nextLine().split(" ");
			
			System.out.println("Ouvindo a API do twitter para os seguintes tópicos: " + Arrays.asList(topics));
		
			// Recupera a inst�ncia da Stream API do Twitter
			TwitterStream stream = TwitterStreamFactory.getSingleton();
			
			// Adiciona o Listener para monitorar o ciclo de vida da conex�o 
			//stream.addConnectionLifeCycleListener(new APIConnectionListener());
			
			// Adiciona o Listener da sumariza��o
			stream.addListener(new TweetRateListener());
			
			// Realiza o filtro de acordo com os t�picos escolhidos
			stream.filter(new FilterQuery(topics).language("pt"));
			
			// Aguarda por 30 minutos at� fechar a conex�o
			sleep(30);
	
			// Fecha a stream API
			stream.shutdown();
		
		}
	
	}
	
	private static void sleep(long timeInMinutes) {
		try {
			Thread.sleep(timeInMinutes * 60_000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
