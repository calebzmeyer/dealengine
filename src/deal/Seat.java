package deal;

public enum Seat {
	
	North,
	East,
	South,
	West;
	
	public String toString() {
		
		if(ordinal() == 0) return "N";
		if(ordinal() == 1) return "E";
		if(ordinal() == 2) return "S";
		return "W";
	}
}