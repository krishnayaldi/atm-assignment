package com.assignment.atmassignment.pojos;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class Address {

	String street;
	String housenumber;
	String postalcode;
	@NotNull(message = "City is mandatory to Complete Addresss")
	String city;
	GeoLocation geoLocation;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String street, String housenumber, String postalcode, String city, GeoLocation geoLocation) {
		super();
		this.street = street;
		this.housenumber = housenumber;
		this.postalcode = postalcode;
		this.city = city;
		this.geoLocation = geoLocation;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, geoLocation, housenumber, postalcode, street);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Address)) {
			return false;
		}
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(geoLocation, other.geoLocation)
				&& Objects.equals(housenumber, other.housenumber) && Objects.equals(postalcode, other.postalcode)
				&& Objects.equals(street, other.street);
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", housenumber=" + housenumber + ", postalcode=" + postalcode + ", city="
				+ city + ", geoLocation=" + geoLocation + "]";
	}
	
}
