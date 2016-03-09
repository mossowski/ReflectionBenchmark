package com.moss.reflectionbenchmark.test;

public class TimeWatch {

	long starts;

	// --------------------------------------------------------------------------------------

	private TimeWatch() {
		reset();
	}

	// --------------------------------------------------------------------------------------

	public static TimeWatch start() {
		return new TimeWatch();
	}

	// --------------------------------------------------------------------------------------

	public TimeWatch reset() {
		starts = System.nanoTime();
		return this;
	}

	// --------------------------------------------------------------------------------------

	public long time() {
		long ends = System.nanoTime();
		return ends - starts;
	}

}
