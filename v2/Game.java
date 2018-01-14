import cs1.Keyboard;
abstract class Game {
    protected double bet;
    protected boolean isWin;
    protected String name;
    protected double minBet = 20;
    protected double winnings;
    protected double wager;

    protected void placeBet(Character player){
	System.out.println("What would you like to bet?");
	wager = Keyboard.readInt();
	if (minBet >= player.getBalance()) {
	    bet = player.getBalance();
	    System.out.println("Your bet was less than the minimum bet.");
	    System.out.println("Your bet was set to $" + bet);
	} else if (wager <= player.getBalance()) {
	    bet = wager;
	    System.out.println("You bet $" + bet);
	} else {
	    System.out.println("Your bet was higher than your balance!");
	    placeBet(player);
	}
    }
    protected abstract void playOnce(Character player);
    protected abstract boolean outcome();
    
}
