package com.bezkoder.spring.jwt.mongodb.models;

public class Tarif {
	
	private String zone;
	private double tarif;
	
	
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public double getTarif() {
		return tarif;
	}
	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

}
