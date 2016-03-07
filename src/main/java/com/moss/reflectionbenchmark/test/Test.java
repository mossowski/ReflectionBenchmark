package com.moss.reflectionbenchmark.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.moss.reflectionbenchmark.model.Person;

public class Test {

	public Person person;

	public Test() {
		person = new Person();
	}

	public long testNameDirectAccess(int m, boolean flag) {
		long start = System.currentTimeMillis();
		String name;
		for (int i = 0; i < m; i++) {
			if (flag) {
				name = person.name;
			}
			else {
				person.name = "Wiesiek";
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public long testAgeDirectAccess(int m, boolean flag) {
		long start = System.currentTimeMillis();
		int age;
		for (int i = 0; i < m; i++) {
			if (flag) {
				age = person.age;
			}
			else {
				person.age = 25;
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public long testMessageDirectAccess(int m) {
		long start = System.currentTimeMillis();
		String message;
		for (int i = 0; i < m; i++) {
			message = person.message("secret message");
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public long testNameReflectionAccess(int m, boolean flag) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		long start = System.currentTimeMillis();
		String name;
		for (int i = 0; i < m; i++) {
			if (flag) {
				Field field = person.getClass().getField("name");
				name = (String) field.get(person);
			}
			else {
				Field field = person.getClass().getField("name");
				field.set(person, "Andrzej");
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	public long testAgeReflectionAccess(int m, boolean flag) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		long start = System.currentTimeMillis();
		int age;
		for (int i = 0; i < m; i++) {
			if (flag) {
				Field field = person.getClass().getField("age");
				age = (Integer) field.get(person);
			}
			else {
				Field field = person.getClass().getField("age");
				field.set(person, 25);
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}
	
	public long testMessageReflectionAccess(int m) throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		long start = System.currentTimeMillis();
		String message = null;
		for (int i = 0; i < m; i++) {
			Method method = person.getClass().getMethod("message", String.class);
			message = (String) method.invoke(person, "secret reflect message");
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

}
