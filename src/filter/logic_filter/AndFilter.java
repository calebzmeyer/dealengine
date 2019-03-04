package filter.logic_filter;

import java.util.List;

import deal.Deal;
import filter.Filter;
import filter.LogicFilter;

public class AndFilter extends LogicFilter {
	
	public AndFilter(List<Filter> filterList) {
		super(filterList);
	}

	@Override
	public boolean test(Deal deal) {
		
		for(Filter filter : this.getFilterList()) {
			if(!filter.test(deal)) return false;
		}
		
		return true;
	}
}