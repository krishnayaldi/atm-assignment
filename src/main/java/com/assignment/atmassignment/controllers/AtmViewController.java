package com.assignment.atmassignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.assignment.atmassignment.services.IAtmService;
import com.assignment.atmassignment.services.aspects.LoggingPointCut;


/**
 * This controller creates plain html view of Atms by provided type
 * 
 * @author Krishna
 */
@Controller
public class AtmViewController {

	@Autowired
	IAtmService atmService;

	/**
	 * This method fetches all existing Atms by type and renders it on html page
	 * 
	 * @param : String type
	 */
	@LoggingPointCut
	@GetMapping("/showAtmsByType/{type}")
	public String showAtmsByType(Model model, @PathVariable("type") String type) {

		model.addAttribute("atmsByType", atmService.getAtmsByType(type));

		return "list-of-atms-by-type";
	}
	
}