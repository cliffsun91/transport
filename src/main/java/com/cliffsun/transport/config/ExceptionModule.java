package com.cliffsun.transport.config;

import com.google.inject.AbstractModule;

public class ExceptionModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(TflApiExceptionMapper.class);
		bind(IllegalArgumentExceptionMapper.class);
	}

}
