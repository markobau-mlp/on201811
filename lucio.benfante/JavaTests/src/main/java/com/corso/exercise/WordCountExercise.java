package com.corso.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCountExercise {
	private static final Logger log = LoggerFactory.getLogger(WordCountExercise.class);
	
	public Map<String, Integer> wordCount(String[] str) {
		if (log.isInfoEnabled()) {
			log.info("Counting words in {}", Arrays.toString(str));
		}
		Map<String, Integer> result = new HashMap<>();
		for (String s : str) {
			log.debug("Counting {}", s);
//			result.put(s, result.containsKey(s)?(result.get(s)+1):1);
			
			Integer counter = 1;
			if (result.containsKey(s)) {
				log.debug("Found {} in the map", s);
				counter = result.get(s) + 1;
				log.trace("New counter for {} is {}", s, counter);
			}
			result.put(s,  counter);
		}
		log.info("Result of counting: {}", result);
		return result;
	}
}
