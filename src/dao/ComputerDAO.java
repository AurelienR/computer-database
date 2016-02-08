package dao;

import java.util.List;
import models.Computer;

public interface ComputerDAO {
	
	List<Computer> findAll() throws DAOException;
	List<Computer> findById(int id) throws DAOException;
	List<Computer> findByName(String name) throws DAOException;
	void insertComputer(Computer computer) throws DAOException;
	void updateComputer(Computer computer) throws DAOException;
	void deleteComputer(int id) throws DAOException;
}
