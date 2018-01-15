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

    private String[] split(String[] hand) {
	String[] splitHand = new String[1];
	splitHand[0] = hand[0];
	hand[1] = null;
	addHand(splitHand);
	addHand(playerHand);
	return splitHand;
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
	String retstr = "Welcome to BlackJack\n";
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

    protected void playOnce(Character player){
	//boolean playing = true;
	//String statement = "Your cards are: " + " "+ playerHand[0] + " " + playerHand[1];
	System.out.println(this);
	player.placeBet();
	if (playerHand[0].equals(playerHand[1])) {
	    System.out.println("Your cards are: " + " "+ playerHand[0] + " " + playerHand[1]);
	    System.out.println("Split? (yes or no)");
	    if (Keyboard.readString().equals("yes")) {
		String[] temp = new String[1];
		temp[0] = playerHand[0];
		playerHand = new String[1];
		playerHand = temp;
		addHand(playerHand);
		turn(player);
		playerHand = new String[1];
		playerHand = temp;
		addHand(playerHand);
		dealerHand = new String[2];
		setHand(dealerHand);
	    }
	}
	turn(player);
    }

    private void turn(Character player) {
	boolean playing = true;
	String statement = "Your cards are: " + " "+ playerHand[0] + " " + playerHand[1];
	while(playing){
	    System.out.println(statement);
	    int total = calculateTotal(playerHand);
	    System.out.println("Your cards currently add up to: " + total);
	    if(outcome(player)) {
		if(total == 21 && playerHand.length == 2){
		    System.out.println("Blackjack!");
		    winnings = player.getBet() * 2.5;
		    player.addBal(winnings);
		    System.out.println("You won $" + winnings);
		    break;
		}
		System.out.println("hit or stay?");
		choice = Keyboard.readString();
		if (choice.equals("hit")){
		    addHand(playerHand);
		    statement += " " + playerHand[playerHand.length - 1];  
		}
		else {
		    playing = false;
		    dealerTurn();
		    int deal = calculateTotal(dealerHand);
		    int play = calculateTotal(playerHand);
		    if ((deal < play) || (deal > 21)) {
			winnings = player.getBet() * 2;
			player.addBal(winnings);
			System.out.println("Winner! You won $" + winnings);
		    }
		    else if (deal == play) {
			winnings = player.getBet();
			player.addBal(winnings);
			System.out.println("It was a draw");
		    }
		    else {
			System.out.println("Sorry, Better luck next time");
		    }
		}			
	    }
	    else {
		System.out.println("Sorry, Better luck next time");
		playing = false;
	    }
	}
    }
	
    
	    
    


    protected boolean outcome(Character player){
	if(calculateTotal(playerHand) <= 21){
	    return true;
	}
	else {
	    return false;
	}
    }
}
