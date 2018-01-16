import cs1.Keyboard;

public class Dice extends Game{
  private int die1;
  private int die2;
  private int success;
  public Dice(){
    name = "Dice";
  }
  public String toString(){
    String retstr = "Welcome to Dice!\n";
    retstr += "The first round of dice will start when the two dice are rolled\n";
    retstr += "The dice are added up and the sum will have to match the following win conditions\n";
    retstr += "A 2, 3, or 10 leads to an immediate loss in the first round\n";
    retstr += "Getting a 7 leads to an immediate tie, no change in balance here\n";
    retstr += "Geting a 11 leads to an immediate win, money is gained\n";
    retstr += "Getting any number other than the aforementioned leads to additional rolling\n";
    retstr += "========================================================================\n";
    retstr += "Additional Rolling Rules:\n";
    retstr += "There will be up to three more rounds of rolling\n";
    retstr += "The dice must equal the initial sum of the first roll to win\n";
    retstr += "The money won will vary\n";
    retstr += "Rolling a 7 results in automatic loss\n";
    retstr += "After 3 rounds, if the aforementioned has not occured, the player has lost.";
    return retstr;
  }
  private int calculateTotal(int a, int b){
    return a + b;
  }
  private void roll(){
    die1 = ((int)(Math.random() * 6)) + 1;
    die2 = ((int)(Math.random() * 6)) + 1;
  }
  protected void playOnce(Character player){//returns payout/bet ratio
    player.placeBet();
    System.out.println("Rolling Dice");
    roll();
    int total = calculateTotal(die1, die2);
    if(total == 7){
      System.out.println("The dice add up to 7, it is a tie");
      success = 1;
      outcome(player);
      System.out.println("Your balance: " + player.getBal());
      return ; //7 makes a tie
    }
    if(total == 11){
      System.out.println("The dice add up to 11, you're a winner!");
      success = 2;
      outcome(player);
      System.out.println("Your balance: " + player.getBal());
      return ;  //11 is a winner
    }
    if(total == 2 || total == 3 || total == 12){
      System.out.println("The dice add up to " + total + ", sorry better luck next time.");
      success = 0;
      outcome(player);
      System.out.println("Your balance: " + player.getBal());
      return ; //these numbers are losses
    }
    else{
      System.out.println("The dice added up to " + total);
      System.out.println("We will now roll the dice an additional three times to see if it lands on the same initial number");
      for(int i = 1; i < 4; i++){
        roll();
        int total2 = calculateTotal(die1, die2);
        System.out.println("First roll: " + total);
        System.out.println("Round " + (i + 1) + "'s roll: " + total2);
        if(total2 == 7){
          success = 0;
          outcome(player);
          System.out.println("Sorry, better luck next time.");
          System.out.println("Your balance: " + player.getBal());
          return ;
        }
        if(total2 == total && (total == 4 || total == 10)){
          if(i == 1){
          success = 5;
          outcome(player);
            System.out.println("Success, lucky much?");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
          if(i == 2){
          success = 4;
          outcome(player);
            System.out.println("Huzzah, second time's the charm");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
          if(i == 3){
          success = 3;
          outcome(player);
            System.out.println("Wowee, slow and steady wins the race");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
        }
        if(total2 == total &&(total == 5 || total == 9)){
          if(i == 1){
          success = 4;
          outcome(player);
            System.out.println("Success, lucky much?");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
          if(i == 2){
          success = 3;
          outcome(player);
            System.out.println("Huzzah, second time's the charm");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
          if(i == 3){
          success = 2;
          outcome(player);
            System.out.println("Wowee, slow and steady wins the race");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
        }
        if(total2 == total && (total == 6 || total == 8)){
          if(i == 1){
          success = 3;
          outcome(player);
            System.out.println("Success, lucky much?");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
          if(i == 2){
          success = 2;
          outcome(player);
            System.out.println("Huzzah, second time's the charm");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
          if(i == 3){
          success = 1;
          outcome(player);
            System.out.println("Wowee, slow and steady wins the race");
            System.out.println("You have won " + success + " times your bet!");
            System.out.println("Your balance: " + player.getBal());
            return ;
          }
        }
      }
      System.out.println("Sorry, better luck next time.");
      System.out.println("Your balance: " + player.getBal());
      return ;
    }
  }
  protected void outcome(Character player){
    winnings = success * player.getBet();
    player.addBal(winnings);
  }

}
