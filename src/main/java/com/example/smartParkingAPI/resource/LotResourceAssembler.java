package com.example.smartParkingAPI.resource;


//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.hateoas.EntityLinks;
//import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.example.smartParkingAPI.domain.ParkingLot;

@Component
public class LotResourceAssembler extends ResourceAssembler<ParkingLot, LotResource> {
	
//	@Autowired
//	protected EntityLinks entityLinks;

//	private static final String UPDATE_REL = "update";
//	private static final String DELETE_REL = "delete";

	@Override
	public LotResource toResource(ParkingLot lot) {
	
		LotResource resource = new LotResource(lot);
		
//		final Link selfLink = entityLinks.linkToSingleResource(lot);
//		
//		resource.add(selfLink.withSelfRel());
//		resource.add(selfLink.withRel(UPDATE_REL));
//		resource.add(selfLink.withRel(DELETE_REL));
//		
		return resource;
	}
}
