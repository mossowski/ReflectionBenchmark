package com.moss.reflectionbenchmark.test;

import java.lang.reflect.Field;

import com.moss.reflectionbenchmark.model.Person;

public class Test {

	public Person person;

	public Test() {
		person = new Person();
	}

	public long testDirectAccess(int m) {
		long start = System.currentTimeMillis();
		int x;
		for (int i = 0; i < m; i++) {
			x = person.age;
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	public long testReflectionAccess(int m) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		long start = System.currentTimeMillis();
		int x;
		for (int i = 0; i < m; i++) {
			Field field = person.getClass().getField("age");
			x = (Integer) field.get(person);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

}
