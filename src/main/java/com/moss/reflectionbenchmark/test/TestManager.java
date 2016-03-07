package com.moss.reflectionbenchmark.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TestManager {
	
	public final static int NUMBER_OF_EXECUTIONS = 100000;
	public final static int NUMBER_OF_EXECUTIONS_WARMUP = 100000;

	public static ArrayList<Long> timesDirectAccessGetName;
	public static ArrayList<Long> timesDirectAccessGetAge;
	public static ArrayList<Long> timesDirectAccessSetName;
	public static ArrayList<Long> timesDirectAccessSetAge;
	public static ArrayList<Long> timesDirectAccessMethod;

	public static ArrayList<Long> timesReflectionAccessGetName;
	public static ArrayList<Long> timesReflectionAccessGetAge;
	public static ArrayList<Long> timesReflectionAccessSetName;
	public static ArrayList<Long> timesReflectionAccessSetAge;
	public static ArrayList<Long> timesReflectionAccessMethod;

	public TestManager() {
		timesDirectAccessGetName = new ArrayList<Long>();
		timesDirectAccessGetAge = new ArrayList<Long>();
		timesDirectAccessSetName = new ArrayList<Long>();
		timesDirectAccessSetAge = new ArrayList<Long>();
		timesDirectAccessMethod = new ArrayList<Long>();

		timesReflectionAccessGetName = new ArrayList<Long>();
		timesReflectionAccessGetAge = new ArrayList<Long>();
		timesReflectionAccessSetName = new ArrayList<Long>();
		timesReflectionAccessSetAge = new ArrayList<Long>();
		timesReflectionAccessMethod = new ArrayList<Long>();
	}
	
	public void warmup() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Test test = new Test();

		test.testNameDirectAccess(NUMBER_OF_EXECUTIONS_WARMUP, true);
		test.testAgeDirectAccess(NUMBER_OF_EXECUTIONS_WARMUP, true);
		test.testNameDirectAccess(NUMBER_OF_EXECUTIONS_WARMUP, false);
		test.testAgeDirectAccess(NUMBER_OF_EXECUTIONS_WARMUP, false);
		test.testMessageDirectAccess(NUMBER_OF_EXECUTIONS_WARMUP);

		test.testNameReflectionAccess(NUMBER_OF_EXECUTIONS_WARMUP, true);
		test.testAgeReflectionAccess(NUMBER_OF_EXECUTIONS_WARMUP, true);
		test.testNameReflectionAccess(NUMBER_OF_EXECUTIONS_WARMUP, false);
		test.testAgeReflectionAccess(NUMBER_OF_EXECUTIONS_WARMUP, false);
		test.testMessageReflectionAccess(NUMBER_OF_EXECUTIONS_WARMUP);
	}
	
	
	public void runTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Test test = new Test();

		long timeDirectAccessGetName = test.testNameDirectAccess(NUMBER_OF_EXECUTIONS, true);
		long timeDirectAccessGetAge = test.testAgeDirectAccess(NUMBER_OF_EXECUTIONS, true);
		long timeDirectAccessSetName = test.testNameDirectAccess(NUMBER_OF_EXECUTIONS, false);
		long timeDirectAccessSetAge = test.testAgeDirectAccess(NUMBER_OF_EXECUTIONS, false);
		long timeDirectAccessMethod = test.testMessageDirectAccess(NUMBER_OF_EXECUTIONS);

		long timeReflectionAccessGetName = test.testNameReflectionAccess(NUMBER_OF_EXECUTIONS, true);
		long timeReflectionAccessGetAge = test.testAgeReflectionAccess(NUMBER_OF_EXECUTIONS, true);
		long timeReflectionAccessSetName = test.testNameReflectionAccess(NUMBER_OF_EXECUTIONS, false);
		long timeReflectionAccessSetAge = test.testAgeReflectionAccess(NUMBER_OF_EXECUTIONS, false);
		long timeReflectionAccessMethod = test.testMessageReflectionAccess(NUMBER_OF_EXECUTIONS);

		timesDirectAccessGetName.add(timeDirectAccessGetName);
		timesDirectAccessGetAge.add(timeDirectAccessGetAge);
		timesDirectAccessSetName.add(timeDirectAccessSetName);
		timesDirectAccessSetAge.add(timeDirectAccessSetAge);
		timesDirectAccessMethod.add(timeDirectAccessMethod);
		
		timesReflectionAccessGetName.add(timeReflectionAccessGetName);
		timesReflectionAccessGetAge.add(timeReflectionAccessGetAge);
		timesReflectionAccessSetName.add(timeReflectionAccessSetName);
		timesReflectionAccessSetAge.add(timeReflectionAccessSetAge);
		timesReflectionAccessMethod.add(timeReflectionAccessMethod);
	}
	
	public void printResult() {
		printTimes(timesDirectAccessGetName);
		printTimes(timesDirectAccessGetAge);
		printTimes(timesDirectAccessSetName);
		printTimes(timesDirectAccessSetAge);
		printTimes(timesDirectAccessMethod);

		printTimes(timesReflectionAccessGetName);
		printTimes(timesReflectionAccessGetAge);
		printTimes(timesReflectionAccessSetName);
		printTimes(timesReflectionAccessSetAge);
		printTimes(timesReflectionAccessMethod);
	}
	
	public void printTimes(ArrayList<Long> timeArray) {
		for (int i = 0; i < timeArray.size(); i++) {
			System.out.print(" " + timeArray.get(i));
		}
		System.out.println();
	}

}
