package com.danielclark.blackjack;

// Dealer ArrayList. .add to give him a card. .get to get his cards

public class GameApp {
	private Player p = new Player();

	public static void main(String[] args) {
		GameApp game = new GameApp();
		
		game.run();
		
	}
	
	public void run() {
		Deck deck = initializeDeck();
		for (Card c : deck.getCards()) {
			System.out.println(c.getR() + " of " + c.getS() + " " + c.getR().getValue());
		}
		p.getHand().addCard(deck.dealCard());
		p.getHand().addCard(deck.dealCard());
		System.out.println(deck.getCards().size());
		
		int playerHandValue = 0;
		for (Card c : p.getHand().getCards()) { // printing out the player's hand. Have to not only get Hand object but his actual cards
			System.out.println(c);
			playerHandValue = playerHandValue + c.getR().getValue();
		}
		System.out.println("Player's Hand: " + playerHandValue);
		
	}
	
	public Deck initializeDeck() {  // create deck first so we have somewhere to store Cards next
		Deck deck = new Deck();
		
		for(Suit s : Suit.values()) {     // iterates through Suit enums
			for(Rank r : Rank.values()) { // gives values to cards
				Card c = new Card(r,s);  // calls Card constructor to create Card obj
				deck.addCard(c); 		// add newly created cards to deck
			}
		}
		deck.shuffleCards();
		return deck;
	}
	

}

// ask player to hit or stay
// if hit, then get player hand, add a card, deal card
























//public int checkForAces(Player player) {
//
//
//int playerValue = 0;
//
//for (Card c : player.getHand().getCards()) {
//	playerValue = playerValue + c.getR().getValue();
//	
//}
//
//System.out.println(playerValue);
//for (Card c : player.getHand().getCards()) {
//	if (playerValue > 21 && c.getR().equals(Rank.ACE)) {
//		playerValue = playerValue - 10;
//	}
//}
//
//return playerValue;
//}
