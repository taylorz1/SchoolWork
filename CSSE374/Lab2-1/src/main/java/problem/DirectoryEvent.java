package problem;

import java.nio.file.Path;

public class DirectoryEvent {
	private final String name;
	private final Path child;
	private final DirectoryMonitorService service;
	
	public DirectoryEvent(String name, Path child, DirectoryMonitorService service) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.child = child;
		this.service = service;
	}

	public String getEventType() {
		return this.name;

	}

	public Path getFile() {
		return this.child.toAbsolutePath();

	}

	public DirectoryMonitorService getSource() {
		return this.service;

	}
}
