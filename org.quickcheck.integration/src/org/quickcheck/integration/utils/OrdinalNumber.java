package org.quickcheck.integration.utils;

public enum OrdinalNumber {
	First(0), Last(-1);

	protected final int number;

	OrdinalNumber(int number) {
		this.number = number;
	}

	public int getValue() {
		return number;
	}

}
