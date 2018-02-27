package problem;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppLauncherApplication {
	public static void main(String[] args) throws IOException, InterruptedException {
		// Register directory to the launcher
		Path dir = Paths.get("./input_output");
		DirectoryMonitorService directoryMonitorService = new DirectoryMonitorService(dir);
		AppLauncher app = new AppLauncher();
		directoryMonitorService.addListener(app);
		directoryMonitorService.addListener(new DirectoryChangeLogger(dir));
		ProcessRunner dataFileRunner = new DataFileRunner();
		app.addRunner(".html", dataFileRunner);
		app.addRunner(".txt", dataFileRunner);
		Thread thread = new Thread(directoryMonitorService);
		thread.start();

		System.out.format("Launcher started watching %s ...%nPress the return key to stop ...", dir);

		// Wait for an input
		System.in.read();
		
		directoryMonitorService.stopGracefully();
		thread.join();

		
		System.out.println("Directory watching stopped ...");
	}
}
