package filter.distribution;

import card.Card;
import deal.Deal;
import deal.Hand;
import deal.Seat;
import filter.SeatFilter;

public class ShortageFilter extends SeatFilter {

	public ShortageFilter(Seat seat) {
		super(seat);
	}

	@Override
	public boolean test(Deal deal) {
		
		Hand hand = deal.getHand(this.getSeat());
		
		int[] lengths = new int[4];
		for(Card card : hand) {
			lengths[card.getSuit().ordinal()]++;
		}
		
		for(int length : lengths) {
			if(length <= 1) return true;
		}
		
		return false;
	}
	
	
}