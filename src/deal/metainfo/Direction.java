package deal.metainfo;

public enum Direction {

	None,
	NS,
	EW,
	All;
	
	public String toString() {
		
		if(ordinal() == 0) return "None";
		if(ordinal() == 1) return "NS";
		if(ordinal() == 2) return "EW";
		return "All";
	}
}
