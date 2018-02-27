package problem;

import java.nio.file.Path;

public class DirectoryChangeLogger implements IDirectoryListener{
	private Path path;
	private String printed;
	
	public DirectoryChangeLogger(Path path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}

	public void directoryChanged(DirectoryEvent e) {
		this.printed = this.path.toString();
		System.out.println(this.path.toString());

	}
	
	public String getPrinted(){
		return this.printed;
	}
}
