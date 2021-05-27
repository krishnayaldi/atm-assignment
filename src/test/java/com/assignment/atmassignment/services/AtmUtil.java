package com.assignment.atmassignment.services;

import java.util.ArrayList;
import java.util.List;

import com.assignment.atmassignment.pojos.Address;
import com.assignment.atmassignment.pojos.Atm;
import com.assignment.atmassignment.pojos.GeoLocation;
import com.assignment.atmassignment.pojos.OpeningHours;
import com.assignment.atmassignment.pojos.WorkingHours;

class AtmUtil {

	public static List<OpeningHours> getDummyCompleteListOpeningHours() {
		List<OpeningHours> openingHours = new ArrayList<OpeningHours>();
		WorkingHours hours[] = { new WorkingHours("9:00", "5:00") };

		openingHours.add(new OpeningHours(1, hours));
		openingHours.add(new OpeningHours(2, hours));
		openingHours.add(new OpeningHours(3, hours));
		openingHours.add(new OpeningHours(4, hours));
		openingHours.add(new OpeningHours(5, hours));
		openingHours.add(new OpeningHours(6, hours));
		openingHours.add(new OpeningHours(7, hours));
		return openingHours;
	}

	public static Address getDummyAddress() {
		return new Address("curry street", "23", "4312 21", "delhi", new GeoLocation(23.45, 43.23));
	}

	public static Atm getDummyAtm() {
		return new Atm(getDummyAddress(), 0, getDummyCompleteListOpeningHours(), "Geldautomaat", "ING");
	}

	public static List<Atm> getDummyListOfAtm() {
		List<Atm> atmList = new ArrayList<Atm>();
		atmList.add(getDummyAtm());
		atmList.add(getDummyAtm());
		return atmList;
	}
}
