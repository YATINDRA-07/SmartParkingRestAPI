package com.example.smartParkingAPI.domain;

import java.io.Serializable;

public class Vehicles implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vehicle_no;
	private String lot_name;
	private String mallId;
	//private String id;
	private int duration;
	
	public String getVehicle_no() {
		return vehicle_no;
	}
	//@Override
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getLot_name() {
		return lot_name;
	}

	public void setLot_name(String lot_name) {
		this.lot_name = lot_name;
	}

	public String getMallId() {
		return mallId;
	}

	public void setMallId(String mallId) {
		this.mallId = mallId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
//	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return vehicle_no;
	}
//	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.vehicle_no = id;
	}

}
