package com.assignment.atmassignment.pojos;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Atm {

	@Valid
	@NotNull(message = "Address is mandatory to create ATM")
	Address address;
	int distance;
	List<OpeningHours> openingHours;
	@NotNull(message = "functionality is mandatory to create ATM")
	String functionality;
	@NotNull(message = "type is mandatory to create ATM")
	String type;
	
	@JsonProperty(required = false)
	String status;
	
	public Atm() {
		// TODO Auto-generated constructor stub
	}

	public Atm(Address address, int distance, List<OpeningHours> openingHours, String functionality, String type) {
		super();
		this.address = address;
		this.distance = distance;
		this.openingHours = openingHours;
		this.functionality = functionality;
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<OpeningHours> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHours> openingHours) {
		this.openingHours = openingHours;
	}

	public String getFunctionality() {
		return functionality;
	}

	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, distance, functionality, openingHours, status, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Atm)) {
			return false;
		}
		Atm other = (Atm) obj;
		return Objects.equals(address, other.address) && distance == other.distance
				&& Objects.equals(functionality, other.functionality)
				&& Objects.equals(openingHours, other.openingHours) && Objects.equals(status, other.status)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Atm [address=" + address + ", distance=" + distance + ", openingHours=" + openingHours
				+ ", functionality=" + functionality + ", type=" + type + ", status=" + status + "]";
	}
	
}
