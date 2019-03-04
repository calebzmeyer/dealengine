package filter;

import java.util.List;

public abstract class LogicFilter extends Filter {
	
	private List<Filter> filterList;
	
	public LogicFilter(List<Filter> filterList) {
		this.filterList = filterList;
	}
	
	public List<Filter> getFilterList(){
		return this.filterList;
	}
}