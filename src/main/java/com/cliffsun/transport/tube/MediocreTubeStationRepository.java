package com.cliffsun.transport.tube;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.resteasy.logging.Logger;

import com.google.inject.Singleton;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Geometry;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;


public class MediocreTubeStationRepository implements TubeStationRepository {

	private Logger logger = Logger.getLogger(getClass());
	private Map<String, TubeStation> stationMap;

	public MediocreTubeStationRepository() {
		// Do the expensive bit here in the constructor 
		// If we bind it as an EagerSingleton in Guice
		// it means it's only called once - and therefore the parsing is only done once).
		// However the trade off is that startup of the web server is slightly slower
		// as this is being instantiated right at the beginning - but it means the 
		// first request will be as fast as subsequent requests 
		// (compared to bind/annotating it as a lazy Singleton
		stationMap = createTubeStationMap();
		logger.info(">>>>>>>>>>>>>>>>>>>>>> parsed the KML tube station data!");
	}

	/* (non-Javadoc)
	 * @see com.cliffsun.transport.tube.TubeStationRepository#getStationMap()
	 */
	@Override
	public Map<String, TubeStation> getStationMap()
	{
		return stationMap;
	}
	
	/* (non-Javadoc)
	 * @see com.cliffsun.transport.tube.TubeStationRepository#getStationList()
	 */
	@Override
	public List<TubeStation> getStationList()
	{
		return new ArrayList<TubeStation>(stationMap.values());
	}
	
	private Map<String, TubeStation> createTubeStationMap() {
		String src = "com/cliffsun/transport/tube/stations.kml";
		InputStream is = getClass().getClassLoader().getResourceAsStream(src);
		if (is == null) 
		{
			throw new IllegalArgumentException(
					"Could not load KML file for stations");
		}
		Kml kml = Kml.unmarshal(is);
		
		return parseTubeStationKmlToMap(kml);
	}
	
	private Map<String, TubeStation> parseTubeStationKmlToMap(Kml kml) 
	{
		Map<String, TubeStation> tubeStationMap = new HashMap<String, TubeStation>();
		
		Feature feature = kml.getFeature();
		if (feature instanceof Document) 
		{
			Document document = (Document) feature;
			List<Feature> featureList = document.getFeature();
			for (Feature documentFeature : featureList) 
			{
				if (documentFeature instanceof Placemark) 
				{
					Placemark placemark = (Placemark) documentFeature;

					TubeStation station = new TubeStation();
					
					String tubeStationName = placemark.getName().trim();
					
					station.setName(tubeStationName);
					station.setDescription(placemark.getDescription().trim());
					SimpleCoordinate tubeCoordinate = parseTubeStationGeometry(placemark.getGeometry(), tubeStationName);
					station.setCoordinates(tubeCoordinate);
					tubeStationMap.put(tubeStationName, station);
				}
			}
			
			return tubeStationMap;
		}
		else 
		{
			throw new IllegalArgumentException("Expected there to be a Document as the feature");
		}
	}

	private SimpleCoordinate parseTubeStationGeometry(Geometry geometry, String name) 
	{
        if(geometry instanceof Point) 
        {
            Point point = (Point) geometry;
            List<Coordinate> coordinates  = point.getCoordinates();
            // we only expect one coordinate for a tube station
            if (coordinates.size() != 1) 
            {
            	throw new IllegalArgumentException("Got more than one coordinate for tube station: " + name);
            }
            
            Coordinate coord = coordinates.get(0);
            SimpleCoordinate tubeCoord = new SimpleCoordinate();
            tubeCoord.setLongitude(coord.getLongitude());
            tubeCoord.setLatitude(coord.getLatitude());
            tubeCoord.setAltitude(coord.getAltitude());
            return tubeCoord;
		}
		else {
			throw new IllegalArgumentException("Expected geometry to be a Point for tube station: " + name);
		}
	}
}
