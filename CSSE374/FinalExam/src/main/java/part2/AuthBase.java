package part2;

import java.util.Collection;
import java.util.Scanner;

import treedb.DefaultDatabase;
import treedb.ICreateBehavior;
import treedb.IDatabase;
import treedb.INode;
import treedb.TreeDatabaseException;
import treedb.TreePath;

public class AuthBase implements IDatabase{
	boolean authenticated = false;
	boolean first = true;
	
	IDatabase wrappeddatabase;
	
	public AuthBase(IDatabase db) {
		// TODO Auto-generated constructor stub
		this.wrappeddatabase = db;
	}

	public void authenticate() {
		Scanner sysin = new Scanner(System.in);
		System.out.println("Please enter your username");
		String s = sysin.nextLine();
		System.out.println("please enter your password (hint: maybe it's abc?)");
		String j = sysin.next();
		if (j.equals("abc")) {
			authenticated = true;
		}
	}

	public boolean authenticated() {
		if (first) {
			authenticate();
			first = false;
		}
		return authenticated;
	}

	public INode create(TreePath path, Object value) throws TreeDatabaseException {
		// TODO Auto-generated method stub
		if (authenticated()) {
			return wrappeddatabase.create(path, value);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

	public void addBehavior(String key, ICreateBehavior value) {
		// TODO Auto-generated method stub
		if (authenticated()) {
			wrappeddatabase.addBehavior(key, value);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

	public INode delete(TreePath path) throws TreeDatabaseException {
		// TODO Auto-generated method stub
		if (authenticated()) {
			return wrappeddatabase.delete(path);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

	public INode read(TreePath path) throws TreeDatabaseException {
		// TODO Auto-generated method stub
		if (authenticated()) {
			return wrappeddatabase.read(path);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

	public Collection<INode> searchKey(String key) {
		// TODO Auto-generated method stub
		if (authenticated()) {
			return wrappeddatabase.searchKey(key);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

	public INode searchUUID(String uuid) {
		// TODO Auto-generated method stub
		if (authenticated()) {
			return wrappeddatabase.searchUUID(uuid);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

	public Collection<INode> searchValue(Object data) {
		// TODO Auto-generated method stub
		if (authenticated()) {
			return wrappeddatabase.searchValue(data);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

	public INode update(TreePath path, Object value) throws TreeDatabaseException {
		// TODO Auto-generated method stub
		if (authenticated()) {
			return wrappeddatabase.update(path, value);
		} else {
			throw new RuntimeException("unsecured!");
		}
	}

}
