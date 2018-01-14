import cs1.Keyboard;
public class Roulette extends Game {
    private String gamble;
    private int spin;

    public Roulette() {
	bet = minBet;
	isWin = false;
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
	placeBet(player);
	System.out.println("Spinning....");
	spin = (int)(Math.random() * 37);
	System.out.println("It rolled on a " + spin);
	if (outcome()) {
	    System.out.println("We have a winner!");
	    System.out.println("Your winnings are $" + winnings);
	}
	else {
	    System.out.println("Better luck next time!");
	}
    }

    protected boolean outcome() {
	if ((spin == 0) && (gamble == "zero")) {
	    winnings = bet * 35;
	    return true;
	}
	else if (((spin % 2 == 0) && (gamble == "even")) || ((spin % 2 == 1) && (gamble == "odd"))) {
	    winnings = bet * 2;
	    return true;
	}
	return false;
    }
}

