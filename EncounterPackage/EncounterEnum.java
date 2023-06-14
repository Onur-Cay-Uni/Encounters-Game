package EncounterPackage;

import java.util.Random;
public enum EncounterEnum {
    FLATLANDS("Flatlands","see boring open planes","FL"),
    FINISHPOINT("Finish Point","see a golden beam of light","FP"),
    WOLF("Wolf","hear a distant howling.","WF"),
    LAKE("Lake","see that there is a vast expanse of water","LK"),
    WORLDSHIFT("World Shift","see a blue-ish portal like thing","WS");

    private final String nearbyAlert;
    private final String name;
    private final String shortName;

    private static final EncounterEnum[] encounters = values();
    private static final Random rand = new Random();
    EncounterEnum(String name, String nearbyAlert, String shortName){
        this.name = name;
        this.nearbyAlert = nearbyAlert;
        this.shortName = shortName;
    }

    public String getName(){
        return name;
    }

    public String getNearbyAlert(){
        return nearbyAlert;
    }

    public String getShortName(){return shortName;}


    public static EncounterEnum randomEncounter() {
        EncounterEnum e = encounters[rand.nextInt(encounters.length)];
        if(e == FINISHPOINT){
            e = encounters[rand.nextInt(encounters.length)];
        }
        //A way to balance out the probability of there being lots of finish points. It doesnt ensure a consistent rate which can make the game less predictable and more enjoyable
        return e;
    }
}
