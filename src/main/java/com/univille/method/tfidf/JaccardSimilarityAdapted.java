package com.univille.method.tfidf;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JaccardSimilarityAdapted {

	public Double calculateSimilarity(List<String> left, List<String> right) {
		final int leftLength = left.size();
        final int rightLength = right.size();
        if (leftLength == 0 || rightLength == 0) {
            return 0d;
        }
        final Set<String> leftSet = new HashSet<>();
        for (int i = 0; i < leftLength; i++) {
            leftSet.add(left.get(i));
        }
        final Set<String> rightSet = new HashSet<>();
        for (int i = 0; i < rightLength; i++) {
            rightSet.add(right.get(i));
        } 
        final Set<String> unionSet = new HashSet<>(leftSet);
        unionSet.addAll(rightSet);
        final int intersectionSize = leftSet.size() + rightSet.size() - unionSet.size();
        return 1.0d * intersectionSize / unionSet.size();
	}

}
