package filter.basic_count;

import card.Card;
import deal.Deal;
import deal.Hand;
import deal.Seat;
import filter.SeatFilter;

public class HCPFilter extends SeatFilter {
	
	
	private int minHCP;
	private int maxHCP;
	
	public HCPFilter(Seat seat, int minHCP, int maxHCP) {
		super(seat);
		
		this.minHCP = minHCP;
		this.maxHCP = maxHCP;
	}

	@Override
	public boolean test(Deal deal) {
		
		Hand hand = deal.getHand(this.getSeat());
		
		int HCP = 0;
		for(Card card : hand) {
			int value = card.getValue().ordinal() + 2;
			if(value > 10) HCP += (value - 10);
		}
		
		if(HCP < minHCP || HCP > maxHCP) return false;		
		return true;
	}
}