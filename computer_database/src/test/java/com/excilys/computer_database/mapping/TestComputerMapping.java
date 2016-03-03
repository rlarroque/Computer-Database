package com.excilys.computer_database.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computer_database.persistence.db.utils.DbUtils;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;
import com.excilys.computer_database.webapp.dto.ComputerDTO;

@ContextConfiguration(locations = {"classpath:/test-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestComputerMapping {

    // Test queries
    private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
    private static final String GET_COMPUTER_BY_NAME_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id where computer.name=?";
    private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
    private static final Logger LOGGER = LoggerFactory.getLogger(TestComputerMapping.class);

    private Connection connection;
    private Statement statement;

    @Autowired
    private DataSource dataSource;

    /**
     * Before all tests remove all companies and add a single one. Then add a computer.
     */
    @Before
    public void executeBeforeEachTests() {

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
            statement.executeUpdate("TRUNCATE company");
            statement.executeUpdate("TRUNCATE computer");
            statement.execute("SET FOREIGN_KEY_CHECKS=1");

            PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
            ps.setString(1, "Dummy Company");
            ps.executeUpdate();

            ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
            ps.setString(1, "Dummy computer");
            ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
            ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
            ps.setInt(4, 1);
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot truncate table", e);
        }
    }

    /**
     * After all tests close the connection and statement.
     */
    @After
    public void executerApresChaqueTest() {

        try {
            DbUtils.close(statement);
            DbUtils.close(connection);
        } catch (SQLException e) {
            LOGGER.error("Cannot close connection", e);
        }
    }

    /**
     * Test.
     */
    @Test
    public void testResultSetToComputer() {

        ResultSet resSet;

        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(GET_COMPUTER_BY_NAME_QUERY);
            ps.setString(1, "Dummy computer");
            resSet = ps.executeQuery();

            LocalDate intro = LocalDate.of(2000, 1, 1);
            LocalDate disc = LocalDate.of(2001, 1, 1);

            if (resSet.next()) {
                Computer computer = ComputerMapper.toComputer(resSet);

                assertEquals("Dummy computer", computer.getName());
                assertEquals(intro, computer.getIntroduced());
                assertEquals(disc, computer.getDiscontinued());
                assertEquals("Dummy Company", computer.getCompany().getName());
            } else {
                fail("No ResultSet.");
            }

        } catch (SQLException e) {
            fail("Cannot get companies table");
        }
    }

    /**
     * Test.
     */
    @Test
    public void testComputerToDTO() {

        Company company = new Company(1l, "Dummy Company");
        Computer computer = new Computer("Dummy Computer");
        computer.setIntroduced(LocalDate.of(2000, 1, 1));
        computer.setDiscontinued(LocalDate.of(2001, 1, 1));
        computer.setCompany(company);

        ComputerDTO dto = ComputerMapper.toDTO(computer);

        assertEquals(computer.getName(), dto.getName());
        assertEquals(computer.getIntroduced().toString(), dto.getIntroducedDate());
        assertEquals(computer.getDiscontinued().toString(), dto.getDiscontinuedDate());
        assertEquals(computer.getCompany().getName(), dto.getCompanyName());
    }

    /**
     * Test.
     */
    @Test
    public void testComputerToDTOWithNull() {

        Computer computer = new Computer("Dummy Computer");
        ComputerDTO dto = ComputerMapper.toDTO(computer);

        assertEquals(computer.getName(), dto.getName());
        assertEquals("", dto.getIntroducedDate());
        assertEquals("", dto.getDiscontinuedDate());
        assertEquals("", dto.getCompanyName());
    }

    /**
     * Test.
     */
    @Test
    public void testDtoToComputer() {

        ComputerDTO dto = createDummyDTO();
        Computer computer = ComputerMapper.toComputer(dto);

        assertEquals(dto.getName(), computer.getName());
        assertEquals(dto.getIntroducedDate(), computer.getIntroduced().toString());
        assertEquals(dto.getDiscontinuedDate(), computer.getDiscontinued().toString());
        assertEquals(dto.getCompanyName(), computer.getCompany().getName());
    }

    /**
     * Test.
     */
    @Test
    public void testDtoToComputerWithNullDate() {
        ComputerDTO dto = createDummyDTO();
        dto.setIntroducedDate("");
        dto.setDiscontinuedDate("");
        dto.setCompanyName("");
        dto.setCompanyId(0);

        Computer computer = ComputerMapper.toComputer(dto);

        assertEquals(dto.getName(), computer.getName());
        assertEquals(null, computer.getIntroduced());
        assertEquals(null, computer.getDiscontinued());
        assertEquals(null, computer.getCompany());
        assertEquals(null, computer.getCompany());
    }

    /**
     * Creation of dummy computerDTO
     * @return the dummy DTO
     */
    public ComputerDTO createDummyDTO() {

        ComputerDTO dto = new ComputerDTO();
        dto.setName("Dummy Computer");
        dto.setIntroducedDate("2000-01-01");
        dto.setDiscontinuedDate("2001-01-01");
        dto.setCompanyId(1);
        dto.setCompanyName("Dummy Company");

        return dto;
    }
}
