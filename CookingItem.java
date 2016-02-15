/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson.
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 *
 * CookinsItem.java Description:
 * Object Class for "Cooking Item": Constructs the items, contains the name, cooktime,
 * and penalties associated with the cooking item, defines the methods
 * for the CookingStationInterface. Note: There are 3 difference constructors for
 * Cooking Item to account for 3 different cases an objet may need to be constructed.
 * 
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * Last Modified: 2/14/2016
 */

public final class CookingItem implements CookingItemInterface {
 
 	/** 
 	 * Time Elapsed in Game. 
	 */
	private staticgit int time_elapsed;
	/** 
	 * Cooking_Item Name.
	 */
	private String name;	
	/** 
	 * Cooking Time.
	 */
	private int cook_time;
	/**
	 *  Time left for item to cook.
	 */
	private int time_left;
	/**
	 * Last Time Item was Checked.
	 */
	private int last_check;
	/** 
	 * Penalty for Undercooked Items.
	 */
	private int underPenalty;
	/** 
	 * Penalty for Burnt Items.
	 */
	private int overPenalty;

    /**
     * Constructor for a CookingItem.
     * @param  itemName           String name of the item to be cooked
     * @param  itemCookTime       Amount of time the CookingItem should cook
     * @param  undercookedPenalty Penalty per min for removing it before done
     * @param  burnPenalty        Penalty per min for removing it after done
     */
    public CookingItem(
        String itemName,
        int itemCookTime,
        int undercookedPenalty,
        int burnPenalty) {
        this.name = itemName;
        this.cook_time = itemCookTime;
        this.time_left = itemCookTime;
        this.underPenalty = undercookedPenalty;
        this.overPenalty = burnPenalty;
    }

	/**
	 *  Cooking Item Object Constructor for when passed another item.
	 *  @param item 		Already defined CookingItem Object
	 */
	CookingItem(CookingItem item) {
		this.name = item.name;
		this.cook_time = item.cook_time;
		this.time_left = item.time_left;
		this.underPenalty = item.underPenalty;
		this.overPenalty = item.overPenalty;
	}

	/**
	 *  Cooking Item Object Constructor for when passed NULL.
	 */
	CookingItem() {
		this.name = "NON_ITEM";
		this.cook_time = 0;
		this.time_left = 0;
		this.underPenalty = 0;
		this.overPenalty = 0;
	}
	
	/** Updates the food item's fields based on the time-elapsed.
	 *  Implements a simulation of one minute of time for this item 
     *  by decrementing cooking time by one minute.
	 */
	public void tick() {
		cook_time--;
	}
	
	/** Prints general item details...
	 * Mostly just for testing purposes...
	 */
	public void print_item() {
		System.out.println("Item Name: " + this.name + "\n");
		System.out.println("Item Cook Time: " + this.time_left+ "\n");
		System.out.println("Current Penalty: " + this.penalty() + "\n");
		System.out.println("Time Remaining: " + this.timeRemaining() + "\n");		
	}

	/**
	 * Prints a string of the cooking item: Contaings Name and time_left.
	 * @return str 	String of CookingItem
	 */
	public String toString(){
		String str = "(" + this.name + " " + this.time_left + ")";
        return str;
	}

   /**
    * Returns time remaining for this dish.
    * @return The amount of time left to cook.
    */
	public int timeRemaining() {
		return this.time_left; 
	} 
	
	/** 
	 * Returns the penalty if this dish were removed now.
	 * Method Checks time remaining for item and imparts 
	 * appropriate d.
	 * @return The penalty for removing the CookingItem now
 	 */
	public int penalty() {
		if (this.cook_time == 0) {
			return 0;
		} else if (this.timeRemaining() > 0) {
			return this.underPenalty * this.cook_time;
		} else {
			return this.overPenalty * -this.cook_time;
		}
	}
	
}