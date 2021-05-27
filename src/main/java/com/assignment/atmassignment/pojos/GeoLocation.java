package com.assignment.atmassignment.pojos;

import java.util.Objects;

public class GeoLocation {

	double lat;
	double lng;
	
	public GeoLocation() {
		// TODO Auto-generated constructor stub
	}

	public GeoLocation(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lat, lng);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GeoLocation)) {
			return false;
		}
		GeoLocation other = (GeoLocation) obj;
		return Double.doubleToLongBits(lat) == Double.doubleToLongBits(other.lat)
				&& Double.doubleToLongBits(lng) == Double.doubleToLongBits(other.lng);
	}

	@Override
	public String toString() {
		return "GeoLocation [lat=" + lat + ", lng=" + lng + "]";
	}
	
}
