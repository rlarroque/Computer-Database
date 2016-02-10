package com.excilys.computerDatabase.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerDatabase.dao.interfaces.ComputerDAO;
import com.excilys.computerDatabase.db.ConnectionFactory;
import com.excilys.computerDatabase.mapping.ComputerMapper;
import com.excilys.computerDatabase.model.Computer;
import com.mysql.jdbc.PreparedStatement;

/**
 * Implementation of ComputerDAO that is used to manipulate the db.
 * @author excilys
 */
public class ComputerJDBCTemplate implements ComputerDAO {
	
	// Queries that will be used.
	private static String getComputersQuery = "SELECT * FROM `computer-database-db`.computer;";
	private static String getComputerByIdQuery = "SELECT * FROM `computer-database-db`.computer where id=?";
	private static String getComputerByNameQuery = "SELECT * FROM `computer-database-db`.computer where name=?";
	private static String createComputerQuery = "INSERT INTO `computer-database-db`.computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";
	private static String updateComputerQuery = "UPDATE `computer-database-db`.computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? where id = ?";
	private static String deleteComputerQuery = "DELETE FROM `computer-database-db`.computer where id = ?";;
	
	private Connection connection;
    private Statement statement;

	public ComputerJDBCTemplate() {
		
	    try {
	    	connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Computer> getComputers() {
		
		List <Computer> computers = new ArrayList<Computer>();
		
		try {
			ResultSet rs = statement.executeQuery(getComputersQuery);
	
			while(rs.next()){
				computers.add(ComputerMapper.map(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return computers;
	}

	@Override
	public Computer getComputer(int id) {
		
		ResultSet rs = null;
		Computer computer = null;
				
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getComputerByIdQuery);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			computer = ComputerMapper.map(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return computer;
	}

	@Override
	public Computer getComputer(String name) {
		
		ResultSet rs = null;
		Computer computer = null;
				
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(getComputerByNameQuery);
			ps.setString(1, name);
			rs = ps.executeQuery();
			
			computer = ComputerMapper.map(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return computer;
	}

	@Override
	public int createComputer(Computer c) {
		ResultSet rs = null;
		int resultKey = 0;
		
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(createComputerQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getName());
			ps.setTimestamp(2, c.getIntroduced());
			ps.setTimestamp(3, c.getDiscontinued());
			ps.setInt(4, c.getCompany_id());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				resultKey = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultKey;
	}

	@Override
	public void updateComputer(Computer c) {
		
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(updateComputerQuery);
			ps.setString(1, c.getName());
			ps.setTimestamp(2, c.getIntroduced());
			ps.setTimestamp(3, c.getDiscontinued());
			ps.setInt(4, c.getCompany_id());
			ps.setInt(5, c.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteComputer(int id) {
		
		try {
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(deleteComputerQuery);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
