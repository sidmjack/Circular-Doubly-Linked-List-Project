/**
 * Names:
 * Logins: 
 * Course: 600.226.02
 * Project: 1 (Cutthroat Kitchen)
 */

public interface CookingItemInterface {

	void tick(); // Implements a simulation of one minute of time for this item 
                 // by decrementing cooking time by one minute
	
	int timeRemaining(); // Returns time remaining for this dish
	
	int penalty(); // Returns the penalty if this dish were removed now

}