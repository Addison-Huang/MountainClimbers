import java.util.ArrayList;
import java.util.Arrays;
import cs1.Keyboard;

public class Character {
    //instance vars
    private double balance;
    private double bet;
    private double minBet = 20.0;
    public ArrayList<String> shop;
    public ArrayList<String> inventory;
    public ArrayList<Double> shopPrice;

    //default constructor
    public Character(){
	balance = 1000;
	shop = new ArrayList<String>(Arrays.asList("ring", "table", "robot dog", "car")); 
	inventory = new ArrayList<String>();
	shopPrice = new ArrayList<Double>(Arrays.asList(1500.0, 500.0, 5000.0  , 20000.0));
    }

    //returns bet
    public double getBet() {
    	return bet;
    }

    //removes lost from balance
    public void remBal(double lost) {
	balance -= lost;
    }

    //adds winnings to balance
    public void addBal(double winnings) {
	balance += winnings;
    }

    //gets balance
    public double getBal() {
	return balance;
    }

    //chooses the destination
    public void choosePlace(){
	System.out.println("Where would you like to go? (casino, shop or quit)");
	String location = Keyboard.readString();
	if (location.equals("casino")){
	    chooseGame();
	} else if(location.equals("shop")){
	    shop();
	} else if(location.equals("quit")){
	    quitGame();
	}
    }

    //the shop where you buy prizes
    public void shop() {
	System.out.println("Welcome to the shop.");
	System.out.println("Your current balance is $" + getBal());		
	System.out.println("What would you like to buy? (choose a number)");
	for(int i = 0; i < shop.size(); i++) {
	    System.out.println(i + 1 + "." + shop.get(i) + " $" + shopPrice.get(i));
	}
	int exit = shop.size() + 1;
	System.out.println(exit + ".exit shop");
	int x = Keyboard.readInt();
	if (x == exit) {
	    choosePlace();
	} else {
	    toBuy(x - 1);
	    if (isWin()) {
		System.out.println("Congratulations, you win!");
		System.out.println("You have beat the casino!");
	    } else {
		shop();
	    }
	}
    }
    
    //checks if the shop is empty, in which case you win
    public boolean isWin() {
	if (shop.size() == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    //player chooses game
    public void chooseGame() {
	System.out.println("Which game would you like to play? (slots, roulette, blackjack, dice)");
	String game = Keyboard.readString();
	if(game.equals("slots")){
	    Game g = new Slots();
	    g.playOnce(this);
	    afterGame(g);	
	}else if(game.equals("roulette")){
	    Game g = new Roulette();
	    g.playOnce(this);
	    afterGame(g);		
	}else if(game.equals("blackjack")){
	    Game g = new Blackjack();
	    g.playOnce(this);
	    afterGame(g);		
	}
	else if (game.equals("dice")) {
	    Game g = new Dice();
	    g.playOnce(this);
	    afterGame(g);
	}
	else {
	    System.out.println("invalid choice!");
	    System.out.println("Pick something else!");
	    chooseGame();
	}
    }
    
    //after the game the player chooses what to do
    public void afterGame(Game g) {
	System.out.println("\n");
	System.out.println("What would you like to do? (choose a number)");
	String str = ("1. Play again.\n");
	str += ("2. Play different game.\n");
	str += ("3. Go somewhere else.\n");
	str += ("4. Quit.\n");
	System.out.println(str);
	int x = Keyboard.readInt();
	if (x == 1) {
	    g.playOnce(this);
	    afterGame(g);
	} else if (x == 2) {
	    chooseGame();
	} else if (x == 3) {
	    choosePlace();
	} else if (x == 4) {
	    quitGame();
	}
    }

    //the player quits 
    public void quitGame() {
	System.out.println("Leaving game...");
	System.out.println("Thanks for playing!");		
    }

    //the player buys something from the shop, helper method
    public void toBuy(int x) {
	if (balance >= shopPrice.get(x)) {
	    String y = shop.remove(x);
	    balance -= shopPrice.remove(x);
	    inventory.add(y);
	    System.out.println(y + " added to your inventory.");
	} else {
	    System.out.println("Insufficient balance");
	}
    }

    //places the bet before certain games
    public void placeBet(){
	System.out.println("Your current balance is $" + getBal());
	System.out.println("What would you like to bet?");
	double wager = Keyboard.readDouble();
	if (minBet >= balance) {
	    bet = balance;
	    balance = 0;
	    System.out.println("Your bet was less than the minimum bet.");
	    System.out.println("Your bet was set to $" + bet);
	} else if (minBet > wager) {
	    bet = minBet;
	    balance -= bet;
	    System.out.println("Your bet was less than the minimum bet.");
	    System.out.println("Your bet was set to $" + bet);  
	} else if (wager <= balance) {
	    bet = wager;
	    balance -= bet;
	    System.out.println("You bet $" + bet);
	} else {
	    System.out.println("Your bet was higher than your balance!");
	    placeBet();
	}
    }
	
	
}
