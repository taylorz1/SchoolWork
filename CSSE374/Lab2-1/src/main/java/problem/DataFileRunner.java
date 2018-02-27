package problem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;

public class DataFileRunner extends ProcessRunner{
	
	public DataFileRunner() {
		// TODO Auto-generated constructor stub
		super();
		processes = new LinkedList<>();
		this.command = null;
	}

	@Override
	void execute(Path file) {
		ProcessBuilder processBuilder = null;
		String arg = null;

		// TODO: F1. Add support for new type of application
		// TODO: F2. Add support for new type of directory event handler
		String fileName = file.toFile().getAbsolutePath();
		System.out.println("Processing " + fileName + "...");
	
		if(fileName.endsWith(".html") || fileName.endsWith(".htm")) {
			command = "explorer";
			// For Mac, instead of "explorer" use the following:
			// command = "/Applications/Safari.app/Contents/MacOS/Safari";
			arg = fileName;
		}
		else if(fileName.endsWith(".txt")) {
			command = "Notepad";
			// For Mac, instead of "Notepad" use the following:
			// command = "/Applications/TextEdit.app/Contents/MacOS/TextEdit";
			arg = fileName;
		}
		else {
			command = null;
			System.err.format("No support available for: %s...%n", file);
			return;
		}

		// Run the application if support is available
		try {
			System.out.format("Launching %s ...%n", command);
			processBuilder = new ProcessBuilder(command, arg);
			
			// Start and add the process to the processes list
			Process process = processBuilder.start();
			this.processes.add(process);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
