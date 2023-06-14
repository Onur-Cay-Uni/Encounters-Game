package PlayerPackage;

import Game.OpenWorld;

//I have made the player class a singleton and made player location only accessible from player. This ensures there is only one player and one playerLocation. This is done this way because this is a single player game.
//This method also makes it more convenient for the encounter classes making sure that they are not creating more players
public class Player {
    int health;
    PlayerLocation playerLocation;

    private static Player player = null;

    private Player(int northSouthBoundary, int eastWestBoundary){
        this.health = 100;
        this.playerLocation = new PlayerLocation(northSouthBoundary,eastWestBoundary);
    }

    public void takeDamage(int damage){

        health -= damage;
        if(health<= 0){
            System.out.println("You have been eliminated");
            OpenWorld.setGameRunning(false);
        }
    }
    public void printHealth(){
        System.out.println("Your health is: "+health);
    }
     public static Player getOrCreateInstance(int northSouthBoundary, int eastWestBoundary){
        if(player == null){
            player = new Player(northSouthBoundary,eastWestBoundary);
        }
        return player;
     }
     public static Player getOrCreateInstance(){
        return player;
     }

     public PlayerLocation getPlayerLocation(){
        return playerLocation;
     }
}
