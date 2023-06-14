package EncounterPackage;
import CoordinatesPackage.Coordinates;
import PlayerPackage.PlayerLocation;
public class WolfEncounter extends Encounter implements Observer {


    protected WolfEncounter(String name, Coordinates encounterCoordinates, String nearbyAlert, PlayerLocation playerLocation, String shortName) {
        super(name, encounterCoordinates, nearbyAlert,shortName);
        playerLocation.registerObserver(this);
    }

    @Override
    protected void effect() {
        System.out.println("You take 20 damage");
        player.takeDamage(20);
        player.printHealth();
    }

    @Override
    protected void onEnterPrint() {
        System.out.println("You are attacked by wolves!");
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
