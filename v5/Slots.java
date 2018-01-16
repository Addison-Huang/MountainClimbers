import cs1.Keyboard;
public class Slots extends Game {
    private final String[] combinations  = { 
	"lemon", "lemon", "lemon", 
	"seven", "seven", "seven", 
	"orange", "orange", "orange", 
	"tangerine", "tangerine", "tangerine", 
	"watermelon", "watermelon", "watermelon",
	"plum", "plum", "plum",
	"star", "star", "star",
	"banana", "banana", "banana"
    };
  
    private String[] _combinations;

    public Slots() {
	name = "Slots";
	_combinations = new String[combinations.length ];
	for( int i=0; i<combinations.length; i++ ) {
	    _combinations[i] = combinations[i];
	}
    }

    public String display() {
	return _combinations[0] + " | " +
	    _combinations[1] + " | " +
	    _combinations[2];
    }

    private void swap( int i, int j ) {
	String foo = _combinations[i];
	_combinations[i] = _combinations[j];
	_combinations[j] = foo;
    }

    public void spinOnce() {
	for( int i = 0; i < _combinations.length; i++ )
	    swap( i, (int)( Math.random() * _combinations.length) );
    }


    public double whatWin() {
	if (_combinations[0].equals("seven")) {
	    winnings = 1000.0;
	    return winnings;
	}
	else {
	    winnings = 100.0;
	    return winnings;
	}		
    }

    public String toString() {
	String retStr = "Welcome to Slots! Here are the rules.\n";
	retStr += "Spinning the slots machine costs $20.\n";
	retStr += "Getting three sevens result in a $1000 jackpot or three of the same fruit results in a $100 jackpot\n";
	retStr += "Anything else, you lose.\n";
	return retStr;
    }
	    
    protected void playOnce(Character player) {
	System.out.println(this);
	System.out.println("Your current balance is $" + player.getBal());	
	if(toContinue()) {
		player.remBal(20.0);
	    System.out.println("Spinning...");
	    System.out.print("Results: ");
	    spinOnce();
	    System.out.println(display());
	    outcome(player);
        System.out.println("You have $" + player.getBal());
	}
    }

    protected void outcome(Character player) {
	if ( _combinations[0].equals(_combinations[1]) && _combinations[0].equals(_combinations[2])) {
	    System.out.println("Congratulations! you won $" + whatWin());
	    player.addBal(winnings);
	}
	else {
	    System.out.println("Better luck next time!");
	}
    }
}
	
		
			
		
	    
	    

	
		

