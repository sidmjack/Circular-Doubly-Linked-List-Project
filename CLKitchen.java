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

class CLKitchen extends CList<CookingStation> {


    int storedPenalties;

    /**
     * Default constructor for CLKitchen.
     */
    CLKitchen() {
       super();
       this.storedPenalties = 0;
    }

    /**
     * Copy constructor for CLKitchen (which is inherited from CList)
     * @param that The CLKitchen to copy
     */
    CLKitchen(CLKitchen that) {
        super(that);
    }
    

    /**
     * Adds an item to a station.
     * @param station CookingStation to be added to the CLKitchen
     */
    public void addStation(CookingStation station) {
        this.insert(station);
    }
    
    /**
     * Iterates through all the CookingStation's in the CLKitchen and modifies
     * their thresholds.
     * @param rt Time remove threshold
     * @param pt Penalty threshold
     */
    public void modifyThreshholds(int rt, int pt) {
        for (int i = 0; i < this.length(); i++) {
            this.getValue().modifyThreshold(rt, pt);
            this.cont();
        }
    }

    /**
     * Decrements the cook time for all Cook_Items.
     */
    public void kitchenTick() {
    
        this.getValue().tick(); //Runs tick on next item to be evaluated.
        
        if (this.getValue().length() == 0) { //Remove station if empty.
            this.storedPenalties += this.remove().cumulativePenalty();
        } else {
            this.cont(); //Moves cursor to next station.
        }


        for (int i = 0; i < this.length(); i++) {
            for (int j = 0; j < this.getValue().length(); j++) {
                this.getValue().getValue().tick();
                this.getValue().cont();
            }
            this.cont();
        }
    }
    /**
     * Cumulative penalty accrued accross all stations.
     * @return Cumulative penalty
     */
    public int cumulativePenalty() {
        int c = this.storedPenalties;
        for (int i = 0; i < this.length(); i++) {
            c += this.getValue().cumulativePenalty();
            this.cont();
        }
        return c;
    }
}