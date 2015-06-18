package com.cliffsun.transport.config;


public class TflApiException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TflApiException(String message) {
		super(message);
	}

	public TflApiException(Exception e) {
		super(e);
	}

}
