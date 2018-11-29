package com.corso.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FibonacciExercise {
	private static final Logger log =
			LoggerFactory.getLogger(FibonacciExercise.class);
	
	long[] cache = new long[1000];
	
	public long fibonacci(int n) {
		log.debug("Calculating fibonacci for {}", n);
		if (n > 1 && n < cache.length && cache[n] != 0) {
			log.debug("Using cache for calculating fibonacci({})", n);
			return cache[n];
		}
		if (n == 0) return 0;
		if (n == 1) return 1;
		long result = fibonacci(n-1)+fibonacci(n-2);
		if (n < cache.length) {
			cache[n] = result;
		}
		return result;
	}

	public long fibonacciNoCache(int n) {
		log.debug("Calculating fibonacci for {}", n);
		if (n == 0) return 0;
		if (n == 1) return 1;
		long result = fibonacciNoCache(n-1)+fibonacciNoCache(n-2);
		return result;
	}
	

	public long fibonacciIterative(int n) {
		log.debug("Calculating fibonacci for {}", n);
		if (n > 1 && n < cache.length && cache[n] != 0) {
			log.debug("Using cache for calculating fibonacci({})", n);
			return cache[n];
		}
		if (n == 0) return 0;
		if (n == 1) return 1;
		long result = 0;
		long last1 = 1, last2 = 0;
		for(int i = 1; i<n; i++) {
			result = last1+last2;
			last2 = last1;
			last1 = result;			
		}
		if (n < cache.length) {
			cache[n] = last1;
		}
		return last1;
	}
}
