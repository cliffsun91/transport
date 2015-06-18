package com.cliffsun.transport.config;

public class HttpRequestException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HttpRequestException(int responseCode) {
		super("HTTP Response Code: " + responseCode);
	}

}
