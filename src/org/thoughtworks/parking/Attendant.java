package org.thoughtworks.parking;

import java.util.ArrayList;
import java.util.List;

public class Attendant implements Subscriber{

	private List<ParkingLot> availableLots;
	
	public Attendant(){
		availableLots=new ArrayList<ParkingLot>();
	}
	

	public ParkingLot getParkingLotByCriteria(SearchCriteria searchCriteria){
		System.out.println(availableLots);
		return searchCriteria.search(availableLots);
		
	}
	public void addParkingLot(ParkingLot parkingLot){
		if(parkingLot.isParkingSpaceAvailable())
			availableLots.add(parkingLot);
		List<SubscriptionType> subscriptionList=new ArrayList<SubscriptionType>();
		subscriptionList.add(SubscriptionType.FULL);
		subscriptionList.add(SubscriptionType.AVAILABLE);
		parkingLot.subscribe(subscriptionList, this);
	}
	

	public List<ParkingLot> getAvailableLots() {
		return availableLots;
	}

	public ParkingLot getAvailableParkingLot(){
		return availableLots.get(0);

	}
	@Override
	public void getNotified(ParkingLot parkingLot, SubscriptionType type) {
		
		switch(type)
		{
		case FULL:
			availableLots.remove(parkingLot);
			break;
		case AVAILABLE:
			availableLots.add(parkingLot);
			break;
		}
		
	}

}
