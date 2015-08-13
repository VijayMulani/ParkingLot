package org.thoughtworks.parking;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ParkingTestNew {

	private Subscriber Swetha;
	private Subscriber FBIVijay;
	private ParkingLot parkingLot;

	@Before
	public void setup()
	{
		Swetha=mock(GarageOwner.class);
		parkingLot=new ParkingLot(1);
		((GarageOwner)Swetha).addNewParkingLot(parkingLot);
		List<SubscriptionType> subscriptionList=new ArrayList<SubscriptionType>();
		subscriptionList.add(SubscriptionType.FULL);
		subscriptionList.add(SubscriptionType.AVAILABLE);
		subscriptionList.add(SubscriptionType.EIGHTY_PERCENT);
		parkingLot.subscribe(subscriptionList, Swetha);

	}
	@After
	public void cleanup()
	{
		parkingLot=null;
		Swetha=null;
	}
	@Test
	public void whetherFullNotificationIsSent()
	{

		Car car=new Car("abc");
		parkingLot.parkCar(car);
		Mockito.verify(Swetha,times(1)).getNotified(parkingLot,SubscriptionType.FULL);
		//Mockito.verify(Swetha,times(1)).getNotified(parkingLot,SubscriptionType.FULL);
	}
	@Test
	public void whetherAvailableNotificationIsSent()
	{
		Car car=new Car("abc");

		parkingLot.unpark(parkingLot.parkCar(car));
		Mockito.verify(Swetha,times(1)).getNotified(parkingLot,SubscriptionType.AVAILABLE);
	}
	@Test
	public void whether80PercentFullNotificationIsSent()
	{
		Car car=new Car("abc");
		
		parkingLot.parkCar(car);
		Mockito.verify(Swetha,times(1)).getNotified(parkingLot,SubscriptionType.EIGHTY_PERCENT);
	}
	@Test
	public void whetherSuccessfullyUnsubscribed()
	{
		Car car=new Car("abc");

		Token token=parkingLot.parkCar(car);
		List<SubscriptionType> unsubscribeList=new ArrayList<SubscriptionType>();
		unsubscribeList.add(SubscriptionType.AVAILABLE);
		parkingLot.unsubscribe(unsubscribeList, Swetha);
		parkingLot.unpark(token);
		Mockito.verify(Swetha,Mockito.never()).getNotified(parkingLot,SubscriptionType.AVAILABLE);
		
		
	}
}
