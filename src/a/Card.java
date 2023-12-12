package a;

public class Card {
	   private final String face; // face of card ("Ace", "Deuce", ...)
	   private final String suit; // suit of card ("Hearts", "Diamonds", ...)
	   private final int value; // value of card (1-11);

	   // two-argument constructor initializes card's face and suit
	   public Card(String face, String suit, int value) {
	      this.face = face;
	      this.suit = suit;
	      this.value = value;
	   }

	   public int getValue() {
	      return value;
	   }

	   // return String representation of Card
	   public String toString() {
	      return face + " of " + suit + " and its value: [" + value + "] ";
	   }
	} // end class Card that has the basic attributes of the cards