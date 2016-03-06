package com.moss.reflectionbenchmark.test;

import java.lang.reflect.Field;

import com.moss.reflectionbenchmark.model.Person;

public class Test {

	public Person person;

	public Test() {
		person = new Person();
	}

	public long testNameDirectAccess(int m) {
		long start = System.currentTimeMillis();
		String name;
		for (int i = 0; i < m; i++) {
			name = person.name;
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public long testAgeDirectAccess(int m) {
		long start = System.currentTimeMillis();
		int age;
		for (int i = 0; i < m; i++) {
			age = person.age;
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public long testNameReflectionAccess(int m) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		long start = System.currentTimeMillis();
		String name;
		for (int i = 0; i < m; i++) {
			Field field = person.getClass().getField("name");
			name = (String) field.get(person);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	public long testAgeReflectionAccess(int m) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		long start = System.currentTimeMillis();
		int age;
		for (int i = 0; i < m; i++) {
			Field field = person.getClass().getField("age");
			age = (Integer) field.get(person);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

}
