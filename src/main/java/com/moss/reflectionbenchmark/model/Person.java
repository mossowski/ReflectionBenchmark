package com.moss.reflectionbenchmark.model;

public class Person {

	public String name;
	public int age;

	// --------------------------------------------------------------------------------------

	public Person() {
		name = "Janusz";
		age = 20;
	}

	// --------------------------------------------------------------------------------------

	public String message(String message) {
		String info = " info";
		return message + info;
	}

}
