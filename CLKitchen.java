/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson
 * Logins:
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 *
 * CLKitchen.java Description:
 * Runs the Cut Throat Kitchen Simulation...
 *
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * Last Modified: 2/14/2016
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class CLKitchen extends CList<CookingStation> {

    /**
     * List containing all Kitchen Stations.
     */
    // private CList<CookingStation> kitchen;

     /**
      * Private Constructor for CTK Main
      */
     public CLKitchen() {
        
     }
     

     /**
      * Adds an item to a station.
      * @param station CookingStation to be added to the CLKitchen
      */
    public void addStation(CookingStation station) {
        this.insert(station);
    }
    
    /**
    * Runs the Cut Throat Kitchen Simulation.
    * @param kitchen THe list of stations in the kitchen
    */
    public void CTK_Simulation() {
        while (!this.isEmpty()) {
            this.kitchenTick(); //Runs the tick method for the stations and their items.
        }
    }

    /**
     * Decrements the cook time for all Cook_Items
     */
    public void kitchenTick() {
    
        this.getValue().tick(); //Runs tick operation on next item to be evaluated.
        
        if (this.getValue().length() == 0) { //Remove station if empty.
                this.remove();
        }

        this.cont(); //Moves cursor to next station.

        for (int i = 0; i < this.length(); i++) {
            for (int j = 0; j < this.getValue().length(); j++) {
                this.getValue().getValue().tick();
                this.getValue().cont();
            }
            this.cont();
        }
    }

    public int cumulativePenalty() {
        int c = 0;
        for (int i = 0; i < this.length(); i++) {
            c += this.getValue().cumulativePenalty();
            this.cont();
        }
        return c;
    }

    /**
     * Prints all of the stations along with the items they contain.
     */
    public void print() {
        System.out.println(this); //depends on toString() function defined in CList.java      
    }
}