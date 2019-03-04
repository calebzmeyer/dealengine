package deal.metainfo;

import deal.Seat;

public class Dealer {
	
	private Seat seat;
	
	public Dealer(int boardNo) {
		
		this.seat = Seat.values()[(boardNo - 1) % 4];
	}
	
	public Seat getSeat() {
		return this.seat;
	}
	
	public String toString() {
		
		return "[Dealer \"" + this.seat + "\"]";
	}
}