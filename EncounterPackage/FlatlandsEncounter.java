package EncounterPackage;
import CoordinatesPackage.Coordinates;
import PlayerPackage.PlayerLocation;
public class FlatlandsEncounter extends Encounter implements Observer {

    public FlatlandsEncounter(String name, Coordinates encounterCoordinates, String nearbyAlert, PlayerLocation playerLocation,String shortName) {
        super(name, encounterCoordinates, nearbyAlert,shortName);
        playerLocation.registerObserver(this);
    }
    @Override
    public void effect() {
        //No effect
    }
    @Override
    public void onEnterPrint() {
        System.out.println("Nothing to see here");
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