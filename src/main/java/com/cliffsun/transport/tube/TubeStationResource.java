package com.cliffsun.transport.tube;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.cliffsun.transport.config.TflApiException;
import com.google.common.base.Preconditions;


public class TubeStationResource {

	private TubeStationRepository tubeStationRepository;

	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371.0d;
	
	public TubeStationResource(TubeStationRepository tubeStationRepository) {
		this.tubeStationRepository = tubeStationRepository;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TubeStation> getAllTubeLines() throws TflApiException 
	{
		return tubeStationRepository.getStationList();
	}
	
	@GET
	@Path("/nearest")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DistancedOrderedTubeStation> getNearestTubeStation(@QueryParam("lat") Double latitude, 
																   @QueryParam("long") Double longitude, 
																   @QueryParam("resultSize") @DefaultValue("1") int resultSize)
	{
		Preconditions.checkArgument(latitude != null,
				"query param 'lat' (latitude) must be specified");
		Preconditions.checkArgument(longitude != null,
				"query param 'long' (longitude) must be specified");
		
		return calculateClosestTubeStation(latitude, longitude, resultSize);
	}
	
	private List<DistancedOrderedTubeStation> calculateClosestTubeStation(Double latitude, Double longitude, int resultSize)
	{
		List<TubeStation> tubeStations = tubeStationRepository.getStationList();
		
		Preconditions.checkArgument(resultSize <= tubeStations.size(), 
				"resultSize specified is too large, max is: " + tubeStations.size());
		
		List<DistancedOrderedTubeStation> distancedStations = new ArrayList<DistancedOrderedTubeStation>();
		for (TubeStation station: tubeStations)
		{
			SimpleCoordinate coord = station.getCoordinates();
			double distance = calculateDistanceInKm(latitude, longitude, coord.getLatitude(), coord.getLongitude());
			DistancedOrderedTubeStation distancedStation = new DistancedOrderedTubeStation();
			distancedStation.setDistanceInKm(distance);
			distancedStation.setTubeStation(station);
			distancedStations.add(distancedStation);
		}
		
		Collections.sort(distancedStations); //sorts in ascending order - perfect
		distancedStations.subList(resultSize, distancedStations.size()).clear();
		
		return distancedStations;
	}
	
	private double calculateDistanceInKm(double lat1, double long1, double lat2, double long2) 
	{
	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lngDistance = Math.toRadians(long2 - long1);

	    double a = Math.pow(Math.sin(latDistance / 2), 2) + 
	    		   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	    		   Math.pow(Math.sin(lngDistance / 2), 2);

	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

	    return AVERAGE_RADIUS_OF_EARTH_KM * c; 
	}

}