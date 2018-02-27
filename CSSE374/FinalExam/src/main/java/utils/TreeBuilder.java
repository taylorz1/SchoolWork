package utils;

import treedb.CompositeNode;
import treedb.DataNode;
import treedb.INode;

public class TreeBuilder {
	int maxDepth = 3;
	int dataFanOut = 2;
	int compositeFanOut = 2;
	String compositeKeyPattern = "compositekey-%d-%d";
	String dataKeyPattern = "datakey-%d-%d";
	String dataValuePattern = "datavalue-%d-%d";
	
	public static TreeBuilder create() {
		return new TreeBuilder();
	}
	
	private TreeBuilder() {}
	
	public TreeBuilder setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
		return this;
	}

	public TreeBuilder setDataFanOut(int dataFanOut) {
		this.dataFanOut = dataFanOut;
		return this;
	}

	public TreeBuilder setCompositeFanOut(int compositeFanOut) {
		this.compositeFanOut = compositeFanOut;
		return this;
	}

	public TreeBuilder setCompositeKeyPattern(String compositeKeyPattern) {
		String[] splits = compositeKeyPattern.split("%d");
		if(splits.length < 2)
			throw new IllegalArgumentException("The supplied argument string must have two %d entries in it.");

		this.compositeKeyPattern = compositeKeyPattern;
		return this;
	}

	public TreeBuilder setDataKeyPattern(String dataKeyPattern) {
		String[] splits = compositeKeyPattern.split("%d");
		if(splits.length < 2)
			throw new IllegalArgumentException("The supplied argument string must have two %d entries in it.");

		this.dataKeyPattern = dataKeyPattern;
		return this;
	}

	public TreeBuilder setDataValuePattern(String dataValuePattern) {
		String[] splits = compositeKeyPattern.split("%d");
		if(splits.length < 2)
			throw new IllegalArgumentException("The supplied argument string must have two %d entries in it.");

		this.dataValuePattern = dataValuePattern;
		return this;
	}

	public INode build() {
		INode compositeNode = new CompositeNode();
		this.buildTreeHelper(compositeNode, 1);
		return compositeNode;
	}
	
	private void buildTreeHelper(INode node, int depth) {
		if(depth > maxDepth)
			return;
		
		// Populate some data
		for(int i = 1; i <= dataFanOut; ++i) {
			String key = String.format(this.dataKeyPattern, depth, i);
			String value = String.format(this.dataValuePattern, depth, i);
			node.put(key, new DataNode(value));
		}
		
		// Expand the subtree
		for(int i = 1; i <= compositeFanOut; ++i) {
			String key = String.format(this.compositeKeyPattern, depth, i);
			INode composite = new CompositeNode();
			node.put(key, composite);
			this.buildTreeHelper(composite, depth + 1);
		}
	}	
}
