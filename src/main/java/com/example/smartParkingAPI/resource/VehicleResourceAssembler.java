package com.example.smartParkingAPI.resource;

//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.hateoas.EntityLinks;
//import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.example.smartParkingAPI.domain.Vehicles;

@Component
public class VehicleResourceAssembler extends ResourceAssembler<Vehicles, VehicleResource> {
	
//	@Autowired
//	protected EntityLinks entityLinks;
//
//	private static final String UPDATE_REL = "update";
//	private static final String DELETE_REL = "delete";

	@Override
	public VehicleResource toResource(Vehicles vehicles) {
		
		VehicleResource resource = new VehicleResource(vehicles);
		
//		final Link selfLink = entityLinks.linkToSingleResource(vehicles);
//		
//		resource.add(selfLink.withSelfRel());
//		resource.add(selfLink.withRel(UPDATE_REL));
//		resource.add(selfLink.withRel(DELETE_REL));
		
		return resource;
	}
}

