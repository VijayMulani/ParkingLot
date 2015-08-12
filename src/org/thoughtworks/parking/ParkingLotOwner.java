package org.thoughtworks.parking;

import java.util.HashSet;
import java.util.Set;

public class ParkingLotOwner implements ParkingLotFullSubscriber,ParkingLotAvailableSubscriber {

	private Set<ParkingLot> parkingLots;
	
	public ParkingLotOwner() {
		
		this.parkingLots = new HashSet<ParkingLot>();
	}
	
	

	public void addNewParkingLot(ParkingLot parkingLot) {
		
		parkingLots.add(parkingLot);
	}


	public boolean checkIfFull(ParkingLot parkingLot) {
		return (!parkingLot.isParkingSpaceAvailable());
	}



	@Override
	public void getParkingFullNotification(ParkingLot parkingLot) {
		
		
	}



	@Override
	public void getParkingAvailableNotification(ParkingLot parkingLot) {
		
		
	}



}
