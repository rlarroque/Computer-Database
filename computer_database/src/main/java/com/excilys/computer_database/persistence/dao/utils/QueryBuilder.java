package com.excilys.computer_database.persistence.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;

/**
 * The class is used to create complex queries to use in the DAO.
 * @author rlarroque
 */
public class QueryBuilder {

    // SQL Queries
    private static final String GET_COMPUTER_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
    private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
    private static final String UPDATE_COMPUTER_QUERY = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
    private static final String DELETE_COMPUTER_QUERY = "DELETE FROM computer where id = ";
    private static final String DELETE_COMPUTER_COMPANY_QUERY = "DELETE FROM computer where company_id = ";
    private static final String COUNT_COMPUTER_QUERY = "SELECT COUNT(*) from computer LEFT JOIN company ON computer.company_id = company.id";
    private static final String GET_COMPANY_QUERY = "SELECT * FROM company";
    private static final String DELETE_COMPANY_QUERY = "DELETE FROM company where id = ";

    /**
     * Get the query to retrieve all computers.
     * @return the query
     */
    public static String getComputersQuery() {
        return GET_COMPUTER_QUERY;
    }

    /**
     * Get the query to retrieve a computer by company id.
     * @param id id of the company
     * @return the query
     */
    public static String getComputersQuery(long id) {
        String query = GET_COMPUTER_QUERY.concat(" WHERE computer.company_id=" + id);

        return query;
    }

    /**
     * Get the query to retrieve a computer by page.
     * @param page page information
     * @return the query
     */
    public static String getComputerPageQuery(Page page) {

        String query = GET_COMPUTER_QUERY;

        if (page.getFilter() != null && !"".equals(page.getFilter())) {
            query = query.concat(" WHERE computer.name LIKE '%").concat(page.getFilter()).concat("%' OR company.name LIKE '%")
                    .concat(page.getFilter()).concat("%'");
        }

        if (page.getOrder() != null) {
            query = query.concat(" ORDER BY computer.").concat(page.getOrder().getCol().toString())
                    .concat(" " + page.getOrder().getType().toString());
        }

        query = query.concat(" LIMIT " + page.getOffset() + " OFFSET " + page.getStartIndex());

        return query;
    }

    /**
     * Get the query to retrieve a computer by name.
     * @param name name of the computer to retrieve
     * @return the query
     */
    public static String getComputerQuery(String name) {
        String query = GET_COMPUTER_QUERY.concat(" WHERE computer.name=?");

        return query;
    }

    /**
     * Get the query to retrieve a computer by id.
     * @param id id of the computer to retrieve
     * @return the query
     */
    public static String getComputerQuery(long id) {
        String query = GET_COMPUTER_QUERY.concat(" WHERE computer.id=?");

        return query;
    }

    /**
     * Build the query to create a computer.
     * @param computer computer to create
     */
    public static PreparedStatementCreator createQuery(Computer computer) {

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement preparedStatement = con.prepareStatement(CREATE_COMPUTER_QUERY, new String[] { "id" });

                preparedStatement.setString(1, computer.getName());

                if (computer.getIntroduced() == null) {
                    preparedStatement.setObject(2, null);
                } else {
                    preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0))));
                }

                if (computer.getDiscontinued() == null) {
                    preparedStatement.setObject(3, null);
                } else {
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0))));
                }

                if (computer.getCompany() == null) {
                    preparedStatement.setObject(4, null);
                } else {
                    preparedStatement.setLong(4, computer.getCompany().getId());
                }

                return preparedStatement;
            }
        };

        return psc;
    }

    /**
     * Build the query to update a computer.
     * @param computer computer to update
     */
    public static PreparedStatementCreator updateQuery(Computer computer) {

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement preparedStatement = con.prepareStatement(UPDATE_COMPUTER_QUERY, new String[] { "id" });

                preparedStatement.setString(1, computer.getName());

                if (computer.getIntroduced() == null) {
                    preparedStatement.setObject(2, null);
                } else {
                    preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0))));
                }

                if (computer.getDiscontinued() == null) {
                    preparedStatement.setObject(3, null);
                } else {
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0))));
                }

                if (computer.getCompany() == null) {
                    preparedStatement.setObject(4, null);
                } else {
                    preparedStatement.setLong(4, computer.getCompany().getId());
                }

                preparedStatement.setLong(5, computer.getId());

                return preparedStatement;
            }
        };

        return psc;
    }

    /**
     * Get the query to delete a computer by id.
     * @param id id of the computer to delete
     * @return the query
     */
    public static String deleteComputerQuery(Long id) {
        String query = DELETE_COMPUTER_QUERY.concat(id.toString());

        return query;
    }

    /**
     * Get the query to delete a computer by company id.
     * @param id id of the company to delete
     * @return the query
     */
    public static String deleteComputerByCompanyQuery(Long id) {
        String query = DELETE_COMPUTER_COMPANY_QUERY.concat(id.toString());

        return query;
    }

    /**
     * Get the query to count computers. The page information is used to build a query with filter or/and order by if there's a need.
     * @param page page information
     * @return the query
     */
    public static String countComputerQuery(Page page) {
        String query = COUNT_COMPUTER_QUERY;

        if (page.getFilter() != null && !"".equals(page.getFilter())) {
            query = query.concat(" WHERE computer.name LIKE '%").concat(page.getFilter()).concat("%' OR company.name LIKE '%")
                            .concat(page.getFilter()).concat("%'");
        }

        return query;
    }

    /**
     * Get the query to retrieve all companies.
     * @return the query
     */
    public static String getCompanyQuery() {
        return GET_COMPANY_QUERY;
    }

    /**
     * Get the query to retrieve a company by name.
     * @return the query
     */
    public static String getCompanyQuery(String name) {
        return GET_COMPANY_QUERY.concat(" WHERE company.name=?");
    }

    /**
     * Get the query to retrieve a company by id.
     * @return the query
     */
    public static String getCompanyQuery(long id) {
        return GET_COMPANY_QUERY.concat(" WHERE company.id=?");
    }
    
    /**
     * Get the query to delete a company by id.
     * @param id id of the company to delete
     * @return the query
     */
    public static String deleteCompanyQuery(Long id) {
        String query = DELETE_COMPANY_QUERY.concat(id.toString());

        return query;
    }
}
