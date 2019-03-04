package deal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import card.Card;
import card.Suit;

public class Hand implements Iterable<Card> {
	
	private List<Card> hand = new ArrayList<Card>();
	
	public Hand(List<Card> hand) {
		this.hand = hand;
		hand.sort(null);
	}
	
	@Override
	public Iterator<Card> iterator() {	
		return this.hand.iterator();
	}
	
	public String toString() {
		
		String returnString = "";
		
		Suit currentSuit = Suit.Spades;
		for(Card card : this.hand) {
			
			while(!currentSuit.equals(card.getSuit())) {
				returnString += ".";
				currentSuit = Suit.values()[currentSuit.ordinal() - 1];
			}
			
			returnString += card.getValue();
		}
		
		while(!currentSuit.equals(Suit.Clubs)) {
			returnString += ".";
			currentSuit = Suit.values()[currentSuit.ordinal() - 1];
		}
		
		return returnString;
	}
}