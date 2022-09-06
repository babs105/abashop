package com.bezkoder.spring.jwt.mongodb.service;

import java.util.List;

import com.bezkoder.spring.jwt.mongodb.models.TarifZone;

public interface TarifZoneService {
	
	TarifZone createTarifZone(TarifZone tarifZone);
	List<TarifZone> getAllTarifZone();
	List<TarifZone> getAllTarifZoneByPays(String pays);
    TarifZone getTarifZoneById(String id);

}
