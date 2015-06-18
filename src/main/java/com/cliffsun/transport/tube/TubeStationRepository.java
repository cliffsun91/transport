package com.cliffsun.transport.tube;

import java.util.List;
import java.util.Map;

public interface TubeStationRepository {

	public abstract Map<String, TubeStation> getStationMap();

	public abstract List<TubeStation> getStationList();

}