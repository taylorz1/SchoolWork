package treedb;

public interface ISubject {
	public void registerObserver(IObserver node);
	public void removeObserver(IObserver node);
	public void notifyObserver(String oldkey, int status); // 0 create/update, 1 delete
}
