package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.models.TarifZone;

public interface TarifZoneRepository  extends MongoRepository<TarifZone, String>  {
	
	List<TarifZone> findAllByPays(String pays);

}
