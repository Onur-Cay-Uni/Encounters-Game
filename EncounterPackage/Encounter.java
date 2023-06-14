package EncounterPackage;

import CoordinatesPackage.Coordinates;
import PlayerPackage.Player;

public abstract class Encounter{

	Coordinates encounterCoordinates;
	String name;
	String nearbyAlert;
	String shortName;
	Player player = Player.getOrCreateInstance();

	@Override
	public String toString() {
		return name;
	}

	protected Encounter(String name, Coordinates encounterCoordinates, String nearbyAlert, String shortName){
		this.name = name;
		this.encounterCoordinates = encounterCoordinates;
		this.nearbyAlert = nearbyAlert;
		this.shortName = shortName;
	}
	public void printEncounterDetails() {
		System.out.println(name + " "+ encounterCoordinates.toString());
	}


	protected void effect(){
		//For subclasses
	}

	protected void onEnterPrint(){
		//For subclasses
	}

	public String getShortName(){
		return shortName;
	}

	protected String isNearBy(Coordinates playerCoordinates){
		if(encounterCoordinates.isEast(playerCoordinates)){
			return "East";
		}
		if(encounterCoordinates.isNorth(playerCoordinates)){
			return "North";
		}
		if(encounterCoordinates.isWest(playerCoordinates)){
			return "West";
		}
		if(encounterCoordinates.isSouth(playerCoordinates)){
			return "South";
		}
		return null;
	}


}

