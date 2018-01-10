public class Character {
    private String username;
    private String password;
    private double balance;
    private int winCounter;
    private int lossCounter;
    private String[] shop;
    private String[] inventory;
    
    public Character(){
	username = "guest";
	password = "password";
	balance = 1000;
	shop =  new String[10];  // ["ring", "", "", "", "", "", "", "", "", ""];
    }
    public Character(String newName, String newPassword){
	this();
	username = newName;
	password = newPassword;
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
}
