package com.moss.reflectionbenchmark.test;

import java.util.ArrayList;

public class TestManager {
	
	public final static int NUMBER_OF_EXECUTIONS = 1000000000;
	public static ArrayList<Long> timesDirectAccessName;
	public static ArrayList<Long> timesDirectAccessAge;
	public static ArrayList<Long> timesReflectAccessName;
	public static ArrayList<Long> timesReflectAccessAge;

	public TestManager() {
		timesDirectAccessName = new ArrayList<Long>();
		timesDirectAccessAge = new ArrayList<Long>();
		timesReflectAccessName = new ArrayList<Long>();
		timesReflectAccessAge = new ArrayList<Long>();
	}
	
	
	public void runTest() {
		Test test = new Test();
		long timeDirectAccessName = test.testNameDirectAccess(NUMBER_OF_EXECUTIONS);
		long timeDirectAccessAge = test.testAgeDirectAccess(NUMBER_OF_EXECUTIONS);
		long timeReflectAccessName = test.testNameDirectAccess(NUMBER_OF_EXECUTIONS);
		long timeReflectAccessAge = test.testAgeDirectAccess(NUMBER_OF_EXECUTIONS);
		timesDirectAccessName.add(timeDirectAccessName);
		timesDirectAccessAge.add(timeDirectAccessAge);
		timesReflectAccessName.add(timeReflectAccessName);
		timesReflectAccessAge.add(timeReflectAccessAge);
	}
	
	public void printResult() {
		printTimes(timesDirectAccessName);
		printTimes(timesDirectAccessAge);
		printTimes(timesReflectAccessName);
		printTimes(timesReflectAccessAge);
	}
	
	public void printTimes(ArrayList<Long> timeArray) {
		for (int i = 0; i < timeArray.size(); i++) {
			System.out.print(" " + timeArray.get(i));
		}
		System.out.println();
	}

}
