import java.util.ArrayList;
import java.util.Arrays;

public class Character {
    //instance vars
    private String username;
    private String password;
    private double balance;
    private int winCounter;
    private int lossCounter;
    public ArrayList<String> shop;
    public ArrayList<String> inventory;
    public ArrayList<Double> shopPrice;
    
    public Character(){
	username = "guest";
	password = "password";
	balance = 1000;
	shop = new ArrayList<String>(Arrays.asList("ring", "table", "box", "car")); 
	inventory = new ArrayList<String>();
	shopPrice = new ArrayList<Double>(Arrays.asList(100.0, 300.0, 400.0, 23.0));
    }
    public Character(String newName, String newPassword){
	this();
	username = newName;
	password = newPassword;
    }

    public double getBalance() {
	return balance;
	}

    public void choosePlace(){
	System.out.println("Choose a place to go: ");
	String location = "casino";  //takes in input from person
	if(location.equals("Casino")){

	    chooseGame();
	}
	else if(location.equals("Bar")){
	    chooseDrink();
	}
	    
    }
    public void chooseGame(){
	System.out.println("Choose a game:");
	String s = "slots"; //prompts user
	if(s.equals("slots")){

	}
	else if(s.equals("roulette")){

	}
	
	
    }
    public void chooseDrink(){

    }

    public void toExit() {
	System.out.println("Leaving game...");
	System.out.println("Thanks for playing!");
    }

    public boolean toBuy() {
	int x = 2;
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
	
	
}
