package com.excilys.computer_database.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computer_database.persistence.dao.ComputerDAO;
import com.excilys.computer_database.persistence.dao.utils.DAOUtils;
import com.excilys.computer_database.persistence.dao.utils.QueryBuilder;
import com.excilys.computer_database.persistence.model.Computer;
import com.excilys.computer_database.persistence.model.Page;
import com.excilys.computer_database.persistence.model.mapper.ComputerMapper;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author rlarroque
 */
@Repository
public class ComputerDAOImpl implements ComputerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAOImpl.class.getName());

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Computer> getAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        List<Computer> computers = new ArrayList<Computer>();

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.getComputersQuery());
            resSet = preparedStatement.executeQuery();

            while (resSet.next()) {
                computers.add(ComputerMapper.toComputer(resSet));
            }

        } catch (SQLException e) {
            LOGGER.error("Cannot get all computers!!!", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }

        return computers;
    }

    @Override
    public List<Computer> getPage(Page page) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        List<Computer> computers = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.getComputerPageQuery(page));
            resSet = preparedStatement.executeQuery();

            while (resSet.next()) {
                computers.add(ComputerMapper.toComputer(resSet));
            }

            LOGGER.info("Page of computers retrieved.");

        } catch (SQLException e) {
            LOGGER.error("Cannot get the page of computers!!!", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }

        return computers;
    }

    @Override
    public Computer get(long id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        Computer computer = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.getComputerQuery(id));
            resSet = preparedStatement.executeQuery();

            if (resSet.next()) {
                computer = ComputerMapper.toComputer(resSet);
            }

            LOGGER.info("Computer with id " + id + " retrieved.");

        } catch (SQLException e) {
            LOGGER.error("Cannot get the computer with the id " + id + "!!! ", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }

        return computer;
    }

    @Override
    public Computer get(String name) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        Computer computer = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.getComputerQuery(name));
            resSet = preparedStatement.executeQuery();

            if (resSet.next()) {
                computer = ComputerMapper.toComputer(resSet);
            }

            LOGGER.info("Computer with name " + name + " retrieved.");

        } catch (SQLException e) {
            LOGGER.error("Cannot get the computer with the name " + name + "!!!", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }

        return computer;
    }

    @Override
    public long create(Computer computer) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        long resultKey = 0;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.createQuery(), Statement.RETURN_GENERATED_KEYS);
            QueryBuilder.buildCreateQuery(computer, preparedStatement);

            preparedStatement.executeUpdate();
            resSet = preparedStatement.getGeneratedKeys();

            if (resSet.next()) {
                resultKey = resSet.getInt(1);
            }

            LOGGER.info("Computer with id " + computer.getId() + " created.");

        } catch (SQLException e) {
            LOGGER.error("Cannot create computer!!!", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }

        return resultKey;
    }

    @Override
    public void update(Computer computer) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.updateQuery());
            QueryBuilder.buildUpdateQuery(computer, preparedStatement);

            preparedStatement.executeUpdate();

            LOGGER.info("Computer with id " + computer.getId() + " updated.");

        } catch (SQLException e) {
            LOGGER.error("Cannot update computer!!!", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }
    }

    @Override
    public void delete(long id) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.deleteComputerQuery(id));
            preparedStatement.executeUpdate();

            LOGGER.info("Computer with id " + id + " deleted.");

        } catch (SQLException e) {
            LOGGER.error("Cannot delete computer with id: " + id + "!!!", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }
    }

    @Override
    public void deleteByCompany(long id) throws SQLException {
        Connection connection = DAOUtils.initConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        try {
            preparedStatement = connection.prepareStatement(QueryBuilder.deleteComputerByCompanyQuery(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Cannot delete computers with company id: " + id + "!!!", e);
        } finally {
            // The connection will be closed at the end of the transaction
            DAOUtils.closeConnection(null, preparedStatement, resSet);
        }
    }

    @Override
    public int count(Page page) {

        int computerNumber = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(QueryBuilder.countComputerQuery(page));
            resSet = preparedStatement.executeQuery();

            if (resSet.next()) {
                computerNumber = resSet.getInt(1);
            }

        } catch (SQLException e) {

            LOGGER.error("Cannot count computers!!!", e);
        } finally {
            DAOUtils.closeConnection(connection, preparedStatement, resSet);
        }

        return computerNumber;
    }
}
