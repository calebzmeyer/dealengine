package generator;

import java.util.ArrayList;
import java.util.List;

import deal.Deal;
import filter.logic_filter.AndFilter;

public class Generator {
	
	private AndFilter conditions;
	
	public Generator(AndFilter conditions) {
		this.conditions = conditions;
	}
	
	public List<Deal> generate(int quantity){
		
		List<Deal> deals = new ArrayList<Deal>();
		
		int count = 0;
		while(count < quantity) {
			
			Deal deal = new Deal();
			if(this.conditions.test(deal)) {
				deals.add(deal);
				count++;
			}
		}
		
		return deals;
	}
}