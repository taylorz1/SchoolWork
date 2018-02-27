package problem;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DirectoryMonitorService implements Runnable {
	private List<IDirectoryListener> listeners;
	private boolean stop;
	private Path dir;
	private final WatchService watcher;

	public DirectoryMonitorService(Path dir) throws IOException {
		// DONE Auto-generated constructor stub
		this.stop = true;
		this.dir = dir;
		this.listeners = new LinkedList<IDirectoryListener>();
		this.watcher = FileSystems.getDefault().newWatchService();
		dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

	}

	@Override
	public void run() {
		this.stop = false;
		while (!stop) {
			// Wait for key to be signaled
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			// Context for directory entry event is the file name of entry
			List<WatchEvent<?>> events = key.pollEvents();
			if (!events.isEmpty()) {
				@SuppressWarnings("unchecked")
				WatchEvent<Path> event = (WatchEvent<Path>) events.get(0);
				Path name = event.context();
				Path child = dir.resolve(name);

				notifyListeners(new DirectoryEvent(event.kind().name(), child, this));
			}

			// Reset key and remove from set if directory no longer accessible
			if (!key.reset()) {
				break;
			}
		}

		// We gracefully stopped the service now, let's delete the temp file
		this.clearEverything();
	}

	protected void clearEverything() {
		File file = new File(dir.toFile() + "/.temp");
		file.delete();
//		((AppLauncher) this.listeners.get(0)).shutDown();	
	}


	public boolean isRunning() {
		return !this.stop;
	}

	public void stopGracefully() throws IOException {
		this.stop = true;
		File file = new File(dir.toFile() + "/.temp");

		// Let's force the while loop in the run method to come out of the
		// blocking watcher.take() call here
		// You can also create a directory by calling file.mkdir()
		file.createNewFile();
	}
	

	public void addListener(IDirectoryListener l) {
		listeners.add(l);
	}

	public void removeListener(IDirectoryListener l) {
		listeners.remove(l);
	}

	protected void notifyListeners(DirectoryEvent e) {
		System.out.println(listeners.size());
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).directoryChanged(e);
		}
	}

	public int getApplicationsCount() {
		// TODO Auto-generated method stub
		return ((AppLauncher) this.listeners.get(0)).getCount();
	}
}
