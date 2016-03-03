package com.excilys.computer_database.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.persistence.db.utils.DbUtils;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;

@ContextConfiguration(locations = { "classpath:/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComputerDAOImpl {

    // Test queries
    private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
    private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";

    private static final Logger LOGGER = LoggerFactory.getLogger(TestComputerDAOImpl.class);

    private Connection connection;
    private Statement statement;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ComputerDAO computerDAO;

    /**
     * Before all tests remove all companies and create a single one.
     */
    @Before
    public void executeBeforeEachTests() {

        MockitoAnnotations.initMocks(this);

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            statement.executeUpdate("TRUNCATE computer");
            statement.executeUpdate("TRUNCATE company");
            statement.execute("SET FOREIGN_KEY_CHECKS=1");

            PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
            ps.setString(1, "Dummy Company");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot truncate table", e);
        }
    }

    /**
     * Close connection and statement.
     */
    @After
    public void executeAfterEachTests() {

        try {
            DbUtils.close(statement);
            DbUtils.close(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot close connection", e);
        }
    }

    /**
     * Test.
     * @throws SQLException exception
     */
    @Test
    public void testGetComputersNone() {

        List<Computer> computers = computerDAO.getAll();
        assertEquals(0, computers.size());
    }
    
    /**
     * Test.
     * @throws SQLException exception
     */
    @Test
    public void testGetComputers() {

        addDummyComputer();

        List<Computer> computers = computerDAO.getAll();
        assertEquals(1, computers.size());
    }

    /**
     * Test.
     * @throws SQLException exception
     */
    @Test
    public void testGetComputersPage() {
        List<Computer> computers = computerDAO.getAll();
        assertEquals(0, computers.size());

        Page page = new Page(1, 10, "id");
        page.setFilter("");

        for (int i = 0; i < 50; i++) {
            computerDAO.create(new Computer("Dummy computer " + i));
        }

        computers = computerDAO.getAll();
        assertEquals(50, computers.size());
        assertEquals("Dummy computer 0", computers.get(0).getName());
        assertEquals("Dummy computer 9", computers.get(9).getName());

        computers = computerDAO.getPage(page);
        assertEquals(10, computers.size());
        assertEquals("Dummy computer 0", computers.get(0).getName());
        assertEquals("Dummy computer 9", computers.get(9).getName());

        page.setStartIndex(10);
        computers = computerDAO.getPage(page);
        assertEquals(10, computers.size());
        assertEquals("Dummy computer 10", computers.get(0).getName());
        assertEquals("Dummy computer 19", computers.get(9).getName());
    }

    /**
     * Test.
     */
    @Test
    public void testGetComputerById() {

        addDummyComputer();

        Computer computer = computerDAO.get(1);
        assertEquals("Dummy computer", computer.getName());
        assertEquals("Dummy Company", computer.getCompany().getName());
    }
    
    /**
     * Test.
     */
    @Test
    public void testGetComputerByName() {

        addDummyComputer();

        Computer computer = computerDAO.get("Dummy computer");
        assertEquals("Dummy computer", computer.getName());
        assertEquals("Dummy Company", computer.getCompany().getName());
    }
    
    /**
     * Test.
     */
    @Test
    public void testGetComputerByNameWrong() {

        addDummyComputer();

        Computer computer = computerDAO.get("Not the right dummy computer");
        assertEquals(null, computer);
    }

    /**
     * Test.
     */
    @Test
    public void testCreateComputer() {

        Company company = new Company(1l, "Dummy Company");
        Computer computer = new Computer("Dummy computer");
        computer.setIntroduced(LocalDate.of(2000, 1, 1));
        computer.setDiscontinued(LocalDate.of(2001, 1, 1));
        computer.setCompany(company);

        computerDAO.create(computer);

        assertEquals("Dummy computer", computerDAO.get(1).getName());
    }

    /**
     * Test.
     */
    @Test
    public void testUpdateComputer() {

        addDummyComputer();

        Company company = new Company(1l, "Dummy Company");
        Computer computer = new Computer("Not so dummy computer");
        computer.setId(1);
        computer.setIntroduced(LocalDate.of(2005, 1, 1));
        computer.setDiscontinued(LocalDate.of(2010, 6, 1));
        computer.setCompany(company);

        computerDAO.update(computer);

        assertEquals("Not so dummy computer", computerDAO.get(1).getName());
        assertEquals(LocalDate.of(2005, 1, 1), computerDAO.get(1).getIntroduced());
        assertEquals(LocalDate.of(2010, 6, 1), computerDAO.get(1).getDiscontinued());
        assertEquals(company, computerDAO.get(1).getCompany());
    }

    /**
     * Test.
     */
    @Test
    public void testDeleteComputer() {

        addDummyComputer();

        try {
            computerDAO.delete(1);
        } catch (SQLException e) {
            LOGGER.error("Delete computer failed", e);
            fail("SQl exception thrown");
        }
        assertEquals(0, computerDAO.getAll().size());
    }

    /**
     * Method used to add a dummy computer into the test database.
     */
    public void addDummyComputer() {

        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
            ps.setString(1, "Dummy computer");
            ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
            ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
            ps.setInt(4, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot create dummy computer", e);
        }
    }
}