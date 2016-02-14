/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson.
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 */

public class CookingItem implements CookingItemInterface {

    /**
     * Time Elapsed in Game. 
    */
    static private int timeElapsped;
    /**
     * CookingItem Name.
    */
    private String name;    
    /**
     * Cooking Time.
    */
    private int cookTime;
    /**
     * time left for item to cook.
    */
    private int timeLeft;
    /**
     * Last Time Item was Checked.
    */
    private int lastCheck;
    /**
     * Penalty for Undercooked Items.
    */
    private int underPenalty;
    /**
     * Penalty for Burnt Items.
    */
    private int overPenalty;

    /**
     * Item Object Constructor.
    */
    public CookingItem(
        String itemName,
        int itemCookTime,
        int undercookedPenalty,
        int burnPenalty) {
        this.name = itemName;
        this.cookTime = itemCookTime;
        this.timeLeft = itemCookTime;
        this.underPenalty = undercookedPenalty;
        this.overPenalty = burnPenalty;
    }
    
    /**
     * Updates the food item's fields based on the time-elapsed.
     * Implements a simulation of one minute of time for this item 
     * by decrementing cooking time by one minute.
    */
    public void tick() {
        int difference = (timeElapsed - this.lastTimeChecked); // Finds difference b/w total time elapsed and item's last check.
        this.timeLeft = this.timeLeft - difference; // Appropriately decrements cooktime...
        this.lastTimeChecked = timeElapsed; // Sets lastTime checked variables for future reference...
    }
    
    /**
     * Prints general item details...
     * Mostly just for testing purposes...
    */
    public void printItem(){
        System.out.println("Item Name: " + this.name + "\n");
        System.out.println("Item Cook Time: " + this.timeLeft + "\n");
        System.out.println("Current Penalty: " + this.penalty() + "\n");
        System.out.println("Time Remaining: " + this.timeRemaining() + "\n");       
    }

    /**
     * Returns time remaining for this dish
    */
    public int timeRemaining() {
        return this.timeLeft; 
    } 
    
    /**
     * Returns the penalty if this dish were removed now.
     * Method Checks time remaining for item and imparts 
     * appropriate d.
    */
    public int penalty() {
        if (this.cookTime == 0) {
            return 0;
        } else if (this.timeRemaining > 0) {
            return this.underPenalty * this.cookTime;
        } else {
            return this.overPenalty * -this.cookTime;
        }
        
        return 0; //Just in case...
    }
}