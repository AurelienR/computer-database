package com.excilys.cdb.daos;

import java.sql.Connection;
import java.util.List;

import com.excilys.cdb.models.Company;

public interface CompanyDAO {

	/**
	 * Find all company stored in DB
	 * 
	 * @return A list of all company found
	 * @throws DAOException
	 */
	List<Company> findAll() throws DAOException;
	
	
	/**
	 * @param startRow
	 * @param size
	 * @return
	 * @throws DAOException
	 */
	public List<Company> findRange(int startRow, int size) throws DAOException;

	/**
	 * Find a company stored in DB by its id
	 * 
	 * @param id
	 *            id of the company to find
	 * @return companies related to the id
	 * @throws DAOException
	 */
	List<Company> findById(int id) throws DAOException;

	/**
	 * Find all company stored in DB by its name
	 * 
	 * @param name
	 *            name of the company to find
	 * @return companies related to the name
	 * @throws DAOException
	 */
	List<Company> findByName(String name) throws DAOException;

	/**
	 * Store passed company in the DB
	 * 
	 * @param company
	 *            company to store in DB
	 * @return return id of the company inserted
	 * @throws DAOException
	 */
	int insertCompany(Company company) throws DAOException;
	
	/**
	 * Delete a company by its id
	 * @param id id of the company to delete
	 * @throws DAOException
	 */
	void deleteCompany(Connection con,int id) throws DAOException;

}
