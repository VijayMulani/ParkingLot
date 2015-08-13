package org.thoughtworks.parking;

import java.util.List;

public class SearchMaxCapacityWala implements SearchCriteria{

	@Override
	public ParkingLot search(List<ParkingLot> parkingLots) {
		ParkingLot p1=null;
		int max=0;
		for(ParkingLot p:parkingLots){
			if(p.getNoOfParkingSpaces()>=max)
			{
				System.out.println("IN LOOP");
				
				p1=p;
	
				max=p.getNoOfParkingSpaces();
				
			}
			
		}
		return p1;
	}

}
