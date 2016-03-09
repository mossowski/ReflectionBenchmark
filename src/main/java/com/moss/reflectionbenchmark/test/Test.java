package com.moss.reflectionbenchmark.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.moss.reflectionbenchmark.model.Person;

public class Test {

	public final static int NUMBER_OF_EXECUTIONS = 10000000;
	public final static int NUMBER_OF_EXECUTIONS_WARMUP = 100000;
	public Person person;

	// --------------------------------------------------------------------------------------

	public Test() {
		person = new Person();
	}

	// --------------------------------------------------------------------------------------

	public long testNameDirectAccess(boolean flag) {
		String name = null;
		for (int i = 0; i < NUMBER_OF_EXECUTIONS_WARMUP; i++) {
			if (flag) {
				name = person.name;
			} else {
				person.name = "Wiesiek";
			}
		}
		TimeWatch timeWatch = TimeWatch.start();
		for (int i = 0; i < NUMBER_OF_EXECUTIONS; i++) {
			if (flag) {
				name = person.name;
			} else {
				person.name = "Wiesiek";
			}
		}
		return timeWatch.time();
	}

	// --------------------------------------------------------------------------------------

	public long testAgeDirectAccess(boolean flag) {
		int age = 0;
		for (int i = 0; i < NUMBER_OF_EXECUTIONS_WARMUP; i++) {
			if (flag) {
				age = person.age;
			} else {
				person.age = 25;
			}
		}
		TimeWatch timeWatch = TimeWatch.start();
		for (int i = 0; i < NUMBER_OF_EXECUTIONS; i++) {
			if (flag) {
				age = person.age;
			} else {
				person.age = 25;
			}
		}
		return timeWatch.time();
	}

	// --------------------------------------------------------------------------------------

	public long testMessageDirectAccess() {
		String message = null;
		for (int i = 0; i < NUMBER_OF_EXECUTIONS_WARMUP; i++) {
			message = person.message("secret message");
		}
		TimeWatch timeWatch = TimeWatch.start();
		for (int i = 0; i < NUMBER_OF_EXECUTIONS; i++) {
			message = person.message("secret message");
		}
		return timeWatch.time();
	}

	// --------------------------------------------------------------------------------------

	public long testNameReflectionAccess(boolean flag) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String name = null;
		for (int i = 0; i < NUMBER_OF_EXECUTIONS_WARMUP; i++) {
			if (flag) {
				Field field = person.getClass().getField("name");
				name = (String) field.get(person);
			} else {
				Field field = person.getClass().getField("name");
				field.set(person, "Andrzej");
			}
		}
		TimeWatch timeWatch = TimeWatch.start();
		for (int i = 0; i < NUMBER_OF_EXECUTIONS; i++) {
			if (flag) {
				Field field = person.getClass().getField("name");
				name = (String) field.get(person);
			} else {
				Field field = person.getClass().getField("name");
				field.set(person, "Andrzej");
			}
		}
		return timeWatch.time();
	}

	// --------------------------------------------------------------------------------------

	public long testAgeReflectionAccess(boolean flag) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		int age = 0;
		for (int i = 0; i < NUMBER_OF_EXECUTIONS_WARMUP; i++) {
			if (flag) {
				Field field = person.getClass().getField("age");
				age = field.getInt(person);
			} else {
				Field field = person.getClass().getField("age");
				field.set(person, 25);
			}
		}
		TimeWatch timeWatch = TimeWatch.start();
		for (int i = 0; i < NUMBER_OF_EXECUTIONS; i++) {
			if (flag) {
				Field field = person.getClass().getField("age");
				age = field.getInt(person);
			} else {
				Field field = person.getClass().getField("age");
				field.set(person, 25);
			}
		}
		return timeWatch.time();
	}

	// --------------------------------------------------------------------------------------

	public long testMessageReflectionAccess() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String message = null;
		for (int i = 0; i < NUMBER_OF_EXECUTIONS_WARMUP; i++) {
			Method method = person.getClass().getMethod("message", String.class);
			message = (String) method.invoke(person, "secret reflect message");
		}
		TimeWatch timeWatch = TimeWatch.start();
		for (int i = 0; i < NUMBER_OF_EXECUTIONS; i++) {
			Method method = person.getClass().getMethod("message", String.class);
			message = (String) method.invoke(person, "secret reflect message");
		}
		return timeWatch.time();
	}

}
