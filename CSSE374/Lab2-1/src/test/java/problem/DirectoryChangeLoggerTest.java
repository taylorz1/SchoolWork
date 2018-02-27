package problem;

import static org.junit.Assert.*;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;

public class DirectoryChangeLoggerTest {
	// TODO: Refactor this test to match with the new implementation.
	// The general idea is you should create a TestClass per Java class.

	// TODO: You need to test the addition of handler logic also

	private DirectoryMonitorService service;
	private DirectoryChangeLogger dcl;
	private Thread thread;
	private String baseDir = "./input_output";
	private String srcTxtFile = baseDir + "/test_files/test.txt";
	private String destTxtFile = baseDir + "/test.txt";
	private String srcHtmlFile = baseDir + "/test_files/test.html";
	private String destHtmlFile = baseDir + "/test.html";
	Path dir;

	@Before
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(destTxtFile));
		Files.deleteIfExists(Paths.get(destHtmlFile));

		dir = Paths.get(baseDir);
		dcl = new DirectoryChangeLogger(dir);
		service = new DirectoryMonitorService(dir);
		service.addListener(dcl);
		thread = new Thread(service);

		thread.start();
	}

	@After
	public void tearDown() throws Exception {
		service.stopGracefully();
//		thread.join();

		// Let's delete the copied files from the base directory
		Files.deleteIfExists(Paths.get(destTxtFile));
		Files.deleteIfExists(Paths.get(destHtmlFile));
	}

	@Test
	public final void testEventPrint() throws Exception {
		Files.copy(Paths.get(srcHtmlFile), Paths.get(destHtmlFile));

		Thread.sleep(10);
		
		// Let's check if it worked
		String actual = dcl.getPrinted();
		String expected = dir.toString();

		assertEquals("Problem checking directoryLogger!", expected, actual);
	}
}
