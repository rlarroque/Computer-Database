package com.excilys.computer_database.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
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

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.persistence.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.service.impl.ComputerServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDAOImpl.class)
public class TestComputerService {
	private static ComputerService compService;
	private static ComputerDAOImpl compDAO = Mockito.mock(ComputerDAOImpl.class);

	@BeforeClass
	public static void executeBeforeTests() {
		Mockito.when(compDAO.getAll()).thenReturn(new ArrayList<Computer>());
		Mockito.when(compDAO.getPage(Matchers.any(Page.class))).thenReturn(new ArrayList<Computer>());
		Mockito.when(compDAO.get(Matchers.anyInt())).thenReturn(new Computer("Dummy Computer"));
		Mockito.when(compDAO.get(Matchers.anyString())).thenReturn(new Computer("Dummy Computer"));
		Mockito.when(compDAO.create(Matchers.any(Computer.class))).thenReturn(100);

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
		Computer computer = compService.get(1);
		assertEquals("Dummy Computer", computer.getName());
	}
	
	@Test
	public void testGetComputerWithName() {
		Computer computer = compService.get("Dummy Computer");
		assertEquals("Dummy Computer", computer.getName());
	}

	@Test(expected = IntegrityException.class)
	public void testGetComputerNullName() {
		compService.get(null);
	}

	@Test(expected = IntegrityException.class)
	public void testGetComputerEmptyName() {
		compService.get("");
	}

	@Test(expected = IntegrityException.class)
	public void testGetComputerInvalidID() {
		compService.get(-1);
	}
	
	@Test
	public void createComputer() {
		
		Computer computer = new Computer("Dummy Computer");
		assertEquals(100, compService.create(computer));
		
		computer.setCompany(new Company(1, "Dummy Comypany"));	
		computer.setIntroduced(LocalDate.of(2012, 1, 1));
		computer.setDiscontinued(LocalDate.now());
		assertEquals(100, compService.create(computer));
	}

	@Test(expected = IntegrityException.class)
	public void createComputerNull() {
		compService.create(null);
	}

	@Test(expected = IntegrityException.class)
	public void createComputerInvalidName() {
		compService.create(new Computer(""));
	}

	@Test(expected = IntegrityException.class)
	public void createComputerNullName() {
		compService.create(new Computer(null));
	}

	@Test(expected = IntegrityException.class)
	public void createComputerInvalidDateType() {
		Computer computer= new Computer();
		computer.setCompany(new Company(1, "Dummy Comypany"));	
		computer.setIntroduced(LocalDate.now());
		computer.setDiscontinued(LocalDate.of(2012, 1, 1));

		compService.update(computer);
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerNull() {
		compService.update(null);
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerInvalidName() {
		compService.update(new Computer(""));
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerNullName() {
		compService.update(new Computer(null));
	}

	@Test(expected = IntegrityException.class)
	public void updateComputerInvalidDateType() {
		Computer computer= new Computer();
		computer.setCompany(new Company(1, "Dummy Comypany"));	
		computer.setIntroduced(LocalDate.now());
		computer.setDiscontinued(LocalDate.of(2012, 1, 1));

		compService.update(computer);
	}
	
	@Test(expected = IntegrityException.class)
	public void deleteComputerNull() {
		try {
			compService.delete(0);
		} catch (SQLException e) {
		}
	}

	@Test(expected = IntegrityException.class)
	public void deleteComputerInvalid() {
		try {
			compService.delete(-1);
		} catch (SQLException e) {
		}
	}
}
