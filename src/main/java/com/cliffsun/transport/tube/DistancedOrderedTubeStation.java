package com.cliffsun.transport.tube;

public class DistancedOrderedTubeStation implements Comparable<DistancedOrderedTubeStation>{

	private double distanceInKm;
	private TubeStation tubeStation;
	
	public double getDistanceInKm() {
		return distanceInKm;
	}
	
	public void setDistanceInKm(double distanceInKm) {
		this.distanceInKm = distanceInKm;
	}

	public TubeStation getTubeStation() {
		return tubeStation;
	}

	public void setTubeStation(TubeStation tubeStation) {
		this.tubeStation = tubeStation;
	}

	@Override
	public int compareTo(DistancedOrderedTubeStation other) {
		if (distanceInKm < other.getDistanceInKm())
		{
			return -1;
		}
		else if (distanceInKm > other.getDistanceInKm())
		{
			return 1;
		}
		return 0;
	}
}
