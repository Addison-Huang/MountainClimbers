public class Roulette extends Game {
    private String gamble;
    private int spin;
    public Roulette() {
	bet = minBet;
	isWin = false;
	name = "Roulette";
    }

    protected void playOnce() {
	System.out.println("Welcome to Roulette!");
	System.out.println("Betting on even or odd will have a payout of what you bet, while betting on 0 will payout 35 times your bet!");
	System.out.println("There are 18 odd numbers, 18 even numbers, and one 0");
	System.out.println("What would you like to place your bet on (odd, even or zero)?");
	gamble = "odd"; //user input gamble
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
	    winnings = bet * 36;
	    return true;
	}
	else if (((spin % 2 == 0) && (gamble == "even")) || ((spin % 2 == 1) && (gamble == "odd"))) {
	    winnings = bet * 2;
	    return true;
	}
	return false;
    }
}

