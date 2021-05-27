package com.assignment.atmassignment.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.atmassignment.exceptions.AtmExistException;
import com.assignment.atmassignment.pojos.Atm;
import com.assignment.atmassignment.services.IAtmService;
import com.assignment.atmassignment.services.aspects.LoggingPointCut;


/**
 * This class exposes rest end points to provide ATM details
 * 
 * End points and HTTP methods are as below which returns JSON as response
 * 1. method : GET, URL : "/getAllAtms", Response : @Atm[]
 * 2. method : GET, URL : "/getAllAtms/{type}", Response : @Atm[] 
 * 3. method : POST, URL : "/createAtm", Response : @Atm
 * 4. method : POST, URL : "/createAtmsForCity/{city}", Response : @Atm[]
 * @author Krishna
 * */
@Validated
@RestController
public class AtmController {

	@Autowired
	IAtmService atmService;

	/**
	 * This method fetches list of all the available ATMs. And sends as JSON response to user
	 * 
	 * @return : @Atm[]
	 * */
	@LoggingPointCut
	@GetMapping("/getAllAtms")
	public List<Atm> getAllAtms() {
		return atmService.getAllAtms();
	}

	/**
	 * This method fetches list of all the available ATMs of Specified type. And sends as JSON response to user
	 * @param : String type
	 * @return : @Atm[]
	 * */
	@LoggingPointCut
	@GetMapping("/getAllAtms/{type}")
	public List<Atm> getAtmsByType(@PathVariable String type) {
		return atmService.getAtmsByType(type);
	}

	/**
	 * This method creates and Atm object. Accepts JSON of Type @Atm
	 * There are few validations 
	 * @Atm.Address is mandatory
	 * @Atm.functionality is mandatory
	 * @Atm.type is mandatory 
	 * @atm.address.city is mandatory
	 * @Atm POJO has optional property as 'status' which can have values SUCCESS, INVALID, DUPLICATE as part of resonse object
	 * 
	 * This method Accepts valid Atm pojo and pushes it to existing set of Atms.
	 * If it founds Atm already exist it will throw AtmExistException
	 * 
	 * @param: {@link Atm}
	 * @return: {@link Atm}
	 * @throws: {@link AtmExistException}
	 * */
	@LoggingPointCut
	@PostMapping("/createAtm")
	public Atm createAtm1(@RequestBody @Valid Atm atm) throws AtmExistException {
		return atmService.createAtm(atm);
	}

	/**
	 * This method creates List of {@link Atm}, validating data with specified city as parameter.
	 * If it founds data to having different city than specified param, it marks it as INVALID by updating {@link Atm.status property}
	 * If it founds {@link Atm} already exists, it marks it as DUPLICATE by updating {@link Atm.status property}
	 * once validations is cleared remaining set of Atms are merged into existing ones
	 * 
	 * @param : List<Atm>
	 * @return : List<Atm>
	 * */
	@LoggingPointCut
	@PostMapping("/createAtmsForCity/{city}")
	public List<Atm> createAtm(@RequestBody @Valid List<Atm> atms, @PathVariable(value = "city") String city) {
		return atmService.createAtmsWithRespectToCity(atms, city);
	}

}
