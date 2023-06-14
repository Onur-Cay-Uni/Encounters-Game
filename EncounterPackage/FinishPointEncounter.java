package EncounterPackage;

import CoordinatesPackage.Coordinates;
import Game.OpenWorld;
import PlayerPackage.PlayerLocation;

public class FinishPointEncounter extends Encounter implements Observer {
    protected FinishPointEncounter(String name, Coordinates encounterCoordinates, String nearbyAlert, PlayerLocation playerLocation, String shortName) {
        super(name, encounterCoordinates, nearbyAlert,shortName);
        playerLocation.registerObserver(this);
    }

    @Override
    protected void effect() {
        OpenWorld.setGameRunning(false);
        System.out.println("Game is now over");
    }

    @Override
    protected void onEnterPrint() {
        System.out.println("You have reached your goal!");
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
