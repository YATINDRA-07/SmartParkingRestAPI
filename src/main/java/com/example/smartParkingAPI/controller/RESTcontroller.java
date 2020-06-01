package com.example.smartParkingAPI.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartParkingAPI.domain.Vehicles;
import com.example.smartParkingAPI.repository.VehicleRepository;
import com.example.smartParkingAPI.resource.VehicleResource;
import com.example.smartParkingAPI.resource.VehicleResourceAssembler;

@CrossOrigin(origins = "*")
@RestController
//@org.springframework.hateoas.ExposesResourceFor(Vehicles.class)
@RequestMapping(value = "/vehicles", produces = "application/json")
public class RESTcontroller {
	
	@Autowired
	private VehicleRepository repository;
	
	@Autowired
	private VehicleResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<VehicleResource>> findAllVehicles() {
		List<Vehicles> vehicles = repository.findAll();
		return new ResponseEntity<>(assembler.toResourceCollection(vehicles), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<VehicleResource> createVehicle(@RequestBody Vehicles vehicle) {
		Vehicles createdVehicle = repository.create(vehicle);
		return new ResponseEntity<>(assembler.toResource(createdVehicle), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
		boolean wasDeleted = repository.delete(id);
		HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
		return new ResponseEntity<>(responseStatus);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<VehicleResource> updateVehicle(@PathVariable String id, @RequestBody Vehicles updatedVehicle) {
		boolean wasUpdated = repository.update(id, updatedVehicle);	
		if (wasUpdated) {
			return findVehicleById(id);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ResponseEntity<VehicleResource> findVehicleById(@RequestParam(name="vc_no", required=true, defaultValue="") String vehicle_no) {
		Vehicles vehicle = repository.findById(vehicle_no);
		if (vehicle!=null) {
			return new ResponseEntity<>(assembler.toResource(vehicle), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}