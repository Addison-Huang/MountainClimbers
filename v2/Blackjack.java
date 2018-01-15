import cs1.Keyboard;

public class Blackjack extends Game{
  private String[] deck = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
  private String[] playersHand = new String[2];
  private String choice;
  private int total;
  public Blackjack(){
    name = "Blackjack";
    setPlayersHand();

  }
  private void setPlayersHand(){
    int i = 0;
    while(i < 2){
      int random = (int)(Math.random() * 13);
      playersHand[i] = deck[random];
      i++;
    }
    i = 0;
  }
  private void addPlayersHand(){
    String[] temp = new String[playersHand.length+1];
    for(int i = 0; i < playersHand.length; i++){
      temp[i] = playersHand[i];
    }
    int random = (int)(Math.random() * 13);
    temp[playersHand.length] = deck[random];
    playersHand = temp;

  }
  private void calculateTotal(){
    int acectr = 0;
    total = 0;
    for(int i = 0; i < playersHand.length; i++){
      if(playersHand[i].equals("Ace")){
        acectr++;
      }
      if(playersHand[i].equals("Two")){
        total += 2;
      }
      if(playersHand[i].equals("Three")){
        total += 3;
      }
      if(playersHand[i].equals("Four")){
        total += 4;
      }
      if(playersHand[i].equals("Five")){
        total += 5;
      }
      if(playersHand[i].equals("Six")){
        total += 6;
      }
      if(playersHand[i].equals("Seven")){
        total += 7;
      }
      if(playersHand[i].equals("Eight")){
        total += 8;
      }
      if(playersHand[i].equals("Nine")){
        total += 9;
      }
      if(playersHand[i].equals("Ten")){
        total += 10;
      }
      if(playersHand[i].equals("Jack")){
        total += 10;
      }
      if(playersHand[i].equals("Queen")){
        total += 10;
      }
      if(playersHand[i].equals("King")){
        total += 10;
      }
    }
    for(;acectr > 0;acectr--){
      if(total + 11 <= 21){
        total += 11;
        acectr--;
      }
      else{
        total += 1;
        acectr--;
      }
    }

  }
  public String toString() {
    String retstr = "Welcome to BlackJack\n";
    retstr += "Blackjack is a card game, the goal is to have your cards add up to 21. No Higher.\n";
    retstr += "Begin!";
    return retstr;
  }
  protected void playOnce(Character player){
    boolean playing = true;
    String statement = "Your cards are: " + " "+ playersHand[0] + " " + playersHand[1];
    System.out.println(this);
    while(playing){
      System.out.println(statement);
      System.out.println(playersHand.length);
      calculateTotal();
      System.out.println("Your cards currently add up to: " + Integer.toString(total));
      if(outcome(player)){
        if(total == 21 && playersHand.length == 2){
          System.out.println("Blackjack!");
          break;
        }
        if(total == 21){
          System.out.println("Winner!");
          break;
        }
        System.out.println("Hit or Stay?");
        choice = Keyboard.readString();
        if (choice.equals("Hit")){
          addPlayersHand();
          statement += " " + playersHand[playersHand.length-1];
        }
        else{
          playing = false;
        }
      }
      else{
        System.out.println("Sorry, Better luck next time");
        break;
      }
    }
  }
  protected boolean outcome(Character player){
    if(total <= 21){
      return true;
    }
    else{
      return false;
    }
  }
}
