package org.thoughtworks.parking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.thoughtworks.exceptions.CarAlreadyParkedException;

public class ParkingLot {
	private int noOfParkingSpaces;
	public ParkingLot(int noOfParkingSpaces) {
		super();
		allottedParkingSpaces=new HashMap<Integer,Car>(noOfParkingSpaces);
		this.noOfParkingSpaces = noOfParkingSpaces;
		availableParkingSpaces=new HashSet<Integer>(noOfParkingSpaces);
	}
	private Set<Integer> availableParkingSpaces;
	private Map<Integer,Car> allottedParkingSpaces;
	public int isParkingSpaceAvailable()
	{
		Iterator<Integer> it=availableParkingSpaces.iterator();
		if(it.hasNext())
			return it.next();
		return -1;
	}
	public void addAvailableParkingSpace(int index)
	{
		availableParkingSpaces.add(index);
	}
	public int parkCar(Car car)
	{
		int index=isParkingSpaceAvailable();
		availableParkingSpaces.remove(index);
		if(allottedParkingSpaces.containsValue(car))
			throw new CarAlreadyParkedException();
		allottedParkingSpaces.put(index, car);
		
		return index;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean unpark(Car car) {
		if(allottedParkingSpaces.containsValue(car)){
			Set<Integer> indices=allottedParkingSpaces.keySet();
			for(Iterator it=indices.iterator();it.hasNext();)
			{
				Integer key=(Integer)it.next();
				if(car.equals(allottedParkingSpaces.get(key)))
				{
					allottedParkingSpaces.remove(key);
					availableParkingSpaces.add(key);
					return true;
				}
				
			}
			return false;
		}
		else
		return false;
	}
	
	
}
