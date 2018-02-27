package problem;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class AppLauncherBaseCaseTest {
	@Test
	public final void testStart() throws IOException, InterruptedException {
		//TODO: Refactor this test match with the new implementation 
		
		// Register directory to the launcher
		Path dir = Paths.get("./input_output");
		DirectoryMonitorService launcher = new DirectoryMonitorService(dir);
		launcher.addListener(new AppLauncher());
		launcher.addListener(new DirectoryChangeLogger(dir));
		Thread thread = new Thread(launcher);
		thread.start();

		// Sleep a little
		Thread.sleep(500);
		
		// Check it if is running
		assertEquals("The launcher did not run in normal configuration!", true, launcher.isRunning());
		
		// Check that the base case for process is also ok
		assertEquals("Unexpected number of processes running!", 0, launcher.getApplicationsCount());
		
		launcher.stopGracefully();
//		thread.join();
		
		// Check it if is running
		assertEquals("The launcher did not gracefully stop!", false, launcher.isRunning());
	}
}
