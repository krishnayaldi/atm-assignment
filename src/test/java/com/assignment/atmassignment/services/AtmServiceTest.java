package com.assignment.atmassignment.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import com.assignment.atmassignment.pojos.Atm;
import com.assignment.atmassignment.services.impl.AtmServiceImpl;
import com.assignment.atmassignment.util.RestClientUtil;


public class AtmServiceTest {

	Logger logger = LoggerFactory.getLogger(AtmServiceTest.class);

	@Mock
	AtmServiceImpl atmService;

	List<Atm> testListOfAtm = new ArrayList<Atm>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		testListOfAtm = RestClientUtil.initializeAtmCustomObjects();
		ReflectionTestUtils.setField(atmService, "listOfAtms", testListOfAtm);
	}

	@Test
	public void testGetAllAtms() {
		assertNotNull(atmService.getAllAtms());
		when(atmService.getAllAtms()).thenCallRealMethod();
		assertEquals(testListOfAtm.size(), atmService.getAllAtms().size());
	}

	@Test
	public void testGetAtmsByType() {
		when(atmService.getAtmsByType(Mockito.anyString())).thenCallRealMethod();
		List<Atm> recievedList = atmService.getAtmsByType("ING");
		assertEquals(2, recievedList.size());
	}

	@Test
	public void testCreateAtm() throws Exception {
		when(atmService.createAtm(Mockito.any(Atm.class))).thenCallRealMethod();
		int beforeCount = testListOfAtm.size();
		atmService.createAtm(AtmUtil.getDummyAtm());
		assertEquals(beforeCount + 1, testListOfAtm.size());
	}

	@Test
	public void testCreateAtmsWithRespectToCity() {
		List<Atm> atmList = new ArrayList<>();
		atmList.add(AtmUtil.getDummyAtm());
		atmList.add(AtmUtil.getDummyAtm());
		Atm atm1 = AtmUtil.getDummyAtm();
		atm1.getAddress().setCity("pune");
		atm1.setFunctionality("Geld storten en opnemen");
		atmList.add(atm1);
		Atm atm2 = AtmUtil.getDummyAtm();
		atm2.getAddress().setCity("pune");
		atm2.setType("GELDMAAT");
		atmList.add(atm2);
		atmList.add(AtmUtil.getDummyAtm());
		atmList.add(testListOfAtm.get(0));
		int beforeCount = testListOfAtm.size();
		when(atmService.createAtmsWithRespectToCity(Mockito.any(List.class), Mockito.anyString())).thenCallRealMethod();
		List<Atm> resultantList = atmService.createAtmsWithRespectToCity(atmList, "pune");

		List<Atm> successList = resultantList.stream().filter(item -> "SUCCESS".equalsIgnoreCase(item.getStatus()))
				.collect(Collectors.toList());
		List<Atm> invalidList = resultantList.stream().filter(item -> "INVALID".equalsIgnoreCase(item.getStatus()))
				.collect(Collectors.toList());
		List<Atm> duplicateList = resultantList.stream().filter(item -> "DUPLICATE".equalsIgnoreCase(item.getStatus()))
				.collect(Collectors.toList());

		assertEquals(beforeCount + 1, testListOfAtm.size());
		assertEquals(1, successList.size());
		assertEquals(4, invalidList.size());
		assertEquals(1, duplicateList.size());
	}
}
