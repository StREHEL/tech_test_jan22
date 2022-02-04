package com.afklm.test.service.errors;

public class NoFrenchUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoFrenchUserException() {
		super("User is not French!");
	}
}
