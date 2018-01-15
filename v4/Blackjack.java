import cs1.Keyboard;

public class Blackjack extends Game{
    private String[] deck = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private String[] playerHand = new String[2];
    private String[] dealerHand = new String[2];
    private String choice;
  
    public Blackjack(){
	name = "Blackjack";
	setHand(playerHand);
	setHand(dealerHand);
    }
  
    private void setHand(String[] hand){
	for(int i = 0; i < 2; i++) {
	    int random = (int)(Math.random() * 13);
	    hand[i] = deck[random];
	}
    }

    private void addHand(String[] hand){
	String[] temp = new String[hand.length + 1];
	for(int i = 0; i < hand.length; i++){
	    temp[i] = hand[i];
	}
	int random = (int)(Math.random() * 13);
	temp[hand.length] = deck[random];
	if (hand.equals(playerHand)) {
	    playerHand = temp;
	} else if (hand.equals(dealerHand)) {
	    dealerHand = temp;
	}
    }
	

    private int calculateTotal(String[] hand){
	int acectr = 0;
	int total = 0;
	for(int i = 0; i < hand.length; i++){
	    for(int x = 0; x < deck.length; x++) {
		if (deck[x] == hand[i]) {
		    if (x == 0) {
			acectr++;
		    } else if (x < 10) {
			total += x + 1;
		    } else {
			total += 10;
		    }
		}
	    }
	}
	for(; acectr > 0; acectr--){
	    if(total + 11 <= 21){
		total += 11;
	    }
	    else {
		total += 1;
	    }
	}
	return total;
    }
  
    public String toString() {
	String retstr = "Welcome to Blackjack!\n";
	retstr += "Blackjack is a card game, the goal is to have your cards add up to 21. No Higher.\n";
	retstr += "Begin!";
	return retstr;
    }

    private boolean hasAce(String[] hand) {
	for (int i = 0; i < hand.length; i++) {
	    if (hand[i].equals(deck[0])) {
		return true;
	    }
	}
	return false;
    }

    private void dealerTurn() {
	System.out.println("It is the dealer's turn now.");
	String statement = "The dealer's cards are: " + " " + dealerHand[0] + " " + dealerHand[1];
	System.out.println(statement);
	while (calculateTotal(dealerHand) < 17) {
	    System.out.println("The dealer hits");
	    addHand(dealerHand);
	    statement += " " + dealerHand[dealerHand.length - 1];
	    System.out.println(statement);
	}
	System.out.println("The dealer's total is: " + calculateTotal(dealerHand));
    }

	private void secondHand(String[] card) {
		System.out.println("Sorry, better luck next time.\n");
		System.out.println("This is your second hand.");
		playerHand = new String[1];
		playerHand = card;
		addHand(playerHand);
	}

		
	private void playSplit(Character player) {
		String[] temp = new String[1];
		temp[0] = playerHand[0];
		
		playerHand = new String[1];
		playerHand = temp;
		addHand(playerHand);
		playerTurn(player);
		if(isBust(playerHand)) {
			secondHand(temp);
			playerTurn(player);
			dealerTurn();
			outcome(player);
		} else {
			dealerTurn();
			if (splitWin()) {
				outcome(player);
			} else {
				secondHand(temp);
				playerTurn(player);
				dealerTurn();
				outcome(player);
			}
		}
	}
		
    protected void playOnce(Character player){
	System.out.println(this);
	player.placeBet();
	if (playerHand[0].equals(playerHand[1])) {
	    System.out.println("Your cards are: " + " "+ playerHand[0] + " " + playerHand[1]);
	    System.out.println("Split? (yes or no)");
	    if (Keyboard.readString().equals("yes")) {
			playSplit(player);
		} else {
			playerTurn(player);
			if (isBust(playerHand)) {
				System.out.println("Sorry, better luck next time.");
			} else {
				dealerTurn();
				outcome(player);
			}
		}
	} else {
		if (!blackjackWin(player)) {
			playerTurn(player);
			if (isBust(playerHand)) {
				System.out.println("Sorry, better luck next time");
			} else {
				dealerTurn();
				outcome(player);
			}
		}
	}
	}

	private boolean isBlackjack(String[] hand) {
		int total = calculateTotal(hand);
		if ((total == 21) && (hand.length == 2)) {
			return true;
		}
		return false;
	}
	
    private boolean blackjackWin(Character player) {
    if ((isBlackjack(playerHand)) && (isBlackjack(dealerHand))){
        System.out.println("Both you and the dealer got a blackjack, so it's a draw.");
        winnings = player.getBet();
        player.addBal(winnings);
		return true;
    } else if(isBlackjack(playerHand)) {
        System.out.println("Blackjack!");
        winnings = player.getBet() * 2.5;
        player.addBal(winnings);
        System.out.println("You won $" + player.getBet());
		return true;
    } else if(isBlackjack(dealerHand)) {
        System.out.println("The dealer got a Blackjack!");
        System.out.println("Sorry, better luck next time.");
		return true;
    }
	return false;
    }
	
    private void playerTurn(Character player) {
	boolean playing = true;
	String statement = "Your cards are: " + " "+ playerHand[0] + " " + playerHand[1];
	while(playing){
	    System.out.println(statement);
	    int total = calculateTotal(playerHand);
	    System.out.println("Your cards currently add up to: " + total);
		if(!isBust(playerHand)) {
			System.out.println("hit or stay?");
			choice = Keyboard.readString();
			if (choice.equals("hit")) {
				addHand(playerHand);
				statement += " " + playerHand[playerHand.length - 1];  
			} else {
				playing = false;
			}
		} else {
			playing = false;
		}
	}
    }

	private boolean isBust(String[] hand) {
		if((calculateTotal(hand)) <= 21) {
			return false;
		} else {
			return true;
		}
	}
	private boolean splitWin() {
		int deal = calculateTotal(dealerHand);
		int play = calculateTotal(playerHand);
		if ((deal < play) || (isBust(dealerHand))) {
			return true;
		} else {
			return false;
		}
	}
	
    protected void outcome(Character player) {
		int deal = calculateTotal(dealerHand);
		int play = calculateTotal(playerHand);
		if ((deal < play) || (isBust(dealerHand))) {
			winnings = player.getBet() * 2;
			player.addBal(winnings);
			System.out.println("Winner! You won $" + winnings);
		} else if (deal == play) {
			winnings = player.getBet();
			player.addBal(winnings);
			System.out.println("It's a draw.");
		} else {
			System.out.println("Sorry, better luck next time.");
		}
	}
}