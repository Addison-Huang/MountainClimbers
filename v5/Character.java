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
    
    public Character(){
	balance = 1000;
	shop = new ArrayList<String>(Arrays.asList("ring", "table", "box", "car")); 
	inventory = new ArrayList<String>();
	shopPrice = new ArrayList<Double>(Arrays.asList(100.0, 300.0, 400.0, 23.0));
    }

    public double getBet() {
    	return bet;
	}
	
    public void remBal(double lost) {
	balance -= lost;
    }
	
    public void addBal(double winnings) {
	balance += winnings;
    }
	
    public double getBal() {
	return balance;
    }
    
    public void choosePlace(){
	System.out.println("Where would you like to go? (casino or shop)");
	String location = Keyboard.readString();
	if (location.equals("casino")){
	    chooseGame();
	} else if(location.equals("shop")){
		shop();
	}
    }

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
		} else {
		shop();
		}
	}
	}
	
	public boolean isWin() {
	if (shop.size() == 0) {
		return true;
	} else {
		return false;
	}
	}

    public void chooseGame() {
	System.out.println("Which game would you like to play? (slots, roulette, blackjack)");
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
	}
	
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

	public void quitGame() {
	System.out.println("Leaving game...");
	System.out.println("Thanks for playing!");		
    }


    public boolean toBuy(int x) {
	if (balance > shopPrice.get(x)) {
	    String y = shop.remove(x);
	    balance -= shopPrice.remove(x);
	    inventory.add(y);
	    System.out.println(y + " added to your inventory.");
	    return true;
	}
	else {
	    System.out.println("Insufficient balance");
	    return false;
	}
    }

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
