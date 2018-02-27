import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestGradebookIndividual {

	private TeamGradebook gb;
	
	@Before
	public void setUp() throws Exception {
		this.gb = new TeamGradebook();
	}

	@Test
	public void testAddStudent() {
		assertEquals("ok", gb.handleCommand("add-student Alice"));
		assertEquals("ok", gb.handleCommand("add-student Bob"));
	}
	

	@Test
	public void testGetNames() {
		assertEquals("ok", gb.handleCommand("add-student Alice"));
		assertEquals("ok", gb.handleCommand("add-student Bob"));
		assertEquals("Alice Bob", gb.handleCommand("get-names"));
	}
	
	@Test
	public void testGetStudentsbyName() {
		assertEquals("ok", gb.handleCommand("add-student Alice"));
		assertEquals("ok", gb.handleCommand("add-student Bob"));
		assertEquals("ok", gb.handleCommand("add-student Charlie"));
		assertEquals("Alice", gb.getStudentByName("Alice").getName());
		assertEquals("Bob", gb.getStudentByName("Bob").getName());
		assertEquals("Charlie", gb.getStudentByName("Charlie").getName());
	}
	
	@Test
	public void testAddTeam() {
		assertEquals("ok", gb.handleCommand("add-student Alice"));
		assertEquals("ok", gb.handleCommand("add-student Bob"));
		assertEquals("ok", gb.handleCommand("add-team Ninjas Bob Alice"));
	}
	
	@Test
	public void testAddGrade() {
		assertEquals("ok", gb.handleCommand("add-student Alice"));
		assertEquals("ok", gb.handleCommand("add-student Bob"));
		assertEquals("ok", gb.handleCommand("add-team Ninjas Bob Alice"));
		assertEquals("ok", gb.handleCommand("add-grade Ninjas 97.5"));
	}

	@Test
	public void testGetAverage() {
		assertEquals("ok", gb.handleCommand("add-student Alice"));
		assertEquals("ok", gb.handleCommand("add-student Bob"));
		assertEquals("ok", gb.handleCommand("add-student Charlie"));
		
		assertEquals("ok", gb.handleCommand("add-team Ninjas Bob Alice"));
		assertEquals("ok", gb.handleCommand("add-grade Ninjas 100"));
		assertEquals("ok", gb.handleCommand("add-team Pirates Charlie Bob"));
		assertEquals("ok", gb.handleCommand("add-grade Pirates 50"));
		
		assertEquals("100", gb.handleCommand("get-average Alice"));
		assertEquals("50", gb.handleCommand("get-average Charlie"));
		assertEquals("75", gb.handleCommand("get-average Bob"));
		
		assertEquals("ok", gb.handleCommand("add-grade Ninjas 80"));
		
		assertEquals("90", gb.handleCommand("get-average Alice"));
		assertEquals("77", gb.handleCommand("get-average Bob"));
		assertEquals("50", gb.handleCommand("get-average Charlie"));
		
		assertEquals("ok", gb.handleCommand("add-team OnlyBob Bob"));
		assertEquals("ok", gb.handleCommand("add-grade OnlyBob 71"));
		assertEquals("75", gb.handleCommand("get-average Bob"));
		
	}

	@Test
	public void testGetAverageNoGrades() {
		assertEquals("ok", gb.handleCommand("add-student Alice"));
		assertEquals("0", gb.handleCommand("get-average Alice"));
	}
	
	

}
