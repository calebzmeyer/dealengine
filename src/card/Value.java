package card;

public enum Value {
	
	Two,
	Three,
	Four,
	Five,
	Six,
	Seven,
	Eight,
	Nine,
	Ten,
	Jack,
	Queen,
	King,
	Ace;
	
	public String toString() {
		
		if(ordinal() > 7) {
			
			if(ordinal() == 12) return "A";
			if(ordinal() == 11) return "K";
			if(ordinal() == 10) return "Q";
			if(ordinal() == 9) return "J";
			return "T";
			
		}
		
		else return "" + (ordinal() + 2);
	}
}