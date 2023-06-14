package Game;

import CoordinatesPackage.Coordinates;
import EncounterPackage.Encounter;
import EncounterPackage.EncounterFactory;
import PlayerPackage.Player;
import PlayerPackage.PlayerLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OpenWorld {
	
	private int northSouthBoundary, eastWestBoundary;


	private Player player;
	private static ArrayList<Encounter> encounters = new ArrayList<Encounter>();
	private static boolean gameRunning = false;

	private PlayerLocation playerLocation;
	private Scanner scanner = new Scanner(System.in);
	private String[][] matrix;
	public OpenWorld() {
	
	}

	private void initialise(int northSouthBoundary, int eastWestBoundary) {
		this.northSouthBoundary = northSouthBoundary;
		this.eastWestBoundary = eastWestBoundary;
		this.matrix = new String[northSouthBoundary+1][eastWestBoundary+1];
		this.player = Player.getOrCreateInstance(northSouthBoundary,eastWestBoundary);
		this.playerLocation = player.getPlayerLocation();
		playerLocation.changeCoordinates(new Coordinates(0,0));
		encounters = EncounterFactory.populateEncounters(northSouthBoundary,eastWestBoundary,playerLocation);
		player.getPlayerLocation().notiftyObservers();
		for (Encounter encounter : encounters) {
			encounter.printEncounterDetails();
		}
		System.out.println("---------------------------");
	}
	
	private void playerMove(int northSouthChange, int eastWestChange) {
		int goToNorthSouthCoord = playerLocation.getPlayerCoordinates().getNorthSouth()+northSouthChange;
		int goToEastWestCoord = playerLocation.getPlayerCoordinates().getEastWest()+eastWestChange;
		playerLocation.changeCoordinates(new Coordinates(goToNorthSouthCoord,goToEastWestCoord));

		System.out.println("---------------------------");

	}

	private void gameLoop(){
		gameRunning = true;
		int[] worldSize = worldSize();
		initialise(worldSize[0],worldSize[1]);
		createMap();
		while (gameRunning){
			System.out.println("Where would you like to go?");
			System.out.println("North:1, South:2, East:3, West:4");
			int nextMove = scanner.nextInt();
			scanner.nextLine();
			int[] nextMoveDetermined = determineMove(nextMove);
			if (!Arrays.equals(nextMoveDetermined, new int[]{0, 0})){
				playerMove(nextMoveDetermined[0], nextMoveDetermined[1]);
			}
			createMap();
			gameRunning = getGameRunning();
		}
		scanner.close();
	}

	private int[] worldSize(){

		System.out.println("What would you like the north-south axis size to be?");
		int northEast = scanner.nextInt();
		scanner.nextLine();
		System.out.println("What would you like the east-west axis size to be?");
		int eastWest  = scanner.nextInt();
		scanner.nextLine();

		return new int[]{northEast,eastWest};
	}

	public static void setGameRunning(boolean setGameRunning){
		gameRunning = setGameRunning;
	}

	public boolean getGameRunning(){
		return gameRunning;
	}

	private int[] determineMove(int nextMove){

		switch (nextMove) {
			case 1 -> {
				return new int[]{1, 0};
			}
			case 2 -> {
				return new int[]{-1, 0};
			}
			case 3 -> {
				return new int[]{0, 1};
			}
			case 4 -> {
				return new int[]{0, -1};
			}
			default -> {
				System.out.println("Input not recognised");
				return new int[]{0, 0};
			}
		}
	}

	public static void setEncounters(ArrayList<Encounter> newEncounters){
		encounters = newEncounters;
	}

	public void printMap(){
		for (String[] strings : matrix) {
			for (String string : strings) {

				System.out.print(string + " ");
			}
			System.out.println();
		}
	}

	public void createMap(){
		int j = 0;
		int i = 0;
		for(int row = northSouthBoundary; row >= 0; row--){
			for(int col = 0; col<matrix[row].length; col++){
				String encounterShortName = encounters.get(j).getShortName();
				matrix[row][col] = "[  "+encounterShortName+"  ]";
				j++;
				if(i == playerLocation.getPlayerCoordinates().getNorthSouth() && col == playerLocation.getPlayerCoordinates().getEastWest()){
					matrix[row][col] = "[  PL  ]";
				}
			}
			i++;
		}
		printMap();
	}


	public static void main(String[]args)
	{
		OpenWorld world = new OpenWorld();
		world.gameLoop();

		
	}

}
