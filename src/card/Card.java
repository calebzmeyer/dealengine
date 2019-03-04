package card;

public class Card implements Comparable<Card> {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value) {
		
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit() {
		return this.suit;
	}
	
	public Value getValue() {
		return this.value;
	}
	
	public String toString() {
		
		return "" + this.value + this.suit;
	}
	
	@Override
	public int compareTo(Card other) {
		
		int suitDiff = other.suit.compareTo(this.suit);
		int valueDiff = other.value.compareTo(this.value);
		
		if(suitDiff != 0) return suitDiff;
		return valueDiff;
	}	
}