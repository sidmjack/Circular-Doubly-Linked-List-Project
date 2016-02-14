/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 *
 * CookinsItemInterface.java Description:
 * The interface that simply defines the methods to be included in 
 * the CookingItemClass.
 * 
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * Last Modified: 2/14/2016
 */

public interface CookingItemInterface {
	/**
	 * Implements a simulation of one minute of time for this item by decrementing cooking time by one minute
	 */
	void tick(); // 
	
	/**
	 * Returns time remaining for the CookingItem.
	 * @return returns int time remaining.
	 */
	int timeRemaining(); 
	
	/**
	 * Returns the penalty accreud (if any) by removing the dish
	 * @return returns int penalty
	 */
	int penalty(); 

}