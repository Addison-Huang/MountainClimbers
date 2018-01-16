import cs1.Keyboard;
abstract class Game {
    protected String name;
    protected double winnings;

	protected boolean toContinue() {
	System.out.println("Continue? (yes or no)");
	String cont = Keyboard.readString();
	if (cont.equals("yes")) {
		return true;
	} else {
		return false;
	}
	}
    protected abstract void playOnce(Character player);
    protected abstract void outcome(Character player);
    
}
