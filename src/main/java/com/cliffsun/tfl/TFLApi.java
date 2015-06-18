package com.cliffsun.tfl;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class TFLApi {

	public static final String appId = "2b54c31f";
	public static final String appKey = "5f18bbc9869f7c9f51dd3b11f20f3fa9";
	private String host;
	
	public TFLApi() {
		this("https://api.tfl.gov.uk/");
	}

	public TFLApi(String host) {
		this.host = host;
	}
	
	public URI allTubeLinesUri()
	{
		return UriBuilder.fromUri(URI.create(host))
							.path("Line/Mode/%7Bmodes%7D")
							.queryParam("modes", "tube")
							.queryParam("app_id", TFLApi.appId)
							.queryParam("app_key", TFLApi.appKey)
							.build();
	}

}
