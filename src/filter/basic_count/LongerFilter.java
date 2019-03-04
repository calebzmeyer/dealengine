package filter.basic_count;

import card.Card;
import card.Suit;
import deal.Deal;
import deal.Seat;
import filter.SeatFilter;

public class LongerFilter extends SeatFilter {
	
	private Suit longer;
	private Suit shorter;
	
	public LongerFilter(Seat seat, Suit longer, Suit shorter) {
		super(seat);
		
		this.longer = longer;
		this.shorter = shorter;
	}

	@Override
	public boolean test(Deal deal) {
		
		int[] lengths = new int[4];
		for(Card card : deal.getHand(super.getSeat())) {
			lengths[card.getSuit().ordinal()]++;
		}
		
		if(lengths[longer.ordinal()] > lengths[shorter.ordinal()]) return true;
		return false;
	}
}