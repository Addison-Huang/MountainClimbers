import cs1.Keyboard;
public class Roulette extends Game {
    private String gamble;
    private int spin;

    public Roulette() {
	name = "Roulette";
    }

    public String toString() {
	String retStr = "Welcome to Roulette!\n";
	retStr += "This board consists of 37 numbers labeled from 0 to 36.\n";
	retStr += "You can either choose to bet on an even number, odd number, or zero.\n";
	retStr += "Betting on even or odd will have a payout of 2 times what you bet, while betting on 0 will payout 35 times your bet!";
	return retStr;
    }

    protected void playOnce(Character player) {
	System.out.println(this);
	System.out.println("What would you like to place your bet on (odd, even, or zero)");
	gamble = Keyboard.readString();
	player.placeBet();
	System.out.println("Spinning....");
	spin = (int)(Math.random() * 37);
	System.out.println("It rolled on " + spin);
	outcome(player);
	}

    protected void outcome(Character player) {
	if ((spin == 0) && (gamble == "zero")) {
	    winnings = player.getBet() * 35;
	    player.addBal(winnings);
	    System.out.println("We have a winner!");
	    System.out.println("Your winnings are $" + winnings);
	} else if (((spin % 2 == 0) && (gamble.equals( "even"))) || ((spin % 2 == 1) && (gamble.equals("odd")))) {
	    winnings = player.getBet() * 2;
	    player.addBal(winnings);
	    System.out.println("Better luck next time!");
	}
    }
}

