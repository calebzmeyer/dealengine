package filter.basic_count;

import card.Card;
import card.Suit;
import deal.Deal;
import deal.Seat;
import filter.SeatFilter;

public class ShorterFilter extends SeatFilter {
	
	private Suit shorter;
	private Suit longer;
	
	public ShorterFilter(Seat seat, Suit shorter, Suit longer) {
		super(seat);
		
		this.shorter = shorter;
		this.longer = longer;
	}

	@Override
	public boolean test(Deal deal) {
		
		int[] lengths = new int[4];
		for(Card card : deal.getHand(super.getSeat())) {
			lengths[card.getSuit().ordinal()]++;
		}
		
		if(lengths[shorter.ordinal()] < lengths[longer.ordinal()]) return true;
		return false;
	}
}