package com.moss.reflectionbenchmark.application;

import com.moss.reflectionbenchmark.test.Test;

/**
 * Reflection Benchmark
 *
 */
public class App {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Test test = new Test();
		long directTime = test.testDirectAccess(100000);
		long reflectionTime = test.testReflectionAccess(100000);

		System.out.println("Direct time     : " + directTime);
		System.out.println("Reflection time : " + reflectionTime);
	}

}
