package com.assignment.atmassignment.pojos;

import java.util.Arrays;
import java.util.Objects;

public class OpeningHours {

	int dayOfWeek;
	WorkingHours[] hours;
	
	public OpeningHours() {
		// TODO Auto-generated constructor stub
	}

	public OpeningHours(int dayOfWeek, WorkingHours[] hours) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.hours = hours;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public WorkingHours[] getHours() {
		return hours;
	}

	public void setHours(WorkingHours[] hours) {
		this.hours = hours;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(hours);
		result = prime * result + Objects.hash(dayOfWeek);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OpeningHours)) {
			return false;
		}
		OpeningHours other = (OpeningHours) obj;
		return dayOfWeek == other.dayOfWeek && Arrays.equals(hours, other.hours);
	}

	@Override
	public String toString() {
		return "OpeningHours [dayOfWeek=" + dayOfWeek + ", hours=" + Arrays.toString(hours) + "]";
	}
	
}
