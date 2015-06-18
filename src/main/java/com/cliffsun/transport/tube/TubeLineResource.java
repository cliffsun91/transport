package com.cliffsun.transport.tube;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cliffsun.tfl.TFLApi;
import com.cliffsun.transport.config.HttpRequestException;
import com.cliffsun.transport.config.TflApiException;
import com.cliffsun.transport.web.HttpRequestClient;

public class TubeLineResource {

	private TFLApi tflApi;
	private HttpRequestClient httpRequest;

	public TubeLineResource(TFLApi tflApi, HttpRequestClient httpRequest) {
		this.tflApi = tflApi;
		this.httpRequest = httpRequest;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TubeLine> getAllTubeLines() throws TflApiException
	{
		try 
		{
			URL url = tflApi.allTubeLinesUri().toURL();
			TubeLine[] tubeLines = httpRequest.get(url, TubeLine[].class);
			return Arrays.asList(tubeLines);
		} 
		catch (MalformedURLException e) 
		{
			throw new TflApiException(e);
		} 
		catch (IOException e) 
		{
			throw new TflApiException(e);
		} 
		catch (HttpRequestException e) 
		{
			throw new TflApiException(e);
		}
	}
	
	@GET
	@Path("/{lineId}")
	public String getTubeLine(@PathParam("lineId") String lineId)
	{
		return "the line you have chosen is: " + lineId;
	}
}
