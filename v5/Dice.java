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
    retstr += "";
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
      return ; //7 makes a tie
    }
    if(total == 11){
      System.out.println("The dice add up to 11, you're a winner!");
      success = 2;
      return ;  //11 is a winner
    }
    if(total == 2 || total == 3 || total == 12){
      System.out.println("The dice add up to " + total + ", sorry better luck next time.");
      success = 0;
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
          System.out.println("Sorry, better luck next time.");
          return ;
        }
        if(total2 == total && (total == 4 || total == 10)){
          if(i == 1){
          success = 5;
            System.out.println("Success, lucky much?");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
          if(i == 2){
          success = 4;
            System.out.println("Huzzah, second time's the charm");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
          if(i == 3){
          success = 3;
            System.out.println("Wowee, slow and steady wins the race");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
        }
        if(total2 == total &&(total == 5 || total == 9)){
          if(i == 1){
          success = 4;
            System.out.println("Success, lucky much?");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
          if(i == 2){
          success = 3;
            System.out.println("Huzzah, second time's the charm");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
          if(i == 3){
          success = 2;
            System.out.println("Wowee, slow and steady wins the race");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
        }
        if(total2 == total && (total == 6 || total == 8)){
          if(i == 1){
          success = 3;
            System.out.println("Success, lucky much?");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
          if(i == 2){
          success = 2;
            System.out.println("Huzzah, second time's the charm");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
          if(i == 3){
          success = 1;
            System.out.println("Wowee, slow and steady wins the race");
            System.out.println("You have won " + success + " times your bet!");
            return ;
          }
        }
      }
      System.out.println("Sorry, better luck next time.");
      return ;
    }
  }
  protected void outcome(Character player){
    winnings = success * player.getBet();
    player.addBal(winnings);
  }

}
