/**
 * Names:
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 */

public class CookingItem implements CookingItemInterface{

	/* Cooking_Item Name.
	*/
	private String name;	
	/* Cooking Time.
	*/
	private int cook_time;
	/* time left for item to cook.
	*/
	private int time_left;
	/* Time Elapsed in Game. 
	*/
	static private int time_elapsped;
	/* Last Time Item was Checked.
	*/
	private int last_check;
	/* Penalty for Undercooked Items.
	*/
	private int underPenalty;
	/* Penalty for Burnt Items.
	*/
	private int overPenalty;

    /* Item Object Constructor.
    */
	CookingItem(String item_name, int item_cook_time, int undercooked_penalty, int burn_penalty) {
		this.name = item_name;
		this.cook_time = item_cook_time;
		this.time_left = item_cook_time;
		this.underPenalty = undercooked_penalty;
		this.overPenalty = burn_penalty;
	}
	
	/* Updates the food item's fields based on the time-elapsed.
	 * Implements a simulation of one minute of time for this item 
     * by decrementing cooking time by one minute.
	*/
	void tick() {
		int difference = (time_elapsed - this.last_time_checked); // Finds difference b/w total time elapsed and item's last check.
    	this.time_left = this.time_left - difference; // Appropriately decrements cooktime...
		this.last_time_checked = time_elapsed; // Sets last_time checked variables for future reference...
	}
	
	/* Prints general item details...
	   Mostly just for testing purposes...
	*/
	void print_item(){
		System.out.println("Item Name: " + this.name + "\n");
		System.out.println("Item Cook Time: " + this.time_left+ "\n");
		System.out.println("Current Penalty: " + this.penalty() + "\n");
		System.out.println("Time Remaining: " + this.timeRemaining() + "\n");		
	}

	/* Returns time remaining for this dish
	*/
	int timeRemaining() {
		return this.time_left; 
	} 
	
	/* Returns the penalty if this dish were removed now.
	   Method Checks time remaining for item and imparts 
	   appropriate d.
	*/
	int penalty() {
		if (this.cook_time == 0) {
			return 0;
		} else if (this.timeRemaining > 0) {
			return this.underPenalty * this.cooktime;
		} else {
			return this.overPenalty * -this.cooktime;
		}
		
		return 0; //Just in case...
	}
}