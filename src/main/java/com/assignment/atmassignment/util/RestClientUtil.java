package com.assignment.atmassignment.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assignment.atmassignment.exceptions.RestClientConsumerException;
import com.assignment.atmassignment.pojos.Address;
import com.assignment.atmassignment.pojos.Atm;
import com.assignment.atmassignment.pojos.GeoLocation;
import com.assignment.atmassignment.pojos.OpeningHours;
import com.assignment.atmassignment.pojos.WorkingHours;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is utility class which initializes list Atm
 * @author Krishna
 * */
public class RestClientUtil {

	private static Logger logger = LoggerFactory.getLogger(RestClientUtil.class);
	/**
	 * This method fetches list of Atms from server and returns it as list of Atm
	 * */
	public static List<Atm> initializeAtmsWitRestApi() throws  RestClientConsumerException{
		List<Atm> resultantListOfAtm = new ArrayList<Atm>();

		try {
			URL url = new URL("https://www.ing.nl/api/locator/atms/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				logger.warn("Error while  consuming rest service.. status code {}",conn.getResponseCode());
				throw new RestClientConsumerException("Failed : HTTP Error code : " + conn.getResponseCode());
			} else {
				String jsonString = "";
				Scanner scanner = new Scanner(url.openStream());

				// to remove error prone string
				if (scanner.hasNext()) {
					scanner.nextLine();
				}

				// Write all the JSON data into a string using a scanner
				while (scanner.hasNext()) {
					jsonString += scanner.nextLine();
				}

				// Close the scanner
				scanner.close();

				// Using the JSON simple library parse the string into a json object
				ObjectMapper mapper = new ObjectMapper();
				Atm[] arrAtms = mapper.readValue(jsonString, Atm[].class);
				logger.info("atms initialized successfully");
				resultantListOfAtm = Arrays.asList(arrAtms);
			}
			conn.disconnect();
		} catch (Exception e) {
			logger.warn("Error while  consuming rest service.. message {}",e.getMessage());
			throw new RestClientConsumerException("Error while consuming ING service");
		}

		return resultantListOfAtm;
	}

	/**
	 * This method creates dummy list of Atms and sends to caller
	 * */
	public static List<Atm> initializeAtmCustomObjects() {
		List<Atm> resultantListOfAtm = new ArrayList<Atm>();
		List<OpeningHours> openingHours = new ArrayList<OpeningHours>();
		WorkingHours hours[]  = {new WorkingHours("9:00", "5:00")};
		
		openingHours.add(new OpeningHours(1, hours));
		openingHours.add(new OpeningHours(2,hours));
		openingHours.add(new OpeningHours(3, hours));
		openingHours.add(new OpeningHours(4, hours));
		openingHours.add(new OpeningHours(5, hours));
		openingHours.add(new OpeningHours(6, hours));
		openingHours.add(new OpeningHours(7, hours));
		
		Address address1 = new Address("curry street", "23", "4312 21", "delhi", new GeoLocation(23.45,43.23));
		Address address2 = new Address("curry street", "23", "4312 21", "dutch", new GeoLocation(23.45,43.23));
		Address address3 = new Address("curry street", "23", "4312 21", "pune", new GeoLocation(23.45,43.23));
		Address address4 = new Address("curry street", "23", "4312 21", "mumbai", new GeoLocation(23.45,43.23));
		
		resultantListOfAtm.add(new Atm(address1, 0, openingHours, "Geldautomaat", "GELDMAAT"));
		resultantListOfAtm.add(new Atm(address2, 0, openingHours, "Geld storten en opnemen", "GELDMAAT"));
		resultantListOfAtm.add(new Atm(address3, 0, openingHours, "Geld storten en opnemen", "ING"));
		resultantListOfAtm.add(new Atm(address4, 0, openingHours, "Geldautomaat", "ING"));
		
		return resultantListOfAtm;
	}
	
}
