package com.excilys.computer_database.services.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.exception.IntegrityException;
import com.excilys.computer_database.dao.ComputerDAO;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;
import com.excilys.computer_database.model.Page;
import com.excilys.computer_database.services.ComputerService;

@ContextConfiguration(locations = {"classpath:/test-service-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComputerService {

    @Autowired
    private ComputerService computerService;
    
    @Autowired
    private ComputerDAO mockComputerDao;

    /**
     * Before each test.
     */
    @Before
    public void executeBeforeEachTests() {
        Mockito.when(mockComputerDao.getAll()).thenReturn(new ArrayList<Computer>());
        Mockito.when(mockComputerDao.getPage(Matchers.any(Page.class))).thenReturn(new ArrayList<Computer>());
        Mockito.when(mockComputerDao.get(Matchers.anyInt())).thenReturn(new Computer("Dummy Computer"));
        Mockito.when(mockComputerDao.get(Matchers.anyString())).thenReturn(new Computer("Dummy Computer"));
        Mockito.when(mockComputerDao.create(Matchers.any(Computer.class))).thenReturn(100l);
    }

    /**
     * Test.
     */
    @Test
    public void testGetComputerWithId() {
        Computer computer = computerService.get(1);
        assertEquals("Dummy Computer", computer.getName());
    }

    /**
     * Test.
     */
    @Test
    public void testGetComputerWithName() {
        Computer computer = computerService.get("Dummy Computer");
        assertEquals("Dummy Computer", computer.getName());
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testGetComputerNullName() {
        computerService.get(null);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testGetComputerEmptyName() {
        computerService.get("");
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void testGetComputerInvalidID() {
        computerService.get(-1);
    }

    /**
     * Test.
     */
    @Test
    public void createComputer() {

        Computer computer = new Computer("Dummy Computer");
        assertEquals(100, computerService.create(computer));

        computer.setCompany(new Company(1l, "Dummy Comypany"));
        computer.setIntroduced(LocalDate.of(2012, 1, 1));
        computer.setDiscontinued(LocalDate.now());
        assertEquals(100, computerService.create(computer));
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void createComputerNull() {
        computerService.create(null);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void createComputerInvalidName() {
        computerService.create(new Computer(""));
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void createComputerNullName() {
        computerService.create(new Computer(null));
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void createComputerInvalidDateType() {
        Computer computer = new Computer();
        computer.setCompany(new Company(1l, "Dummy Comypany"));
        computer.setIntroduced(LocalDate.now());
        computer.setDiscontinued(LocalDate.of(2012, 1, 1));

        computerService.update(computer);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void updateComputerNull() {
        computerService.update(null);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void updateComputerInvalidName() {
        computerService.update(new Computer(""));
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void updateComputerNullName() {
        computerService.update(new Computer(null));
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void updateComputerInvalidDateType() {
        Computer computer = new Computer();
        computer.setCompany(new Company(1l, "Dummy Comypany"));
        computer.setIntroduced(LocalDate.now());
        computer.setDiscontinued(LocalDate.of(2012, 1, 1));

        computerService.update(computer);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void deleteComputerNull() {
        computerService.delete(0);
    }

    /**
     * Test.
     */
    @Test(expected = IntegrityException.class)
    public void deleteComputerInvalid() {
        computerService.delete(-1);
    }
}
