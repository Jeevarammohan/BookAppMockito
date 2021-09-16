package com.bookapp.exception;

public class IdNotFoundException extends RuntimeException {

	public IdNotFoundException() {
		super();
	}

	public IdNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
