package org.thoughtworks.parking;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotAttendantTest {

	private Subscriber attendantABC;
	private ParkingLot parkingLot;
	

	@Before
	public void setup()
	{
		
		attendantABC=new Attendant();
		parkingLot=new ParkingLot(1);
		((Attendant)attendantABC).addParkingLot(parkingLot);
		

	}
	@After
	public void cleanup()
	{
		parkingLot=null;
		attendantABC=null;
	}
	
	@Test
	public void testIfPArkingLotsAreAdded() {
		Assert.assertEquals(1, ((Attendant)attendantABC).getAvailableLots().size());
		
		
	}
	
	@Test
	public void getAvailableParkingLot(){
		
		Assert.assertTrue(((Attendant) attendantABC).getAvailableParkingLot() instanceof ParkingLot);
		
	}
	

	@Test
	public void testForMostFreeParkingLot() {
		ParkingLot parkingLot2=new ParkingLot(10);
		((Attendant)attendantABC).addParkingLot(parkingLot2);
		MostFreeSearch m=new MostFreeSearch();
		
		Assert.assertEquals(parkingLot2, ((Attendant)attendantABC).getParkingLotByCriteria(m));
	}
	
	@Test
	public void testForMaxCapacityParkingLot(){
		ParkingLot parkingLot2=new ParkingLot(2);
	((Attendant)attendantABC).addParkingLot(parkingLot2);
	parkingLot2.parkCar(new Car("vijays car"));

	SearchMaxCapacityWala m=new SearchMaxCapacityWala();
	
	Assert.assertEquals(parkingLot2, ((Attendant)attendantABC).getParkingLotByCriteria(m));
}

}
