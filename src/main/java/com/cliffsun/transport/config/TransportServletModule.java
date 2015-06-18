package com.cliffsun.transport.config;

import com.cliffsun.transport.ResourceModule;
import com.cliffsun.transport.web.GAEHttpRequestClient;
import com.cliffsun.transport.web.HttpRequestClient;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.owlike.genson.Genson;

public class TransportServletModule extends AbstractModule{

	@Override
	protected void configure() 
	{
		bind(HttpRequestClient.class).to(GAEHttpRequestClient.class);
		
		install(new ResourceModule());
		install(new ExceptionModule());
	}
	
	@Provides
	public Genson getGenson()
	{
		return new Genson();
	}

}
