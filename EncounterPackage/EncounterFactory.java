package EncounterPackage;

import PlayerPackage.PlayerLocation;
import CoordinatesPackage.Coordinates;
import java.util.ArrayList;

public class EncounterFactory {
    //I have chosen to make this public even though it can be private. This way it allows the developer to use it to create a single encounter
    public static Encounter createEncounterOnCoordinate(EncounterEnum encounterEnum, Coordinates coordinates, PlayerLocation playerLocation){
        switch (encounterEnum) {
            case FLATLANDS -> {
                return new FlatlandsEncounter(encounterEnum.getName(), coordinates, encounterEnum.getNearbyAlert(), playerLocation, encounterEnum.getShortName());
            }
            case LAKE -> {
                return new LakeEncounter(encounterEnum.getName(), coordinates, encounterEnum.getNearbyAlert(), playerLocation, encounterEnum.getShortName());
            }
            case WOLF -> {
                return new WolfEncounter(encounterEnum.getName(), coordinates, encounterEnum.getNearbyAlert(), playerLocation, encounterEnum.getShortName());
            }
            case FINISHPOINT -> {
                return new FinishPointEncounter(encounterEnum.getName(), coordinates, encounterEnum.getNearbyAlert(), playerLocation, encounterEnum.getShortName());
            }
            case WORLDSHIFT -> {
                return new WorldShiftEncounter(encounterEnum.getName(), coordinates, encounterEnum.getNearbyAlert(), playerLocation, encounterEnum.getShortName());
            }
            default -> throw new IllegalArgumentException("Invalid type of encounter");
        }
    }

    //No encounter is created where the player is and a finish point is always created at top right to ensure the game can be over.
    public static ArrayList<Encounter> populateEncounters(int NSBoundary, int EWBoundary, PlayerLocation playerLocation) {
        ArrayList<Encounter> encounters = new ArrayList<>();
        Coordinates playerCoordinates = playerLocation.getPlayerCoordinates();
        for (int NS = 0; NS <= NSBoundary; NS++) {
            for (int EW = 0; EW <= EWBoundary; EW++) {
                if (playerCoordinates.getEastWest() == EW && playerCoordinates.getNorthSouth() == NS){
                    encounters.add(createEncounterOnCoordinate(EncounterEnum.FLATLANDS, new Coordinates(playerCoordinates.getNorthSouth(),playerCoordinates.getEastWest()),playerLocation));
                }
                if ((playerCoordinates.getEastWest() != EW || playerCoordinates.getNorthSouth() != NS)&&!(NS==NSBoundary && EW==EWBoundary)) {
                    encounters.add(createEncounterOnCoordinate(EncounterEnum.randomEncounter(), new Coordinates(NS, EW), playerLocation));
                }
            }
        }
        encounters.add(createEncounterOnCoordinate(EncounterEnum.FINISHPOINT, new Coordinates(NSBoundary,EWBoundary ),playerLocation));
        return encounters;
    }
}
