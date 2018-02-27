package client;

import static treedb.TreePath.at;

import java.util.Collection;

import part2.AuthBase;
import treedb.CompositeNode;
import treedb.DataNode;
import treedb.DataNodeBehavior;
import treedb.DefaultDatabase;
import treedb.IDatabase;
import treedb.INode;
import treedb.TreeDatabaseException;
import utils.PerformanceBenchmark;

public class Client {
	public static void main(String[] args) throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());

		// Create an entry for Chandan
		INode node = new CompositeNode();
		node.put("name", new DataNode("Chandan Rupakheti"));
		node.put("room", new DataNode("F212"));

		db.create(at("/rose-hulman/faculty/rupakheti"), node);

		// Create an entry for Mark
		node = new CompositeNode();
		node.put("name", new DataNode("Mark Hays"));
		node.put("room", new DataNode("F214"));
		String marksOriginalUUID = node.getUUID();

		db.create(at("/rose-hulman/faculty/hays"), node);
		
		// Create an entry for Steve
		node = new CompositeNode();
		node.put("name", new DataNode("Steve Chenoweth"));
		node.put("room", new DataNode("F214"));

		db.create(at("/rose-hulman/faculty/chenoweth"), node);
		
		// Create an entry for Lynn
		node = new CompositeNode();
		node.put("name", new DataNode("Lynne Degler"));
		node.put("room", new DataNode("F231"));
		db.create(at("rose-hulman/staff/degler/"), node);
		
		
		// Get and print Steve's info
		INode steve = db.read(at("/rose-hulman/faculty/chenoweth"));
		System.out.println("Steve: " + steve);

		// Got room number for Steve wrong, it should be D215B, update using the db.update() api
		db.update(at("/rose-hulman/faculty/chenoweth/room"), "D215B"); // Notice the extra /room at the end
		steve = db.read(at("/rose-hulman/faculty/chenoweth"));
		System.out.println("Steve Updated: " + steve);
		System.out.println();

		INode lynnsName = db.read(at("rose-hulman/staff/degler/name"));
		System.out.println("Lynn's Name: " + lynnsName);

		// Got Lynn's name wrong, let's search Lynn using her key and update her node using the INode.put() api
		Collection<INode> allDeglers = db.searchKey("degler");
		for(INode aDegler: allDeglers) {
			// This will update all nodes that is associated with the "degler" key, however, 
			// there is only one "degler" key in the DB at the moment.
			aDegler.put("name", new DataNode("Lynn Degler"));
		}
		// Let's check if the change worked
		lynnsName = db.read(at("rose-hulman/staff/degler/name"));
		System.out.println("Updated Lynn's Name: " + lynnsName);
		System.out.println();
		
		System.out.println("Let's search for Mark Hays using value-based search api ...");
		Collection<INode> allHays = db.searchValue("Mark Hays");
		allHays.forEach(aHays -> {
			System.out.println("Mark's name (the value) is found: " + aHays);
		});
		
		// Turns out we could have searched him using marksOriginalUUID right from the get go, see line #30
		INode anotherMark = db.searchUUID(marksOriginalUUID);
		System.out.println("Mark (the node) is found again: " + anotherMark);
		System.out.println();

		// Let's do key-based search performance benchmarking
		// NOTE: Uncomment code below to prevent slowdown for regular use. 
		System.out.println("Creating sample database for benchmarking. This may take 10s of seconds ...");
		PerformanceBenchmark benchmark = new PerformanceBenchmark(17, 2, 10);
		System.out.println("Running Key-based search benchmarking. This may also take some 10s of seconds ...");
		long averageTime = benchmark.getKeySearchAverageTime(15);
		System.out.println("Average case key search time: " + averageTime + " nano seconds!");
	}
}
