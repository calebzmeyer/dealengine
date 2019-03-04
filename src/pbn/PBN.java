package pbn;

import deal.Deal;
import deal.metainfo.Dealer;
import deal.metainfo.Vulnerability;

public class PBN {
	
	private int boardNo;
	private Dealer dealer;
	private Vulnerability vulnerability;
	private Deal deal;
	
	public PBN(int boardNo, Dealer dealer, Vulnerability vulnerability, Deal deal) {
		
		this.boardNo = boardNo;
		this.dealer = dealer;
		this.vulnerability = vulnerability;
		this.deal = deal;
	}
	
	public String toString() {
		
		String returnString = "";
		
		returnString += "[Board \"" + boardNo + "\"]" + "\n";
		returnString += dealer.toString() + "\n";
		returnString += vulnerability.toString() + "\n";
		returnString += deal.toString();
		
		return returnString;
	}
}