/*Mountain Climbers
  Kenny Li, Addison Huang, Simon Tsui
  APCS1 pd1
*/

import cs1.Keyboard;

public class Blackjack extends Game{
    private String[] deck = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private String[] playerHand;
    private String[] dealerHand;
    private String choice;
  
    //default constructor
    public Blackjack(){
	name = "Blackjack";
    }
	
    //override toString
    public String toString() {
	String retstr = "Welcome to Blackjack. Here are the rules.\n";
	retstr += "The goal of this game is to have a hand that totals more than the dealer's hand without exceeding 21.\n";
	retstr += "1. The value of the cards corresponds to their number.\n";
	retstr += "2. The value of the royals(jack, queen, king) are all 10.\n";
	retstr += "3. The ace can either be valued as a 1 or an 11.\n";
	retstr += "-------------------------------------\n";
	retstr += "You will start your hand off with 2 cards.\n";
	retstr += "You can either choose to hit (add another card) or stay (keep what you have).\n";
	retstr += "After you stay, the dealer will continue to hit until he has a hand that is greater than 16.\n";
	retstr += "-------------------------------------\n";	
	retstr += "Here are the ways to lose\n";
	retstr += "1. You bust by exceeding 21.\n";
	retstr += "2. The dealer has a greater total than you.\n";
	retstr += "3. The dealer has a blackjack and you don't.\n";
	retstr += "-------------------------------------\n";	
	retstr += "Here are the ways to win\n";
	retstr += "1. The dealer busts by exceeding 21.\n";
	retstr += "2. You have a greater total than the dealer.\n";
	retstr += "3. You have a blackjack and the dealer doesn't.\n";
	retstr += "-------------------------------------\n";	
	retstr += "You and the dealer can also tie if you have the same total or both of you have a blackjack.\n";
	retstr += "-------------------------------------\n";
	retstr += "If you're starting hand is a pair, then you can choose to split your hand into two hands.\n";
	retstr += "You only need to win with one of your hands to win the round.\n";
	return retstr;
    }

    //-----methods dealing with hand-----
	
    //sets the starting hand of two cards
    private void setHand(String[] hand){
	for(int i = 0; i < 2; i++) {
	    int random = (int)(Math.random() * 13);
	    hand[i] = deck[random];
	}
    }
	
    //adds a new card to hand
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
	
    //helper method for calculating total dealing with aces   
    private boolean hasAce(String[] hand) {
	for (int i = 0; i < hand.length; i++) {
	    if (hand[i].equals(deck[0])) {
		return true;
	    }
	}
	return false;
    }	

    //calculates total of hand
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
    //-----------------------------------
	
	
    //-----methods dealing with split-----
	
    //plays two hands after player chooses to split
    private void playSplit(Character player) {
	String[] temp = new String[1];
	temp[0] = playerHand[0];
		
	playerHand = new String[1];
	playerHand = temp;
	addHand(playerHand);
	playerTurn(player);
	if(isBust(playerHand)) {
	    playSecondHand(player, temp);
	} else {
	    dealerTurn();
	    if (splitWin()) {
		outcome(player);
	    } else {
		playSecondHand(player, temp);
	    }
	}
    }

    //helper method to test if first hand in split wins
    private boolean splitWin() {
	int deal = calculateTotal(dealerHand);
	int play = calculateTotal(playerHand);
	if ((deal < play) || (isBust(dealerHand))) {
	    return true;
	} else {
	    return false;
	}
    }	
	
    //helper method for creating the second hand
    private void secondHand(String[] card) {
	System.out.println("Sorry, better luck next time.\n");
	System.out.println("This is your second hand.");
	playerHand = new String[1];
	playerHand = card;
	addHand(playerHand);
    }
	
    private void playSecondHand(Character player, String[] card) {
	secondHand(card);
	playerTurn(player);
	if(isBust(playerHand)) {
	    System.out.println("Sorry, better luck next time.");
	} else {
	    dealerTurn();
	    outcome(player);
	}
    }		
    //-----------------------------------
	
    //-----methods dealing with win conditions-----
	
    //helper method for blackjackWin, tests if hand is blackjack
    private boolean isBlackjack(String[] hand) {
	int total = calculateTotal(hand);
	if ((total == 21) && (hand.length == 2)) {
	    return true;
	}
	return false;
    }
	
    //win conditions when someone has a blackjack
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
	
    //tests is hand is a bust (greater than 21)
    private boolean isBust(String[] hand) {
	if((calculateTotal(hand)) <= 21) {
	    return false;
	} else {
	    return true;
	}
    }

    //compares the total of player and dealer and determines outcome
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
    //-----------------------------------

    //the player's turn
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
	
    //the dealer's turn
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

    //play one round of Blackjack
    protected void playOnce(Character player){
	System.out.println(this);
	if (toContinue()) {
	    dealerHand = new String[2];
	    playerHand = new String[2];
	    setHand(playerHand);
	    setHand(dealerHand);
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
	    System.out.println("You have $" + player.getBal());	
	}
    }

}//end class Blackjack



	
