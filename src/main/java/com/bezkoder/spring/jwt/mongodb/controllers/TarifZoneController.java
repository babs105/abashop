package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bezkoder.spring.jwt.mongodb.models.TarifZone;
import com.bezkoder.spring.jwt.mongodb.service.TarifZoneService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tarifZone")
public class TarifZoneController {

	@Autowired
	TarifZoneService tarifZoneService;

	@RequestMapping(value = "/create", method = { RequestMethod.POST,
			RequestMethod.OPTIONS }, produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public TarifZone addTarifZone(@RequestBody TarifZone tarifZone) {
		return tarifZoneService.createTarifZone(tarifZone);

	}

	@GetMapping("/getAllTarifZone")
	public List<TarifZone> getAllTarifZone() {
		return tarifZoneService.getAllTarifZone();
		// return productService.getAllProductImg();
	}

	@RequestMapping(value = "/getAllTarifZoneByPays/{pays}", method = { RequestMethod.GET,
			RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<TarifZone> getAllTarifZoneByPays(@PathVariable String pays) {
		return tarifZoneService.getAllTarifZoneByPays(pays);
	}

	@RequestMapping(value = "/getTarifZoneById/{id}", method = { RequestMethod.GET,
			RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public TarifZone getTarifZoneById(@PathVariable String id) {
		return tarifZoneService.getTarifZoneById(id);
	}
}
