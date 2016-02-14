/**
 * Names:
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 */

public class Item implements CookingItemInterface{

	/* Cooking_Item Name
	*/
	private String name;	
	/* Cooking Time
	*/
	private int cook_time;
	/* Time Elapsed in Game; 
	*/
	static private int time_elapsped;
	/* Last Time Item was Checked
	*/
	private int last_check;
	/* Penalty for Undercooked Items
	*/
	private int underPenalty;
	/* Penalty for Burnt Items
	*/
	private int overPenalty;

    /* Item Object Constructor
    */
	Item(item_name, item_cook_time, undercooked_penalty, burn_penalty) {
		this.name = item_name;
		this.cook_time = item_cook_time;
		this.underPenalty = undercooked_penalty;
		this.overPenalty = burn_penalty;
	}
	
	/* Updates the food item's fields based on the time-elapsed.
	*/
	void update_item() {
		int difference = (time_elapsed - this.last_time_checked); // Finds difference b/w total time elapsed and item's last check.
    	this.cook_time = this.cook_time - difference; // Appropriately decrements cooktime...
		this.last_time_checked = time_elapsed; // Sets last_time checked variables for future reference...
	}
	
	/* Prints general item details...
	   Mostly just for testing purposes...
	*/
	void print_item(){
		System.out.println("Item Name: " + this.name + "\n");
		System.out.println("Item Cook Time: " + this.item_cook_time + "\n");
		System.out.println("Current Penalty: " + this.penalty() + "\n");
		System.out.println("Time Remaining: " + this.timeRemaining() + "\n");		
	}
	
	/* Implements a simulation of one minute of time for this item 
       by decrementing cooking time by one minute
	*/
    void tick() { //Consider making static...
    	time_elapsed++; //Increments time elapsed...
    }

	/* Returns time remaining for this dish
	*/
	int timeRemaining() {
		return this.cook_time; 
	} 
	
	/* Returns the penalty if this dish were removed now.
	   Method Checks time remaining for item and imparts 
	   appropriate d
	*/
	int penalty() {
		if (this.cooktime == 0) {
			return 0;
		} else if (this.timeRemaining > 0) {
			return this.underPenalty * this.cooktime;
		} else {
			return this.overPenalty * -this.cooktime;
		}
		
		return 0; //Just in case...
	}
}