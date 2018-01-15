public class Woo{
    public static void main(String[] args){
	Character player = new Character();
	/*Game newGame = new blackjack()
	player.toBuy();
	System.out.println(player.shop);
	System.out.println(player.inventory);
	System.out.println(player.shopPrice);*/
	/*Game x = new Roulette();
	x.playOnce(player);
	Game y = new Slots();
	y.playOnce(player);*/
	System.out.println(player.getBal());
	Game z = new Blackjack();
	z.playOnce(player);
	System.out.println(player.getBal());
    }
}
