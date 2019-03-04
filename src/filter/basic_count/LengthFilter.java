package filter.basic_count;

import card.Card;
import card.Suit;
import deal.Deal;
import deal.Hand;
import deal.Seat;
import filter.SeatFilter;

public class LengthFilter extends SeatFilter {
	
	private Suit suit;
	private int minLength;
	private int maxLength;
	
	public LengthFilter(Seat seat, Suit suit, int minLength, int maxLength) {
		super(seat);
		
		this.suit = suit;
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	@Override
	public boolean test(Deal deal) {
		
		Hand hand = deal.getHand(this.getSeat());
		
		int count = 0;
		for(Card card : hand) {
			if(card.getSuit() == this.suit) count++;
		}
		
		if(count < this.minLength || count > this.maxLength) return false;
		return true;
	}
}