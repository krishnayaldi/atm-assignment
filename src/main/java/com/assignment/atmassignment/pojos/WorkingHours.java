package com.assignment.atmassignment.pojos;

import java.util.Objects;

public class WorkingHours {

	String hourFrom;
	String hourTo;
	
	public WorkingHours() {
		//no-arg constructor
	}
	
	public WorkingHours(String hoursFrom, String hoursTo) {
		super();
		this.hourFrom = hoursFrom;
		this.hourTo = hoursTo;
	}
	
	public String getHourFrom() {
		return hourFrom;
	}
	public void setHourFrom(String hourFrom) {
		this.hourFrom = hourFrom;
	}
	public String getHourTo() {
		return hourTo;
	}
	public void setHoursTo(String hourTo) {
		this.hourTo = hourTo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hourFrom, hourTo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof WorkingHours)) {
			return false;
		}
		WorkingHours other = (WorkingHours) obj;
		return Objects.equals(hourFrom, other.hourFrom) && Objects.equals(hourTo, other.hourTo);
	}

	@Override
	public String toString() {
		return "WorkingHours [hourFrom=" + hourFrom + ", hourTo=" + hourTo + "]";
	}
	
}
