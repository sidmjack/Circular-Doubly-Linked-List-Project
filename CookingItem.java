/**
 * Names: Lawrence Wolf-Sonkin & Sidney Jackson.
 * Logins: lwolfso1 & sjacks85
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 *
 * CookinsItem.java Description:
 * Object Class for "Cooking Item": Constructs the items, contains the name,
 * cooktime,    and penalties associated with the cooking item, defines the
 * methods for the CookingStationInterface. Note: There are 3 difference
 * constructors for CookingItem to account for 3 different cases an objet may
 * need to be constructed.
 *
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * Last Modified: 2/17/2016
 */

public final class CookingItem implements CookingItemInterface {
 
    /**
     * Time Elapsed in Game.
     */
    private static int timeElapsed;
    /**
     * CookingItem Name.
     */
    private String name;
    /**
     * Cooking Time.
     */
    private int cookTime;
    /**
     *  Time left for item to cook.
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
        this.cookTime = itemCookTime;
        this.timeLeft = itemCookTime;
        this.underPenalty = undercookedPenalty;
        this.overPenalty = burnPenalty;
    }

    /**
     *  Cooking Item Object Constructor for when passed another item.
     *  @param item         Already defined CookingItem Object
     */
    CookingItem(CookingItem item) {
        this.name = item.name;
        this.cookTime = item.cookTime;
        this.timeLeft = item.timeLeft;
        this.underPenalty = item.underPenalty;
        this.overPenalty = item.overPenalty;
    }

    // /**
    //  *  Cooking Item Object Constructor for when passed NULL.
    //  */
    // CookingItem() {
    //     this.name = "NON_ITEM";
    //     this.cookTime = 0;
    //     this.timeLeft = 0;
    //     this.underPenalty = 0;
    //     this.overPenalty = 0;
    // }
    
    /**
     * Returns the name of this CookingItem.
     * @return Returns the name of this CookingItem
     */
    public String getItemName() {
        return this.name;
    }
    
    /** Updates the food item's fields based on the time-elapsed.
     *  Implements a simulation of one minute of time for this item
     *  by decrementing cooking time by one minute.
     */
    public void tick() {
        this.timeLeft--;
    }
    
    /** Prints general item details...
     * Mostly just for testing purposes...
     */
    public void printItem() {
        System.out.println("Item Name: " + this.name + "\n");
        System.out.println("Item Cook Time: " + this.timeLeft + "\n");
        System.out.println("Current Penalty: " + this.penalty() + "\n");
        System.out.println("Time Remaining: " + this.timeRemaining() + "\n");
    }

    /**
     * Prints a String representation of a CookingItem.
     */
    public void print() {
        System.out.print(this);
    }

    /**
     * Prints a string of the cooking item: Contaings Name and timeLeft.
     * @return str  String of CookingItem
     */
    public String toString() {
        return "(" + this.name + " " + this.timeLeft + ")";
    }

   /**
    * Returns time remaining for this dish.
    * @return The amount of time left to cook.
    */
    public int timeRemaining() {
        return this.timeLeft;
    }
    
    /**
     * Returns the penalty if this dish were removed now.
     * Method Checks time remaining for item and imparts
     * appropriate d.
     * @return The penalty for removing the CookingItem now
     */
    public int penalty() {
        if (this.timeRemaining() == 0) {
            return 0;
        } else if (this.timeRemaining() > 0) {
            return this.underPenalty * this.timeLeft;
        } else {
            return this.overPenalty * -this.timeLeft;
        }
    }
    
}