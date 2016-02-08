package com.excilys.computerDatabse.dao.interfaces;

import java.util.List;

import com.excilys.computerDatabase.model.Computer;

public interface ComputerDAO {
	
	public List<Computer> getComputers();
	public Computer getComputer(int id);
	public Computer getComputer(String name);
	public int createComputer(Computer c);
	public void updateComputer(Computer c);
	public void deleteComputer(int id);
}
