package com.excilys.cdb.dao;

import java.util.List;

import com.excilys.cdb.models.Computer;

/**
 * Interface for managing Computer data with the database
 * 
 * @author Aurelien.R
 *
 */
public interface ComputerDAO {

	/**
	 * Find all computer stored in DB
	 * 
	 * @return A list of all computer found
	 * @throws DAOException
	 */
	List<Computer> findAll() throws DAOException;

	/**
	 * Find a computer stored in DB by its id
	 * 
	 * @param id
	 *            id of the computer to find
	 * @return computers related to the id
	 * @throws DAOException
	 */
	List<Computer> findById(int id) throws DAOException;

	/**
	 * Find all computer stored in DB by its name
	 * 
	 * @param name
	 *            name of the computer to find
	 * @return computers related to the name
	 * @throws DAOException
	 */
	List<Computer> findByName(String name) throws DAOException;

	/**
	 * Store passed computer in the DB
	 * 
	 * @param computer
	 *            computer to store in DB
	 * @return TODO
	 * @throws DAOException
	 */
	int insertComputer(Computer computer) throws DAOException;

	/**
	 * Update passed computer in the DB (find computer by its id)
	 * 
	 * @param computer
	 *            with the new field to change (keep id ref to update)
	 * @throws DAOException
	 */
	void updateComputer(Computer computer) throws DAOException;

	/**
	 * Delete a computer by it's id
	 * 
	 * @param id
	 *            id of the computer to delete
	 * @throws DAOException
	 */
	void deleteComputer(int id) throws DAOException;
}
