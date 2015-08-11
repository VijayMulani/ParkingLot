package org.thoughtworks.parking;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thoughtworks.exceptions.CarAlreadyParkedException;

public class ParkingTest {

	private ParkingLot parkingLot;
	@Before
	public void setup()
	{
		parkingLot=new ParkingLot(10);
		parkingLot.addAvailableParkingSpace(1);
		parkingLot.addAvailableParkingSpace(2);
		parkingLot.addAvailableParkingSpace(3);
		parkingLot.addAvailableParkingSpace(4);
		parkingLot.addAvailableParkingSpace(5);
		parkingLot.addAvailableParkingSpace(6);
		parkingLot.addAvailableParkingSpace(7);
		parkingLot.addAvailableParkingSpace(8);
		parkingLot.addAvailableParkingSpace(9);
		parkingLot.addAvailableParkingSpace(10);	
	}
	@After
	public void cleanup()
	{
		parkingLot=null;
	}
	
	
	
	
	
	/*@Test
	public void whetherSpaceIsAdded()
	{
		int index=parkingLot.isParkingSpaceAvailable();
		
		Assert.assertEquals(1,index );

	}
	@Test 
	public void whetherSpaceIsAvailable()
	{
		
		int index=parkingLot.isParkingSpaceAvailable();
		boolean b=index>0?true:false;
		Assert.assertTrue(b);
	}
	@Test 
	public void whetherSpaceIsNotAvailable()
	{
		ParkingLot parkingLot=new ParkingLot(0);
		
		int index=parkingLot.isParkingSpaceAvailable();
		
		boolean b=index>0?true:false;
		Assert.assertFalse(b);
	}*/
	
	
	
	
	@Test
	public void whetherParked()
	{
		Car car=new Car("abc");
		int index=parkingLot.parkCar(car);
		boolean b=index>0?true:false;
		
		
		Assert.assertTrue(b);
	}

	@Test
	public void whetherNotParked()
	{
		ParkingLot parkingLot=new ParkingLot(0);
		Car car=new Car("abc");
		int index=parkingLot.parkCar(car);
		boolean b=index>0?true:false;
		
		
		Assert.assertFalse(b);
	}




	@Test(expected=CarAlreadyParkedException.class)
	public void cannotParkDuplicate(){
		Car car=new Car("abc");
		int index=parkingLot.parkCar(car);
		parkingLot.parkCar(car);
	}

	@Test
	public void shouldUnpark(){
		Car car=new Car("abc");
		int index=parkingLot.parkCar(car);
		
		
		Assert.assertTrue(parkingLot.unpark(car));

	}
	
	@Test
	public void shouldNotUnpark(){
		Car car=new Car("abc");	
		
		
		Assert.assertFalse(parkingLot.unpark(car));

	}
}
