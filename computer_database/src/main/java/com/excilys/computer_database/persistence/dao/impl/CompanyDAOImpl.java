package com.excilys.computer_database.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.persistence.dao.CompanyDAO;
import com.excilys.computer_database.persistence.dao.utils.DAOUtils;
import com.excilys.computer_database.persistence.db.ConnectionFactory;
import com.excilys.computer_database.persistence.model.Company;
import com.excilys.computer_database.persistence.model.mapper.CompanyMapper;

/**
 * Implementation of CompanyDAO that is used to manipulate the db.
 * @author rlarroque
 */
public class CompanyDAOImpl implements CompanyDAO {

    private static CompanyDAOImpl instance;

    // Query that will be used.
    private static final String GET_COMPANIES_QUERY = "SELECT * FROM company;";
    private static final String DELETE_COMPANY_QUERY = "DELETE FROM company where id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDAOImpl.class.getName());

    /**
     * Return a singleton of company DAO.
     * @return the instance
     */
    public static CompanyDAOImpl getInstance() {
        if (instance == null) {
            instance = new CompanyDAOImpl();
        }

        return instance;
    }

    @Override
    public List<Company> getAll() {

        Connection connection = DAOUtils.initConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        List<Company> companies = new ArrayList<Company>();

        try {
            preparedStatement = connection.prepareStatement(GET_COMPANIES_QUERY);
            resSet = preparedStatement.executeQuery();

            while (resSet.next()) {
                companies.add(CompanyMapper.toCompany(resSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Cannot get all companies!!! " + e.getMessage());
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }

        return companies;
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ConnectionFactory.getConnectionNoCommit();
        PreparedStatement preparedStatement = null;

        preparedStatement = connection.prepareStatement(DELETE_COMPANY_QUERY);
        preparedStatement.execute("SET FOREIGN_KEY_CHECKS=0");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.execute("SET FOREIGN_KEY_CHECKS=1");
    }
}
