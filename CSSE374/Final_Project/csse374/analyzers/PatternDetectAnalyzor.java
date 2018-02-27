package csse374.analyzers;

import csse374.Project.Model;

public abstract class PatternDetectAnalyzor extends AbstractAnalyzor {
	protected Model model;

	@Override
	public void analyze(Model model) {
		this.model = model;
		detect();
	}
	
	abstract protected void detect();

}
