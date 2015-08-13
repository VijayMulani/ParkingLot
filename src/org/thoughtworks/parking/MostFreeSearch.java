package org.thoughtworks.parking;

import java.util.List;

public class MostFreeSearch implements SearchCriteria{

	@Override
	public ParkingLot search(List<ParkingLot> parkingLots) {
		System.out.println(parkingLots);
		ParkingLot p1=null;
		int max=0;
		for(ParkingLot p:parkingLots){
			if(p.getNumberOfFreeSpaces()>=max)
			{
				System.out.println("IN LOOP");
				
				p1=p;
	
				max=p.getNumberOfFreeSpaces();
				System.out.println(max);
			}
			
		}
		return p1;
	}

}
