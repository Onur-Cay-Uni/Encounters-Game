package EncounterPackage;

import CoordinatesPackage.Coordinates;
import Game.OpenWorld;
import PlayerPackage.PlayerLocation;

import java.util.ArrayList;

public class WorldShiftEncounter extends Encounter implements Observer {

    private PlayerLocation playerLocation;

    private ArrayList<Encounter> encounters = new ArrayList<Encounter>();

    private boolean callEffect = false;
    protected WorldShiftEncounter(String name, Coordinates encounterCoordinates, String nearbyAlert, PlayerLocation playerLocation, String shortName) {
        super(name, encounterCoordinates, nearbyAlert, shortName);
        playerLocation.registerObserver(this);
        this.playerLocation= playerLocation;
    }

    @Override
    protected void effect() {
        playerLocation.removeAllObservers();
        encounters = EncounterFactory.populateEncounters(playerLocation.getNorthSouthBoundary(),playerLocation.getEastWestBoundary(),playerLocation);
        OpenWorld.setEncounters(encounters);
        System.out.println("You are now teleported to a Flatlands in a different world");
        playerLocation.notiftyObservers(this);
    }

    @Override
    protected void onEnterPrint() {
        System.out.println("You get sucked in to the blue portal!");
    }

    @Override
    public void update(Coordinates playerCoordinates) {
        String playerRelativeLocation = isNearBy(playerCoordinates);
        if (playerCoordinates.isHere(encounterCoordinates)) {
            onEnterPrint();
            effect();
        }
        if(playerRelativeLocation!= null){
            System.out.println("To the "+playerRelativeLocation+" you "+nearbyAlert);
        }

    }

}
