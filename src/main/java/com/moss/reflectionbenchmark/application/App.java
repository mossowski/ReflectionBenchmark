package com.moss.reflectionbenchmark.application;

import java.lang.reflect.InvocationTargetException;

import com.moss.reflectionbenchmark.test.TestManager;


/**
 * Reflection Benchmark
 *
 */
public class App {

	public final static int NUMBER_OF_TESTS = 25;

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		TestManager testManager = new TestManager();
		for (int i = 0; i < NUMBER_OF_TESTS; i++) {
			testManager.runTest();
		}
		//testManager.printResultTimes();
		testManager.printResult();
	}

}
