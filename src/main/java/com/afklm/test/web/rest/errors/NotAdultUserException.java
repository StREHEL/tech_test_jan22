package com.afklm.test.web.rest.errors;

public class NotAdultUserException extends BadRequestAlertException {

	private static final long serialVersionUID = 1L;

	public NotAdultUserException() {
		super(ErrorConstants.NON_ADULT_USER_TYPE, "User is not adult!", "userManagement", "minoruser");
	}
}
