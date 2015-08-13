package org.thoughtworks.parking;

public interface Subscriber {

	public void getNotified(ParkingLot parkingLot,SubscriptionType type);

	
}
