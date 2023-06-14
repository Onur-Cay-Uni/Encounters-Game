package EncounterPackage;

import CoordinatesPackage.Coordinates;
import PlayerPackage.PlayerLocation;

public class LakeEncounter extends Encounter implements Observer {

    protected LakeEncounter(String name, Coordinates encounterCoordinates, String nearbyAlert, PlayerLocation playerLocation, String shortName) {
        super(name, encounterCoordinates, nearbyAlert,shortName);
        playerLocation.registerObserver(this);
    }

    @Override
    protected void effect() {
        System.out.println("You take 10 damage");
        player.takeDamage(10);
        player.printHealth();
    }

    @Override
    protected void onEnterPrint() {
        System.out.println("The water is icy cold!");
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
