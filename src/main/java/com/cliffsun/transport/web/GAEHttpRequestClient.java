package com.cliffsun.transport.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import com.cliffsun.transport.config.HttpRequestException;
import com.google.inject.Inject;
import com.owlike.genson.Genson;

public class GAEHttpRequestClient implements HttpRequestClient {

	private Genson genson;

	@Inject
	public GAEHttpRequestClient(Genson genson) {
		this.genson = genson;
	}
	
	@Override
	public <T> T get(URL url, Class<T> returnType) throws IOException, HttpRequestException {
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoOutput(true);
	    connection.setRequestMethod("GET");
	    connection.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
	
	    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String line;
	    
	    StringBuffer responseBody = new StringBuffer();
	    
	    while ((line = reader.readLine()) != null) {
	        responseBody.append(line);
	    }
	    reader.close();
	    
	    int responseCode = connection.getResponseCode();
	    
	    if (responseCode == HttpURLConnection.HTTP_OK) 
	    {
	    	return genson.deserialize(responseBody.toString(), returnType);
	    } 
	    else if (responseCode == -1)
	    {
	    	throw new IllegalStateException();
	    }
	    else
	    {
	    	throw new HttpRequestException(responseCode);
	    }
	}

}
