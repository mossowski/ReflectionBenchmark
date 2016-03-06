package com.moss.reflectionbenchmark.application;

import com.moss.reflectionbenchmark.test.TestManager;


/**
 * Reflection Benchmark
 *
 */
public class App {

	public final static int NUMBER_OF_TESTS = 1000;
	
	public static void main(String[] args) {
		TestManager testManager = new TestManager();
		for (int i = 0; i < NUMBER_OF_TESTS; i++) {
			testManager.runTest();
		}
		testManager.printResult();
	}

}
