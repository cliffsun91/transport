package com.cliffsun.transport.web;

import java.io.IOException;
import java.net.URL;

import com.cliffsun.transport.config.HttpRequestException;

public interface HttpRequestClient {

	public <T> T get(URL url, Class<T> returnType) throws IOException, HttpRequestException;
}
