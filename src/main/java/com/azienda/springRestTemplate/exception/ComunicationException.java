package com.azienda.springRestTemplate.exception;

public class ComunicationException extends Exception {
	public ComunicationException(String message,Throwable throwable) {
		super(message,throwable);
	}
}
