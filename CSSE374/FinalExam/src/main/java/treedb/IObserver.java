package treedb;

public interface IObserver {
	public void update(INode changed, String oldkey, int status);

}
