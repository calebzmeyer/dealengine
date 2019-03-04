package filter.distribution;

import card.Card;
import deal.Deal;
import deal.Seat;
import filter.SeatFilter;

public class BalancedFilter extends SeatFilter {

	private boolean wantBalanced;
	
	public BalancedFilter(Seat seat, boolean wantBalanced) {
		super(seat);
		this.wantBalanced = wantBalanced;
	}

	@Override
	public boolean test(Deal deal) {
		
		if(new ShortageFilter(this.getSeat()).test(deal)) return !wantBalanced;
		
		int[] lengths = new int[4];
		for(Card card : deal.getHand(this.getSeat())) {
			lengths[card.getSuit().ordinal()]++;
		}
		
		int sum = 0;
		for(int length : lengths) {
			if(length > 3) sum += length;
		}
		
		if(sum > 8) return !wantBalanced;
		return wantBalanced;
	}
}
