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

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public final class CTKMain {


    /**
    * Private Constructor for CTK Main.
    */
    private CTKMain() {

    }
     
    /**
    * Main Function for Cut Throat Kitchen.
    * @param args Main's argument (A FILE?)
    */
    public static void main(String[] args) {


        if (args.length != 1) {
            System.out.println("Invalid number of arguments given!");
            return;
        }

        CLKitchen kitchen = new CLKitchen();

        final int three = 3;
        final int four = 4;
        
        File file = new File(args[0]);

        CookingStation tempStation; //Temporary CookingStation Variable
        CookingItem tempItem; //Temporary CookItem Variable

        try {
            Scanner sc = new Scanner(file);

            do {
                String i = sc.nextLine(); //Assigne next lne in file to a String.
                String[] input = i.split(" "); //Parse the string into arguments.
                
                tempStation = new CookingStation(input[0]);

                while (!(i = sc.nextLine()).trim().isEmpty()) { //Add all items to new station.
                    input = i.split(" ");
                    //Set individual argument names to needed variable.
                    //Maybe turn into a helper method?
                    String s = input[0];
                    int a = Integer.parseInt(input[1]);
                    int b = Integer.parseInt(input[2]);
                    int c = Integer.parseInt(input[three]);

                    tempItem = new CookingItem(s, a, b, c); //Create new item.
                    tempStation.addItem(tempItem); //Add new item to station.
                }
                kitchen.insert(tempStation); //Add station to kitchen.
                
            } while (sc.hasNextLine());
            
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // kitchen.ctkSimulation();

        int pt;
        int rt;
        for (int z = 0; z < four; z++ ) {
            try {
                String fileName = "sim" + z + ".txt";
                File outfile = new File(fileName);
                file.createNewFile();  
                FileWriter writer = new FileWriter(outfile);
                
                //Kitchen Simulation Here!
                if (z >= 0 && z <= 2) {
                    kitchen.modifyThreshholds(0, z);
                } else if (z == three) {
                    kitchen.modifyThreshholds(3, 5);
                }


                
                while (!kitchen.isEmpty()) {
                    writer.write(kitchen.toString());
                    kitchen.kitchenTick(); //Runs the tick method for the stations and their items.
                }

                writer.write(kitchen.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int finalPenalty = kitchen.cumulativePenalty();
        System.out.println("Final Penalty was: " + finalPenalty);
    }




}



