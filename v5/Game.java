import cs1.Keyboard;
abstract class Game {
    //instances variables
    protected String name;
    protected double winnings;

    //method in which it asks the player to continue
    protected boolean toContinue() {
	System.out.println("Continue? (yes or no)");
	String cont = Keyboard.readString();
	if (cont.equals("yes")) {
	    return true;
	} else {
	    return false;
	}
    }
    //simulates the game, runs it
    protected abstract void playOnce(Character player);
    //checks whether it was a win or loss
    protected abstract void outcome(Character player);
    
}
