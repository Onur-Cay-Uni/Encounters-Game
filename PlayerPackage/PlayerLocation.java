package PlayerPackage;

import CoordinatesPackage.Coordinates;
import EncounterPackage.Encounter;
import EncounterPackage.Observer;
import LogsPackage.LogAnalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerLocation implements Subject
{
	private Coordinates playerCoordinates;
	private int northSouthBoundary;
	private int eastWestBoundary;

	private LogAnalytics database =  LogAnalytics.getOrCreateInstance();
	private List<Observer> observers;
	
	protected PlayerLocation(int northSouthBoundary, int eastWestBoundary)
	{
		this.northSouthBoundary = northSouthBoundary;
		this.eastWestBoundary = eastWestBoundary;
		this.playerCoordinates = new Coordinates(0,0);
		observers = new CopyOnWriteArrayList<>();

	}
		
	public void changeCoordinates(Coordinates updateCoordinates)
	{
		if (updateCoordinates.getNorthSouth() >= 0 && updateCoordinates.getNorthSouth() <= northSouthBoundary && updateCoordinates.getEastWest()>=0 && updateCoordinates.getEastWest()<=eastWestBoundary)
		{
			playerCoordinates = updateCoordinates;
			System.out.println("You are at location: " + playerCoordinates.toString());
			database.logMove(updateCoordinates);
			notiftyObservers();
		}
		else 
		{
			System.out.println("Out of bounds move attempted - position has not changed, you are at:" + playerCoordinates.toString());
			database.logMove(playerCoordinates);
			notiftyObservers();
		}
	}

	public int getNorthSouthBoundary(){
		return northSouthBoundary;
	}
	public int getEastWestBoundary(){
		return eastWestBoundary;
	}
	public Coordinates getPlayerCoordinates(){
		return playerCoordinates;
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notiftyObservers() {
		for(Observer observer:observers){
			observer.update(playerCoordinates);
		}
	}
	public void notiftyObservers(Encounter e){
		for(Observer observer:observers){
			if (!e.equals(observer)){
				observer.update(playerCoordinates);
			}

		}
	}

	public void removeAllObservers(){
		for(Observer observer:observers){
			removeObserver(observer);
		}
	}
}
