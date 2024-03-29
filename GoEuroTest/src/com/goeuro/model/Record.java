package com.goeuro.model;

public class Record {
	int id;
	String name;
	String type;
	double latitude;
	double longitude;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Record [ id=" + id + ", name=" + name
				+ ", type=" + type + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
	public Record( int id, String name, String type,
			double latitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
