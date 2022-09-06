package com.bezkoder.spring.jwt.mongodb.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TarifZone {

	@Id
	private String id = UUID.randomUUID().toString();
	
	private String pays;
	private List<Tarif> tarifs;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public List<Tarif> getTarifs() {
		return tarifs;
	}
	public void setTarifs(List<Tarif> tarifs) {
		this.tarifs = tarifs;
	}
	
	
	
}
