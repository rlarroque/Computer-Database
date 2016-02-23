package com.excilys.computer_database.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.persistence.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.persistence.db.ConnectionFactory;
import com.excilys.computer_database.persistence.db.utils.DbUtils;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;

public class TestComputerDAOImpl {

    // Test queries
    private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
    private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";

    private ComputerDAOImpl computerDAO;
    private Connection connection;
    private Statement statement;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    /**
     * Before all tests remove all companies and create a single one.
     */
    @Before
    public void executeBeforeEachTests() {
        computerDAO = ComputerDAOImpl.getInstance();

        try {
            connection = ConnectionFactory.getConnection();
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
            logger.error("Cannot truncate table");
        }
    }

    /**
     * Close connection and statement.
     */
    @After
    public void executeAfterEachTests() {
        computerDAO = null;

        try {
            DbUtils.close(statement);
            DbUtils.close(connection);
        } catch (SQLException e) {
        }
    }

    /**
     * Test.
     * @throws SQLException exception
     */
    @Test
    public void testGetComputers() throws SQLException {
        Connection mconnection = null;

        List<Computer> computers = computerDAO.getAll();
        assertEquals(0, computers.size());

        try {
            mconnection = ConnectionFactory.getConnection();
            PreparedStatement ps = mconnection.prepareStatement(CREATE_COMPUTER_QUERY);
            ps.setString(1, "Dummy computer");
            ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
            ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
            ps.setInt(4, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Cannot create computer");
        } finally {

            try {
                computers = computerDAO.getAll();
                assertEquals(1, computers.size());

                mconnection.close();
            } catch (SQLException e) {
                logger.debug("Cannot close connection!");
            }
        }
    }

    /**
     * Test.
     * @throws SQLException exception
     */
    @Test
    public void testGetComputersPage() throws SQLException {
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
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
            ps.setString(1, "Dummy computer");
            ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
            ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
            ps.setInt(4, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Cannot create computer");
        }

        Computer computer = computerDAO.get(1);
        assertEquals("Dummy computer", computer.getName());
        assertEquals("Dummy Company", computer.getCompany().getName());
    }

    /**
     * Test.
     */
    @Test
    public void testCreateComputer() {

        Company company = new Company(1, "Dummy Company");
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
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
            ps.setString(1, "Dummy computer");
            ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
            ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
            ps.setInt(4, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Cannot create computer");
        }

        Company company = new Company(1, "Dummy Company");
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
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
            ps.setString(1, "Dummy computer");
            ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
            ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
            ps.setInt(4, 1);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            logger.error("Cannot create computer");
        }

        try {
            computerDAO.delete(1);
        } catch (SQLException e) {
        }
        assertEquals(0, computerDAO.getAll().size());
    }
}