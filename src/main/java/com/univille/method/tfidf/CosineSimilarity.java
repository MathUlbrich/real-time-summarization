package com.univille.method.tfidf;

public class CosineSimilarity {

	public double similarity(double[] scoreDoc1, double[] scoreDoc2) {
		double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (int i = 0; i < scoreDoc1.length; i++) //docVector1 and docVector2 must be of same length
        {
            dotProduct += scoreDoc1[i] * scoreDoc2[i];  //a.b
            magnitude1 += Math.pow(scoreDoc1[i], 2);  //(a^2)
            magnitude2 += Math.pow(scoreDoc1[i], 2); //(b^2)
        }

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 | magnitude2 != 0.0)
        {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } 
        else 
        {
            return 0.0;
        }
        return cosineSimilarity;
	}
	
	public static void main(String[] args) {
		
		System.out.println(new CosineSimilarity().similarity(new double[] { 1.2d, 0.84527 }, new double[] { 1.4356d, 0.67892, 0.01876557 }));
		
	}
	
}
