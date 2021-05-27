/**
 * 
 */
package com.assignment.atmassignment.services;

import java.util.List;

import com.assignment.atmassignment.exceptions.AtmExistException;
import com.assignment.atmassignment.pojos.Atm;

/**
 * @author Krishna
 * 
 */
public interface IAtmService {

	public List<Atm> getAllAtms();

	public List<Atm> getAtmsByType(String type);

	public Atm createAtm(Atm atm) throws AtmExistException;
	
	public List<Atm> createAtmsWithRespectToCity(List<Atm> receivedAtmsList,String city);
}
