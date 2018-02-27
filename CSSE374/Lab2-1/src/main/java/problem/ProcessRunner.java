package problem;

import java.nio.file.Path;
import java.util.List;

abstract class ProcessRunner {
	protected List<Process> processes;
	protected String command;

	public List<Process> getProcesses() {
		return this.processes;
	}

	public String getCommand(){
		return this.command;
	}
	
	abstract void execute(Path p);
}
