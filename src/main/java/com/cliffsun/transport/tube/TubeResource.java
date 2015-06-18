package com.cliffsun.transport.tube;

import javax.ws.rs.Path;

import com.cliffsun.tfl.TFLApi;
import com.cliffsun.transport.web.HttpRequestClient;
import com.google.inject.Inject;


@Path("/tube")
public class TubeResource {

	private TFLApi tflApi;
	private HttpRequestClient client;
	private TubeStationRepository tubeStationRepository;

	@Inject
	public TubeResource(TFLApi tflApi, HttpRequestClient client, TubeStationRepository tubeStationRepository) {
		this.tflApi = tflApi;
		this.client = client;
		this.tubeStationRepository = tubeStationRepository;
	}
	
	@Path("/line")
	public TubeLineResource tubeLineResource() {
		return new TubeLineResource(tflApi, client);
	}
	
	@Path("/stations")
	public TubeStationResource tubeStationResource() {
		return new TubeStationResource(tubeStationRepository);
	}
	
}
