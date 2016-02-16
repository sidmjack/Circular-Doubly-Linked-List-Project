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
            return;
        }

        CLKitchen kitchen = new CLKitchen();
        
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

        kitchen.CTK_Simulation();

        int final_penalty = kitchen.cumulativePenalty();
        System.out.println("Final Penalty was: " + final_penalty);
    }


}
