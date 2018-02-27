package utils;

import static treedb.TreePath.at;

import java.util.Random;

import treedb.DefaultDatabase;
import treedb.IDatabase;
import treedb.INode;
import treedb.TreeDatabaseException;

public class PerformanceBenchmark {
	int maxDepth;
	int compositeFanOut;
	int dataFanOut;
	IDatabase db;
	
	public PerformanceBenchmark(int maxDepth, int compositeFanOut, int dataFanOut) throws TreeDatabaseException {
		this.maxDepth = maxDepth;
		this.compositeFanOut = compositeFanOut;
		this.dataFanOut = dataFanOut;
		this.createDb();
	}
	
	private void createDb()  throws TreeDatabaseException {
		System.out.println("Creating test database ...");
		long startTime = System.currentTimeMillis();
		INode topNode = TreeBuilder.create()
									.setMaxDepth(this.maxDepth)								// Maximum depth of tree
									.setCompositeFanOut(this.compositeFanOut)						// Total number of composite nodes per level
									.setDataFanOut(this.dataFanOut)								// Total number of data nodes per level
									.setCompositeKeyPattern("composite-%d-%d-key")	// composite-{level}-{fanout}-key, e.g., for a level 1 first composite node: composite-1-1-key 
									.setDataKeyPattern("data-%d-%d-key")				// data-{level}-{fanout}-key, e.g., for level 2 third data node: data-2-3-key
									.setDataValuePattern("data-%d-%d-value")			// data-{level}-{fanout}-value - value stored in a data node, e.g., data-2-3-value
									.build();
		this.db = new DefaultDatabase();
		db.create(at("root"), topNode);
		long endTime = System.currentTimeMillis();
		double time = (endTime - startTime)/1000.0;
		System.out.println("DB created in " + time + " secs!");
	}
	
	private long randomKeySearch() {
		Random random = new Random(System.currentTimeMillis());
		
		long randomLevel = 1 + random.nextInt(maxDepth); 
		long compositeIndex = 1 + random.nextInt(compositeFanOut); 
		long dataIndex = 1 + random.nextInt(dataFanOut);

		boolean compositeSearch = random.nextBoolean();
		
		String key = "";
		if(compositeSearch) 
			key = String.format("composite-%d-%d-key", randomLevel, compositeIndex);
		else
			key = String.format("data-%d-%d-key", randomLevel, dataIndex);
			
		long startTime = System.nanoTime();
		this.db.searchKey(key);
		long endTime = System.nanoTime();
		
		return endTime - startTime;
	}
	
	// In nano seconds unit
	public long getKeySearchAverageTime(int iterations) {
		long totalDeltas = 0;
		for(int i = 0; i < iterations; ++i) {
			totalDeltas += this.randomKeySearch();
		}
		return totalDeltas/iterations;
	}
}
