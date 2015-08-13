package org.thoughtworks.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.thoughtworks.exceptions.CarAlreadyParkedException;
import org.thoughtworks.exceptions.CarNotParkedException;
import org.thoughtworks.exceptions.ParkingSpaceFullException;

public class ParkingLot {
	private int noOfParkingSpaces;
	private ParkingLotOwner owner;
	private Set<Integer> availableParkingSpaces;
	private Map<Token,Car> allottedParkingSpaces;
	/*	private Set<ParkingLotFullSubscriber> fullSubscribers;
	private Set<ParkingLotAvailableSubscriber> availableSubscribers;*/
	private Map<SubscriptionType,List<Subscriber>> subscribers;
	public ParkingLot(int noOfParkingSpaces) {
		super();
		this.noOfParkingSpaces = noOfParkingSpaces;
		allottedParkingSpaces=new HashMap<Token,Car>(noOfParkingSpaces);
		availableParkingSpaces=new HashSet<Integer>(noOfParkingSpaces);
		/*fullSubscribers=new HashSet<ParkingLotFullSubscriber>();
		availableSubscribers=new HashSet<ParkingLotAvailableSubscriber>();*/
		subscribers=new HashMap<SubscriptionType,List<Subscriber>>();


		for(int i=1;i<=noOfParkingSpaces;i++)
			availableParkingSpaces.add(i);
	}


	public boolean isParkingSpaceAvailable()
	{
		if(availableParkingSpaces.size()>0)
			return true;
		return false;
	}
	public void addAvailableParkingSpace(int index)
	{
		availableParkingSpaces.add(index);
	}

	public int getNumberOfFreeSpaces(){
		return availableParkingSpaces.size();
				
	}
	
	public int getNoOfParkingSpaces() {
		return noOfParkingSpaces;
	}


	public Token parkCar(Car car)
	{
		boolean isParkingSpaceAvailable=isParkingSpaceAvailable();
		Token token=null;
		if(isParkingSpaceAvailable&&!isAlreadyParked(car))
		{
			int lotNumber=generateLotNumber();
			token=new Token(lotNumber);
			availableParkingSpaces.remove(lotNumber);
			if(availableParkingSpaces.size()==0)
				notifySubscriber(SubscriptionType.FULL);
			if(availableParkingSpaces.size()==0.8*noOfParkingSpaces)
				notifySubscriber(SubscriptionType.EIGHTY_PERCENT);
			allottedParkingSpaces.put(token, car);
		}
		else
			throw new ParkingSpaceFullException();
		return token;
	}

	public boolean isAlreadyParked(Car car)
	{
		if(allottedParkingSpaces.containsValue(car))
			throw new CarAlreadyParkedException();
		return false;
	}

	public int generateLotNumber(){
		Iterator<Integer> it=availableParkingSpaces.iterator();
		if(it.hasNext()){
			return it.next();
		}
		else
			throw new ParkingSpaceFullException();

	}

	/*public void subscribeForAvailable(ParkingLotAvailableSubscriber subscriber){
		availableSubscribers.add(subscriber);
	}

	public void subscribeForFull(ParkingLotFullSubscriber subscriber){
		fullSubscribers.add(subscriber);
	}*/

	public void subscribe(List<SubscriptionType> subscriptionList,Subscriber subscriber)
	{
		for(SubscriptionType type:subscriptionList)
		{
			if(!subscribers.containsKey(type))
				subscribers.put(type, new ArrayList<Subscriber>());
			subscribers.get(type).add(subscriber);
		}
	}
	public void unsubscribe(List<SubscriptionType> subscriptionList,Subscriber subscriber)
	{
		for(SubscriptionType type:subscriptionList)
		{
			subscribers.get(type).remove(subscriber);
		}
	}

	public Car unpark(Token token) {

		Car car=allottedParkingSpaces.get(token);
		if(car==null)
			throw new CarNotParkedException();
		else
		{
			allottedParkingSpaces.remove(token);
			availableParkingSpaces.add(token.getLotNumber());
			if(availableParkingSpaces.size()==1)
				notifySubscriber(SubscriptionType.AVAILABLE);
			return car;
		}


	}

	public void notifySubscriber(SubscriptionType type){
		ArrayList<Subscriber> subsribers=(ArrayList<Subscriber>) subscribers.get(type);
		for(Subscriber subscriber:subsribers)
			subscriber.getNotified(this,type);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((allottedParkingSpaces == null) ? 0 : allottedParkingSpaces
						.hashCode());
		result = prime
				* result
				+ ((availableParkingSpaces == null) ? 0
						: availableParkingSpaces.hashCode());
		result = prime * result + noOfParkingSpaces;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingLot other = (ParkingLot) obj;
		if (allottedParkingSpaces == null) {
			if (other.allottedParkingSpaces != null)
				return false;
		} else if (!allottedParkingSpaces.equals(other.allottedParkingSpaces))
			return false;
		if (availableParkingSpaces == null) {
			if (other.availableParkingSpaces != null)
				return false;
		} else if (!availableParkingSpaces.equals(other.availableParkingSpaces))
			return false;
		if (noOfParkingSpaces != other.noOfParkingSpaces)
			return false;
		return true;
	}









}
