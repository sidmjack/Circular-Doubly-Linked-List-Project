/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson
 * Logins:
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 *
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * CTKMain.java Description:
 * Runs the Cut Throat Kitchen Simulation...
 *
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * Last Modified: 2/14/2016
 */
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

                final int four = 4;

        int pt;
        int rt;
        for (int z = 0; z < four; z++) {
            try {
                final int three = 3;
                final int five = 5;


                CLKitchen kitchen = new CLKitchen();

                readInFileToKitchen(kitchen, args[0]);

                String fileName = "sim" + z + ".txt";
                File outfile = new File(fileName);
                outfile.createNewFile();  
                FileWriter writer = new FileWriter(outfile);
                
                //Kitchen Simulation Here!
                if (z >= 0 && z <= 2) {
                    kitchen.modifyThreshholds(0, z);
                } else if (z == three) {
                    kitchen.modifyThreshholds(three, five);
                }


                
                while (!kitchen.isEmpty()) {
                    writer.write(kitchen + ", " + kitchen.cumulativePenalty()
                        + "\n");
                    kitchen.kitchenTick(); //Runs tick on stations and items.
                }

                writer.write(kitchen + ", " + kitchen.cumulativePenalty()
                    + "\n");
                int finalPenalty = kitchen.cumulativePenalty();
                writer.write("Final Penalty was: " + finalPenalty);

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Reads in a File into the given CLKitchen variable.
     * @param kitchen  CLKitchen object to take in the file.
     * @param fileName input file
     */
    private static void readInFileToKitchen(CLKitchen kitchen,
        String fileName) {

        final int three = 3;
        
        File file = new File(fileName);

        CookingStation tempStation; //Temporary CookingStation Variable
        CookingItem tempItem; //Temporary CookItem Variable

        try {
            Scanner sc = new Scanner(file);

            do {
                String i = sc.nextLine(); //Send next line in file to String.
                String[] input = i.split(" "); //Parse String into arguments
                
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
                    tempStation.append(tempItem); //Add new item to station.
                }
                kitchen.append(tempStation); //Add station to kitchen.
                
            } while (sc.hasNextLine());
            
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}



