package com.cliffsun.transport.tube;

public class TubeStation {
	
	private String name;
	private String description;
	private SimpleCoordinate coordinates;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SimpleCoordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(SimpleCoordinate coordinates) {
		this.coordinates = coordinates;
	}
}
