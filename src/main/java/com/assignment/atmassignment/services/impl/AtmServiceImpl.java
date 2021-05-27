package com.assignment.atmassignment.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assignment.atmassignment.exceptions.AtmExistException;
import com.assignment.atmassignment.exceptions.RestClientConsumerException;
import com.assignment.atmassignment.pojos.Atm;
import com.assignment.atmassignment.services.IAtmService;
import com.assignment.atmassignment.services.aspects.LoggingPointCut;
import com.assignment.atmassignment.util.RestClientUtil;


/**
 * This class consist manipulation operations over set of Atm
 * @author Krishna
 * */
@Service(AtmServiceImpl.SERVICE_NAME)
public class AtmServiceImpl implements IAtmService {

	public static final String SERVICE_NAME = "atmServiceImpl";
	
	private static Logger logger = LoggerFactory.getLogger(AtmServiceImpl.class);
	
	List<Atm> listOfAtms = new ArrayList<Atm>();

	/**
	 * This post-construct method initialise list Atms. By fetching it from ING server
	 * if it fails to retrieve it from ING server it creates few dummy Atms
	 * */
	@LoggingPointCut
	@PostConstruct
	public void initializeAtms() {
		try {
			listOfAtms.addAll(RestClientUtil.initializeAtmsWitRestApi());
		} catch (RestClientConsumerException ex) {
			logger.warn("error while initializing list of atms. Initializing with custom objects");
			listOfAtms.addAll(RestClientUtil.initializeAtmCustomObjects());
		}
	}

	/**
	 * This method provides list of all the available Atms
	 * */
	@Override
	@LoggingPointCut
	public List<Atm> getAllAtms() {
		return listOfAtms;
	}
	
	/**
	 * this method filters Atm by specified type
	 * @param : String type
	 * @return: List<Atm>
	 * */
	@Override
	@LoggingPointCut
	public List<Atm> getAtmsByType(String type) {
		return listOfAtms.stream().filter(item -> type.equals(item.getType()))
				.collect(Collectors.toList());
	}

	/**
	 * This method creates a single Atm at time. 
	 * while creating Atm if it founds that Atm already exist it throws and {@link AtmExistException}
	 * @param : {@link Atm}
	 * @return : {@link Atm}
	 * @throws : {@link AtmExistException}  
	 * */
	@Override
	@LoggingPointCut
	public Atm createAtm(Atm atm) throws AtmExistException {
		if(listOfAtms.contains(atm)) {
			throw new AtmExistException("Atm already exist "+ atm.toString());
		}else {
			listOfAtms.add(atm);
			atm.setStatus("SUCCESS");
		}
		return atm;
	}

	/**
	 * This method pushes set of list which is valid enough.
	 * if it founds receivedList consist indifferent city name than the received parameter
	 * then it marks it as INVALID by updating Status property of Atm as INVALID
	 * if it founds Atm already exists , it marks it as DUPLICATE by updating status proerty
	 * if it founds Atm is valid it marks it as SUCCESS in status property
	 * @param : List<Atm>
	 * @param : String city
	 * @return : List<Atm>
	 * */
	@Override
	@LoggingPointCut
	public List<Atm> createAtmsWithRespectToCity(List<Atm> receivedAtmsList,String city) {

		List<Atm> resultantAtms = new ArrayList<Atm>();

		Map<Boolean, List<Atm>> partitionedObject = receivedAtmsList.stream()
				.collect(Collectors.partitioningBy(item -> city.equalsIgnoreCase(item.getAddress().getCity())));
		List<Atm> invalidAtms = partitionedObject.get(false);
		List<Atm> intermediateValidAtms = partitionedObject.get(true);

		Map<Boolean, List<Atm>> partitionedObjectAfterRemovingInvalidObjects = intermediateValidAtms.stream()
				.collect(Collectors.partitioningBy(item -> listOfAtms.contains(item)));
		List<Atm> duplicateAtms = partitionedObjectAfterRemovingInvalidObjects.get(true);
		List<Atm> validAtms = partitionedObjectAfterRemovingInvalidObjects.get(false);
		
		//Adding Validated Atms to list
		listOfAtms.addAll(validAtms);

		resultantAtms.addAll(validAtms.stream().map(obj -> {
			obj.setStatus("SUCCESS");
			return obj;
		}).collect(Collectors.toList()));
		
		resultantAtms.addAll(duplicateAtms.stream().map(obj -> {
			obj.setStatus("DUPLICATE");
			return obj;
		}).collect(Collectors.toList()));
		
		resultantAtms.addAll(invalidAtms.stream().map(obj -> {
			obj.setStatus("INVALID");
			return obj;
		}).collect(Collectors.toList()));

		return resultantAtms;
	}

}
