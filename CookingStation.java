/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 *
 * CookinsStation.java Description:
 * Object Class for "Cooking Station": Constructs the stations, contains the name and 
 * circular list of cooking items belonging to that station, and defines the methods
 * for the CookingStationInterface. Extends CList Class. 
 * 
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * Last Modified: 2/14/2016
 */

import java.lang.Math;

public final class CookingStation extends CList<CookingItem> implements CookingStationInterface {

    /** cOOKINGSTATION Name.
    */
    private String stationName;    
    
    /** List containing all Station Items.
     */
    // private CList<CookingItem> station;

    /** Variable that keeps track of the minutes passed.
     */
    private int tickValue = 0;

    /** Variable that keeps track of the total penalty accrued.
     */
    private int cumulativePenalty = 0;

    /** Value that sets the ideal "window" to remove CookingItem.
     *  If the item observed is within an appropriate "window" of it's ideal cook time, remove item.
     */
    private static final int rEMOVETHRESHOLD  = 3;

    /** Value that sets the penalty threshold.
     *  If the penalty for an item has accrued a penalty beyond the threshold, remove item.
     */
    private static final int pENALTYTHRESHOLD = 5;

    /**
     * Cooking Station Object Constructor.
     * @param stationName The String name of the CookingStation.
     */
    CookingStation(String stationName) {
        super(); //runs CList constructor
        this.stationName = stationName;

        // this = null;
    }
    /**
     *  Put a new dish at the end of the station.
     *  @param it the dish to add
     */
    public void addItem(CookingItem item) {
      this.insert(item);
    }


    public String toString() {
        String s = new String();
        s += this.stationName + " " + super.toString();
        return s;
    }

    public void print() {
        // grill [ (steak 10) (salmon 5) (chicken 30) ] stove [ (pasta 10) (sauce 5) (ratatoulle 30) ]
        System.out.print(this);
    }
    /**
     * Returns the name of this CookingStation.
     * @return Returns the name of this CookingStation
     */
    public String getStationName() {
        return this.stationName;
    }

    /** 
      * Tends the current item using a simple algorithm that "decides" whether
      * or to remove the item from the station or let it remain. 
      * @return the value of the current element, null if none
     */
    public void tick() {
        
        CookingItem temp = this.tend(rEMOVETHRESHOLD, pENALTYTHRESHOLD);
        if (temp != null) {
          cumulativePenalty += temp.penalty(); //Adds penalty (if any is accrued).
        } else {
            this.cont(); //Moves cursor to next foodItem in the CookingItem List.
        }
        
        tickValue++; //Increments tick value (minutes passed) by one.

        
    }

    /**
    * Decides whether a CookingItem should removed from a station or not.
    * @param  rEMOVETHRESHOLD  Determines what range within the idea cooktime
    *                          item shuold be removed.
    * @param  pENALTYTHRESHOLD Depending on whether cooktime is over/under,
    *                          decides whether to remove the item based on the
    *                          penalty it would accrue...
    * @return                  Returns value of item if removed; Returns "null" otherwise.
    */
    public CookingItem tend(int rEMOVETHRESHOLD, int pENALTYTHRESHOLD) {
        
        int timeRemaining = this.getValue().timeRemaining(); //Time Remaining until Item is Cooked.
        int penalty = this.getValue().penalty(); //Penalty that would be gained if item was removed.

        if (timeRemaining < rEMOVETHRESHOLD) {
            return this.remove(); //Use this to get penalty later...
        } 

        if (timeRemaining > 0) {
            if (penalty > pENALTYTHRESHOLD) { //If Item is way undercooked, dont touch it!
                return null;
            } else { //If item is undercooked, but almost done, remove item.
                return this.remove();
            }
        } else { //If item is done, just take it take it off!
                return this.remove();
        }

    }

    /**
     * Returns the cumulative penalty accrued.
     * @return cumulativePenalty  
     */
    public int cumulativePenalty() {
        return cumulativePenalty;
    }

}