package deal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import card.Card;
import card.Suit;
import card.Value;

public class Deal {
	
	private Hand[] hands = new Hand[4];
	
	public Deal() {
		
		List<Card> shuffledDeck = new ArrayList<Card>();
		List<Card> sortedDeck = new ArrayList<Card>();
		
		for(Suit suit : Suit.values()) {
			for(Value value: Value.values()) {
				sortedDeck.add(new Card(suit, value));
			}
		}
		
		Random rand = new Random();
		
		while(!sortedDeck.isEmpty()) {
			int index = rand.nextInt(sortedDeck.size());
			shuffledDeck.add(sortedDeck.remove(index));
		}
		
		hands[Seat.North.ordinal()] = new Hand(shuffledDeck.subList(0, 13));
		hands[Seat.East.ordinal()] = new Hand(shuffledDeck.subList(13, 26));
		hands[Seat.South.ordinal()] = new Hand(shuffledDeck.subList(26, 39));
		hands[Seat.West.ordinal()] = new Hand(shuffledDeck.subList(39, 52));
	}
	
	public Hand getHand(Seat seat) {
		return this.hands[seat.ordinal()];
	}
	
	public String toString() {
		
		return "[Deal \"" + hands[0] + " " + hands[1] + " " + hands[2] + " " + hands[3] + "\"]";
	}
}