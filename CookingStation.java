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

	/** Cooking_Station Name.
	*/
	private String station_name;	
	
	/** List containing all Station Items.
	 */
	private CList<CookingItem> station;

	/** Variable that keeps track of the minutes passed.
	 */
	private static int tick_value = 0;

	/** Variable that keeps track of the total penalty accrued.
	 */
	private static int cumulative_penalty = 0;

	/** Value that sets the ideal "window" to remove CookingItem.
	 *  If the item observed is within an appropriate "window" of it's ideal cook time, remove item.
	 */
	private static final int REMOVE_THRESHOLD  = 3;

	/** Value that sets the penalty threshold.
	 *  If the penalty for an item has accrued a penalty beyond the threshold, remove item.
	 */
	private static final int PENALTY_THRESHOLD = 5;

    /**
     * Cooking Station Object Constructor.
     */
	CookingStation(String station_name) {
		this.station_name = station_name;
		this.station = null;
	}

	/** 
	  * Tends the current item using a simple algorithm that "decides" whether or
	  * to remove the item from the station or let it remain. 
	  * @return the value of the current element, null if none
	 */
	public void tick() {

		CookingItem temp; //Temporary Cooking Item Object
		
		this.station.cont(); //Moves cursor to next food_item in the CookingItem List.
		temp = new CookingItem(this.tend(REMOVE_THRESHOLD, PENALTY_THRESHOLD));
		
		cumulative_penalty+=temp.penalty(); //Adds penalty (if any is accrued).
		tick_value++; //Increments tick value (minutes passed) by one.
	}

	/**
	 * Adds an item to a station.
	 */
	public void addItem(CookingItem item) {
		this.station.insert(item);
	}

  /**
   * Decides whether a CookingItem should removed from a station or not.
   * @param  REMOVE_THRESHOLD  Determines what range within the idea cooktime item shuold be removed.
   * @param  PENALTY_THRESHOLD Depending on whether cooktime is over/under, decides whether to 
   *                           remove the item based on the penalty it would accrue...
   * @return                   Returns value of item if removed; Returns "null" otherwise.
   */
	public CookingItem tend(int REMOVE_THRESHOLD, int PENALTY_THRESHOLD) {
		
		int timeRemaining = this.station.getValue().timeRemaining(); //Time Remaining until Item is Cooked.
		int penalty = this.station.getValue().penalty(); //Penalty that would be gained if item was removed.

		if (Math.abs(timeRemaining) < REMOVE_THRESHOLD) {
			return this.station.remove(); //Use this to get penalty later...
		} 

		if (timeRemaining < 0) {
			if (penalty > PENALTY_THRESHOLD) { //If Item is way undercooked, dont touch it!
				return null;
			} else { //If item is undercooked, but almost done, remove item.
				return this.station.remove();
			}
		} else { //If item is done, just take it take it off!
				return this.station.remove();
		}

	}

	/**
	 * Returns the cumulative penalty accrued.
	 * @return cumulative_penalty  
	 */
	public static int cumulativePenalty() {
		return cumulative_penalty;
	}

}