package filter.logic_filter;

import java.util.List;

import deal.Deal;
import filter.Filter;
import filter.LogicFilter;

public class NotFilter extends LogicFilter {

	public NotFilter(List<Filter> filterList) {
		super(filterList);
	}

	@Override
	public boolean test(Deal deal) {

		for(Filter filter : this.getFilterList()) {
			if(!filter.test(deal)) return true;
		}
		
		return false;
	}
}