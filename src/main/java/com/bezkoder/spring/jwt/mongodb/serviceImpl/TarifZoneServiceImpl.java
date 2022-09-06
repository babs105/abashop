package com.bezkoder.spring.jwt.mongodb.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.models.TarifZone;
import com.bezkoder.spring.jwt.mongodb.repository.TarifZoneRepository;
import com.bezkoder.spring.jwt.mongodb.service.TarifZoneService;

@Service
public class TarifZoneServiceImpl implements TarifZoneService {
	
	@Autowired
	TarifZoneRepository tarifZoneRepository;

	@Override
	public TarifZone createTarifZone(TarifZone tarifZone) {
		// TODO Auto-generated method stub
		return tarifZoneRepository.save(tarifZone);
	}

	@Override
	public List<TarifZone> getAllTarifZone() {
		// TODO Auto-generated method stub
		return tarifZoneRepository.findAll();
	}

	@Override
	public List<TarifZone> getAllTarifZoneByPays(String pays) {
		// TODO Auto-generated method stub
		return tarifZoneRepository.findAllByPays(pays);
	}

	@Override
	public TarifZone getTarifZoneById(String id) {
		// TODO Auto-generated method stub
		return tarifZoneRepository.findById(id).get();
	}

}
