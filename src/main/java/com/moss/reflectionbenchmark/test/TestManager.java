package com.moss.reflectionbenchmark.test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TestManager {
	
	public final static int NUMBER_OF_EXECUTIONS = 10000;
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
		System.out.println("Direct access get name     : " + prepareData(timesDirectAccessGetName));
		System.out.println("Direct access get age      : " + prepareData(timesDirectAccessGetAge));
		System.out.println("Direct access set name     : " + prepareData(timesDirectAccessSetName));
		System.out.println("Direct access set age      : " + prepareData(timesDirectAccessSetAge));
		System.out.println("Direct access method       : " + prepareData(timesDirectAccessMethod));

		System.out.println("Reflection access get name : " + prepareData(timesReflectionAccessGetName));
		System.out.println("Reflection access get age  : " + prepareData(timesReflectionAccessGetAge));
		System.out.println("Reflection access set name : " + prepareData(timesReflectionAccessSetName));
		System.out.println("Reflection access set age  : " + prepareData(timesReflectionAccessSetAge));
		System.out.println("Reflection access method   : " + prepareData(timesReflectionAccessMethod));
	}

	public long prepareData(ArrayList<Long> timeArray) {
		long time = 0;
		removeOutsiders(timeArray);
		time = countAverageTime(timeArray);
		return time;
	}

	public long countAverageTime(ArrayList<Long> timeArray) {
		long averageTime = 0;
		for (int i = 0; i < timeArray.size(); i++) {
			averageTime += timeArray.get(i);
		}
		double modulo = averageTime % timeArray.size();
		averageTime /= timeArray.size();
		if (modulo >= 0.5) {
			averageTime++;
		}
		return averageTime;
	}
	
	public void removeOutsiders(ArrayList<Long> timeArray) {
		if (timeArray.size() > 10) {
			int remove = timeArray.size() / 10;
			for (int i = 0; i < remove; i++) {
				timeArray.remove(0);
				timeArray.remove(timeArray.size() - 1);
			}
		}
	}
	
	public void printResultTimes() {
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
