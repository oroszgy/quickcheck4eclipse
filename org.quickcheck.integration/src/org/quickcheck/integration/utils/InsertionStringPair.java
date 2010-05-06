package org.quickcheck.integration.utils;

/**
 * Simple class for handling String pairs
 * 
 * @author György Orosz
 * 
 */
public class InsertionStringPair {
	public enum Position {
		BEFORE, AFTER, DEFAULT
	}

	public Position position;
	public String first;
	public String second;

	public InsertionStringPair(String first, String second, Position position) {
		this.position = position;
		this.first = first;
		this.second = second;
	}

	public InsertionStringPair(String first, String second) {
		this(first, second, Position.DEFAULT);
	}

	@Override
	public String toString() {
		return first + second;
	}
}