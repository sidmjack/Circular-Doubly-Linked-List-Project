/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 *
 * CTKMain.java Description:
 * Runs the Cut Throat Kitchen Simulation...
 * 
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * Last Modified: 2/14/2016
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public final class CTKMain {

	/** 
	 * List containing all Kitchen Stations.
	 */
	private static CList<CookingStation> kitchen;

	 /**
	  * Private Constructor for CTK Main
	  */
	 private CTKMain() {
	 }
	 
	 /**
	  * Main Function for Cut Throat Kitchen
	  * @param args Main's argument (A FILE?)
	  */
	 public static void main(String[] args) {

		if (args.length != 1) {
            System.out.println("Invalid number of arguments given!");
        } else {
        	
        	File file = new File(args[0]);

        	CookingStation tempStation; //Temporary CookingStation Variable
        	CookingItem tempItem; //Temporary CookItem Variable

        	try {
        		Scanner sc = new Scanner(file);

        		do {
         			String i = sc.nextLine(); //Assigne next lne in file to a String.
		 			String[] input = i.split(" "); //Parse the string into arguments.
		 			
		 			tempStation = new CookingStation(input[0]);
		 			kitchen.insert(tempStation); //Add station to kitchen.

		 			while (!(sc.nextLine()).trim().isEmpty()) { //Add all items to new station.

		 				//Set individual argument names to needed variable.
		 				//Maybe turn into a helper method?
		 				String s = input[0];
		 				int a = Integer.parseInt(input[1]);  
    	 				int b = Integer.parseInt(input[2]);
    	 				int c = Integer.parseInt(input[3]);

    	 				tempItem = new CookingItem(s, a, b, c); //Create new item.
    	 				tempStation.addItem(tempItem); //Add new item to station.
    	 			}
    	 			
    	 		} while (sc.hasNextLine());
    	 		
    	 		sc.close();

    	    } catch (FileNotFoundException e) {
        		e.printStackTrace();
    		}
        } 

        CTK_Simulation(kitchen);
        
        int final_penalty = CookingStation.cumulativePenalty();
        System.out.println("Final Penalty was: " + final_penalty);
	 }

    /**
	 * Adds an item to a station.
	 */
	public void addStation(CookingStation station) {
		kitchen.insert(station);
	}
	
	/**
 	* Runs the Cut Throat Kitchen Simulation.
 	* @param kitchen THe list of stations in the kitchen
 	*/
	public static void CTK_Simulation(CList<CookingStation> kitchen) {
		while (kitchen.length() != 0) {
			kitchenTick(); //Runs the tick method for the stations and their items.
		}
	}

	/**
	 * Decrements the cook time for all Cook_Items 
	 */
	public static void kitchenTick() {
	
		kitchen.getValue().tick(); //Runs tick operation on next item to be evaluated.
		
		if (kitchen.getValue().station.length() == 0) { //Remove station if empty.
				kitchen.remove();
		}

		kitchen.cont(); //Moves cursor to next station.

		for (int i = 0; i < kitchen.length(); i++) {
			for (int j = 0; j < kitchen.getValue().station.length(); j++) {
				kitchen.getValue().station.getValue().tick();
				kitchen.getValue().station.cont();
			}
			kitchen.cont();
		}
	}

	/**
	 * Prints all of the stations along with the items they contain.
	 * Needs work. As is will reset cursor everytime print is used.
	 */
	public static void printKitchen() {
		
		kitchen.moveToStart();
		System.out.print("[ ");
		for (int i = 0; i < kitchen.length(); i++) {
			System.out.print(kitchen.getValue().station_name);
			System.out.print("[ ");
			for (int j = 0; j < kitchen.getValue().station.length(); j++) {
				
				String item = kitchen.getValue().station.getValue().name;
				int time_left = kitchen.getValue().station.getValue().cook_time;
				
				System.out.println("(" + item + " " + time_left + ")");

				kitchen.getValue().station.cont();
			}
			System.out.print(" ]");
			kitchen.cont();
		}
		System.out.print(" ]");
	}
}