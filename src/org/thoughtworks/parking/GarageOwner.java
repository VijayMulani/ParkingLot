package org.thoughtworks.parking;

import java.util.HashSet;
import java.util.Set;

public class GarageOwner implements Subscriber {

	private Set<ParkingLot> parkingLots;
	public GarageOwner() {
		// TODO Auto-generated constructor stub
		parkingLots = new HashSet<ParkingLot>();
	}
	@Override
	public void getNotified(ParkingLot parkingLot,SubscriptionType type) {
		

	}
	public void addNewParkingLot(ParkingLot parkingLot) {

		parkingLots.add(parkingLot);
	}


	public boolean checkIfFull(ParkingLot parkingLot) {
		return (!parkingLot.isParkingSpaceAvailable());
	}

}
