package org.thoughtworks.parking;
import junit.framework.Assert;
import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.thoughtworks.exceptions.CarAlreadyParkedException;
import org.thoughtworks.exceptions.CarNotParkedException;
import org.thoughtworks.exceptions.ParkingSpaceFullException;

public class ParkingTest {

	private ParkingLotFullSubscriber Swetha;
	private ParkingLotFullSubscriber FBIVijay;
	private ParkingLot parkingLot;
	
	@Before
	public void setup()
	{
		Swetha=new ParkingLotOwner();
		parkingLot=new ParkingLot(10);
		parkingLot.subscribeForFull(Swetha);
		
	}
	@After
	public void cleanup()
	{
		parkingLot=null;
		Swetha=null;
	}
	
	
	
	
	
	
	@Test
	public void whetherParked()
	{
		Car car=new Car("abc");
		
		Assert.assertEquals(true, parkingLot.parkCar(car) instanceof Token);
	}

	@Test(expected=ParkingSpaceFullException.class)
	public void whetherNotParked()
	{
		ParkingLot parkingLot=new ParkingLot(0);
		Car car=new Car("abc");
		Token token=parkingLot.parkCar(car);
	
	}




	@Test(expected=CarAlreadyParkedException.class)
	public void cannotParkDuplicate(){
		Car car=new Car("abc");
		Token token=parkingLot.parkCar(car);
		parkingLot.parkCar(car);
	}

	@Test
	public void shouldUnpark(){
		Car car=new Car("abc");
		Token token=parkingLot.parkCar(car);
		
		
		Assert.assertEquals(car,parkingLot.unpark(token));

	}
	
	@Test(expected=CarNotParkedException.class)
	public void shouldNotUnpark(){
		Car car=new Car("abc");	
		Token token=parkingLot.parkCar(car);
		parkingLot.unpark(token);
		
		parkingLot.unpark(token);
	}
	
	@Test
	public void shouldNotifyOwnerOnParkingFull()
	{
		ParkingLotOwner mockedOwner=mock(ParkingLotOwner.class);
		
		ParkingLot parkingLot=new ParkingLot(1);
		parkingLot.subscribeForAvailable(mockedOwner);
		parkingLot.subscribeForFull(mockedOwner);
		
		Car car=new Car("abc");
		Token token=parkingLot.parkCar(car);
		
		
		Mockito.verify(mockedOwner,times(1)).getParkingFullNotification(parkingLot);
		
		
	}
	@Test
	public void shouldNotNotifyMultipleTimes()throws ParkingSpaceFullException
	{
		ParkingLotOwner mockedOwner=mock(ParkingLotOwner.class);
		ParkingLot parkingLot=new ParkingLot(1);
		parkingLot.subscribeForAvailable(mockedOwner);
		parkingLot.subscribeForFull(mockedOwner);
		Car car=new Car("abc");
		Car car2=new Car("abcd");
		try{
		parkingLot.parkCar(car);
		parkingLot.parkCar(car2);
		}catch(ParkingSpaceFullException e)
		{}
		
		
		Mockito.verify(mockedOwner,times(1)).getParkingFullNotification(parkingLot);
	}
	@Test
	public void shouldNotNotifyOwnerWhenSpaceAvailable()
	{
		ParkingLotOwner mockedOwner=mock(ParkingLotOwner.class);
		ParkingLot parkingLot=new ParkingLot(2);
		parkingLot.subscribeForAvailable(mockedOwner);
		parkingLot.subscribeForFull(mockedOwner);
		Car car=new Car("abc");
		Token token=parkingLot.parkCar(car);
		
		Mockito.verify(mockedOwner,never()).getParkingAvailableNotification(parkingLot);
	}
	
	@Test
	public void shouldNotifyOwnerWhenSpaceAvailable()
	{
		ParkingLotOwner mockedOwner=mock(ParkingLotOwner.class);
		ParkingLot parkingLot=new ParkingLot(1);
		parkingLot.subscribeForAvailable(mockedOwner);
		parkingLot.subscribeForFull(mockedOwner);
		Car car=new Car("abc");
		Token token=parkingLot.parkCar(car);
	
		
		parkingLot.unpark(token);
		
		Mockito.verify(mockedOwner,times(1)).getParkingAvailableNotification(parkingLot);
	}
	
	@Test
	public void shouldNotifyOnceOnSpaceAvailable()
	{
		ParkingLotOwner mockedOwner=mock(ParkingLotOwner.class);
		ParkingLot parkingLot=new ParkingLot(2);
		parkingLot.subscribeForAvailable(mockedOwner);
		parkingLot.subscribeForFull(mockedOwner);
		Car car=new Car("abc");
		Car car2=new Car("abcd");
		Token token=parkingLot.parkCar(car);
		Token token2=parkingLot.parkCar(car2);
		
		parkingLot.unpark(token);
		parkingLot.unpark(token2);
		Mockito.verify(mockedOwner,times(1)).getParkingAvailableNotification(parkingLot);
	}
	
	@Test
	public void shouldNotifyFBIWhenFull()
	{
		ParkingLotFullSubscriber mockFBIAgent=mock(FBIAgent.class);
		ParkingLot parkingLot=new ParkingLot(1);
		parkingLot.subscribeForFull(mockFBIAgent);
		Car car=new Car("abc");
		parkingLot.parkCar(car);
		
		Mockito.verify(mockFBIAgent,times(1)).getParkingFullNotification(parkingLot);
	}
	
}
