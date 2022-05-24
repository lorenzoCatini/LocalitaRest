package com.azienda.springRestTemplate.exception;

public class DatiNonValidiException extends Exception {
	public DatiNonValidiException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
