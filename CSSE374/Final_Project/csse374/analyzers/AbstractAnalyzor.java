package csse374.analyzers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import csse374.Project.IFilter;
import csse374.Project.Model;

public abstract class AbstractAnalyzor implements IAnalyzor {
	protected List<IFilter> filters = new LinkedList<IFilter>();
	
	public void addFilters(List<IFilter> list) {
		this.filters.addAll(list);
	}

	@Override
	abstract public void analyze(Model model);

}
