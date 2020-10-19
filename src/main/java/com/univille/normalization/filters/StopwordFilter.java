package com.univille.normalization.filters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StopwordFilter implements Filter {

	private static final Set<String> STOP_WORDS = getStopwords();
	
	@Override
	public String filter(String token) {
		return STOP_WORDS.contains(token) ? "" : token;
	}
	
	private static Set<String> getStopwords() {
		String[] stopwords = new String[] {
				"de", "a", "o", "que", "e", "do", "da", "em", "um", "para", "�",
				"com", "n�o", "uma", "os", "no", "se", "na", "por", "mais", "as",
				"dos", "como", "mas", "foi", "ao", "ele", "das", "tem", "�", "seu",
				"sua", "ou", "ser", "quando", "muito", "h�", "nos", "j�", "est�",
				"eu", "tamb�m", "s�", "pelo", "pela", "at�", "isso", "ela", "entre",
				"era", "depois", "sem", "mesmo", "aos", "ter", "seus", "quem", "nas",
				"me", "esse", "eles", "est�o", "voc�", "tinha", "foram", "essa", "num",
				"nem", "suas", "meu", "�s", "minha", "t�m", "numa", "pelos", "elas", "havia",
				"seja", "qual", "ser�", "n�s", "tenho", "lhe", "deles", "essas", "esses", "pelas",
				"este", "fosse", "dele", "tu", "te", "voc�s", "vos", "lhes", "meus", "minhas", "teu",
				"tua", "teus", "tuas", "nosso", "nossa", "nossos", "nossas", "dela", "delas", "esta",
				"estes", "estas", "aquele", "aquela", "aqueles", "aquelas", "isto", "aquilo", "estou",
				"est�", "estamos", "est�o", "estive", "esteve", "estivemos", "estiveram", "estava",
				"est�vamos", "estavam", "estivera", "estiv�ramos", "esteja", "estejamos", "estejam",
				"estivesse", "estiv�ssemos", "estivessem", "estiver", "estivermos", "estiverem", "hei",
				"h�", "havemos", "h�o", "houve", "houvemos", "houveram", "houvera", "houv�ramos", "haja",
				"hajamos", "hajam", "houvesse", "houv�ssemos", "houvessem", "houver", "houvermos", "houverem",
				"houverei", "houver�", "houveremos", "houver�o", "houveria", "houver�amos", "houveriam", "sou",
				"somos", "s�o", "era", "�ramos", "eram", "fui", "foi", "fomos", "foram", "fora", "f�ramos", "seja",
				"sejamos", "sejam", "fosse", "f�ssemos", "fossem", "for", "formos", "forem", "serei", "ser�", "seremos",
				"ser�o", "seria", "ser�amos", "seriam", "tenho", "tem", "temos", "t�m", "tinha", "t�nhamos", "tinham", "tive",
				"teve", "tivemos", "tiveram", "tivera", "tiv�ramos", "tenha", "tenhamos", "tenham", "tivesse", "tiv�ssemos",
				"tivessem", "tiver", "tivermos", "tiverem", "terei", "ter�", "teremos", "ter�o", "teria", "ter�amos", "teriam"
		};
		return new HashSet<>(Arrays.asList(stopwords));
	}

}