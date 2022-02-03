package com.afklm.test.web.rest.errors;

public class NoFrenchUserException extends BadRequestAlertException {

	private static final long serialVersionUID = 1L;

	public NoFrenchUserException() {
		super(ErrorConstants.NON_FRENCH_RESIDENT_TYPE, "User is not French!", "userManagement", "nofrenchuser");
	}
}
