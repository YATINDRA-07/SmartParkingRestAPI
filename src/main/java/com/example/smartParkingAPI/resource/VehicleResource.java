package com.example.smartParkingAPI.resource;

//import org.springframework.hateoas.ResourceSupport;

import com.example.smartParkingAPI.domain.Vehicles;
import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class VehicleResource {//extends ResourceSupport{
	private String vehicle_no;
	private String lot_name;
	private String mallId;
	private String id;
	private int duration;
//	@JsonUnwrapped
//    private final Vehicles vehicles;
    
	
	public String getVehicle_no() {
		return vehicle_no;
	}
	public VehicleResource(Vehicles vehicles) {
	this.vehicle_no = vehicles.getVehicle_no();
	this.lot_name = vehicles.getLot_name();
	this.mallId = vehicles.getMallId();
	this.id = vehicles.getId();
	this.duration = vehicles.getDuration();
}
	public String getLot_name() {
		return lot_name;
	}
	public String getMallId() {
		return mallId;
	}
	@JsonProperty("id")
	public String getResourceId() {
		return id;
	}
	public int getDuration() {
		return duration;
	}
		
}