package com.excilys.computer_database.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDAOImpl.class)
public class TestComputerService {

	private static ComputerService compService;
	private static ComputerDAOImpl compDAO = Mockito.mock(ComputerDAOImpl.class);

	@BeforeClass
	public static void executeBeforeTests() {
		Mockito.when(compDAO.getComputers()).thenReturn(new ArrayList<Computer>());
		Mockito.when(compDAO.getComputersPage(Matchers.anyInt(), Matchers.anyInt())).thenReturn(new ArrayList<Computer>());
		Mockito.when(compDAO.getComputer(Matchers.anyInt())).thenReturn(new Computer("Dummy Computer"));
		Mockito.when(compDAO.getComputer(Matchers.anyString())).thenReturn(new Computer("Dummy Computer"));
		Mockito.when(compDAO.createComputer(Matchers.any(Computer.class))).thenReturn(100);

		PowerMockito.mockStatic(ComputerDAOImpl.class);
		PowerMockito.when(ComputerDAOImpl.getInstance()).thenReturn(compDAO);
		
		compService = ComputerServiceImpl.getInstance();
	}
	
	@AfterClass
	public static void executeAfterTests(){
		compService = null;
	}
	
	@Test
	public void testGetComputerWithId() {
		Computer computer = compService.getComputer(1);
		assertEquals("Dummy Computer", computer.getName());
	}
	
	@Test
	public void testGetComputerWithName() {
		Computer computer = compService.getComputer("Dummy Computer");
		assertEquals("Dummy Computer", computer.getName());
	}

	@Test(expected = IntegrityException.class)
	public void testGetComputerNullName() {
		compService.getComputer(null);
	}

	@Test(expected = IntegrityException.class)
	public void testGetComputerEmptyName() {
		compService.getComputer("");
	}

	@Test(expected = IntegrityException.class)
	public void testGetComputerInvalidID() {
		compService.getComputer(-1);
	}
	
	@Test
	public void createComputer() {
		
		Computer computer = new Computer("Dummy Computer");
		assertEquals(100, compService.createComputer(computer));
		
		computer.setCompany(new Company(1, "Dummy Comypany"));	
		computer.setIntroduced(LocalDateTime.of(2012, 1, 1, 0, 0, 0));
		computer.setDiscontinued(LocalDateTime.now());
		assertEquals(100, compService.createComputer(computer));
	}

	@Test(expected = IntegrityException.class)
	public void createComputerNull() {
		compService.createComputer(null);
	}

	@Test(expected = IntegrityException.class)
	public void createComputerInvalidName() {
		compService.createComputer(new Computer(""));
	}

	@Test(expected = IntegrityException.class)
	public void createComputerNullName() {
		compService.createComputer(new Computer(null));
	}

	@Test(expected = IntegrityException.class)
	public void createComputerInvalidDateType() {
		Computer computer= new Computer();
		computer.setCompany(new Company(1, "Dummy Comypany"));	
		computer.setIntroduced(LocalDateTime.now());
		computer.setDiscontinued(LocalDateTime.of(2012, 1, 1, 0, 0, 0));

		compService.updateComputer(computer);
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerNull() {
		compService.updateComputer(null);
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerInvalidName() {
		compService.updateComputer(new Computer(""));
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerNullName() {
		compService.updateComputer(new Computer(null));
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerInvalidDateType() {
		Computer computer= new Computer();
		computer.setCompany(new Company(1, "Dummy Comypany"));	
		computer.setIntroduced(LocalDateTime.now());
		computer.setDiscontinued(LocalDateTime.of(2012, 1, 1, 0, 0, 0));

		compService.updateComputer(computer);
	}
	
	@Test(expected = IntegrityException.class)
	public void deleteComputerNull() {
		compService.deleteComputer(0);
	}

	@Test(expected = IntegrityException.class)
	public void deleteComputerInvalid() {
		compService.deleteComputer(-1);
	}
}
