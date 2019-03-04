package filter;

import deal.Seat;

public abstract class SeatFilter extends Filter {
	
	private Seat seat;
	
	public SeatFilter(Seat seat) {
		this.seat = seat;
	}
	
	public Seat getSeat() {
		return this.seat;
	}
}