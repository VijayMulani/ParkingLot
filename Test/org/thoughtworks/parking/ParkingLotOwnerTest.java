package org.thoughtworks.parking;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotOwnerTest {

	ParkingLot parkingLot;
	ParkingLot parkingLot2;
	ParkingLotOwner Swetha;
	
	@Before
	public void setup(){
		parkingLot=new ParkingLot(1);
		parkingLot2=new ParkingLot(1);
		Swetha=new ParkingLotOwner();
		Swetha.addNewParkingLot(parkingLot);
		Swetha.addNewParkingLot(parkingLot2);
		
		parkingLot.addAvailableParkingSpace(1);
	}
	
	@After
	public void cleanUp(){
		parkingLot=null;
		Swetha=null;
	}
	
	@Test
	public void testWhetherParkingLotIsFull() {
		Car car=new Car("swetha");
		parkingLot.parkCar(car);
		Assert.assertTrue(Swetha.checkIfFull(parkingLot2));
		Assert.assertTrue(Swetha.checkIfFull(parkingLot));
	}
	
	@Test
	public void testWhetherParkingLotIsNotFull() {
		
		Assert.assertFalse(Swetha.checkIfFull(parkingLot));
	}
	
	@Test
	public void testWhetherParkingLotIsNotFullAfterUnpark() {
		Car car=new Car("swetha");
		parkingLot.unpark(parkingLot.parkCar(car));
		
		Assert.assertFalse(Swetha.checkIfFull(parkingLot));
	}

}
