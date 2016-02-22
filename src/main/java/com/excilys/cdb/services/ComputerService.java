package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.daos.DAOException;
import com.excilys.cdb.daos.impl.ComputerDAOImpl;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.ComputerValidator;
import com.excilys.cdb.validators.QueryPageParameterValidator;
import com.excilys.cdb.validators.ValidatorException;

/**
 * Service layer to ComputerDAO
 * 
 * @author Aurelien.R
 *
 */
public class ComputerService {

	// Singleton
	private static ComputerService instance;

	// Constructors
	private ComputerService() {
	};

	// Methods
	/**
	 * Singleton method access
	 * @return unique instance of ComputerService
	 */
	public static ComputerService getInstance() {
		if (instance == null) {
			instance = new ComputerService();
		}
		return instance;
	}
	
	/**
	 * Find Computers by query parameters criterias
	 * @param qp query parameters to based search on
	 * @return Ordering list of matching Computers 
	 * @throws DAOException issues with DB
	 * @throws ValidatorException issues with data
	 */
	public List<Computer> findByQuery(QueryPageParameter qp) throws DAOException, ValidatorException {
		
		// Validate queryParameter
		QueryPageParameterValidator.validate(qp);
		
		// Return computers
		return ComputerDAOImpl.getInstance().findByQuery(qp);
	}
	
	/**
	 * Find computer withing a range in DB
	 * @param startRow starting row of the range
	 * @param size size of the range
	 * @return related List of computers
	 */
	public List<Computer> findRange(int startRow, int size){
		
		// Validate parameters
		ComputerValidator.checkStartingRow(startRow);
		ComputerValidator.checkSize(size);		
		
		// Retrieve computers
		return ComputerDAOImpl.getInstance().findRange(startRow, size);
	}
	
	/**
	 * Count all computers in db
	 * @return total number of computers in DB
	 * @throws DAOException issue with db
	 */
	public int count() throws DAOException{
		return ComputerDAOImpl.getInstance().count();
	}

	/**
	 * Find the full list of computers stored in DB
	 * @return list of all computers
	 * @throws DAOException issues with db
	 */
	public List<Computer> findAll() throws DAOException {
		return ComputerDAOImpl.getInstance().findAll();
	}

	/**
	 * Find computers by their id
	 * @param id id of the computer to search for
	 * @return List of matching computers
	 * @throws DAOException issues with DB
	 * @throws ValidatorException issues with data
	 */
	public List<Computer> findById(int id) throws DAOException, ValidatorException{
		// Validate id
		ComputerValidator.checkValidId(id);
		
		// Retrieve computers
		return ComputerDAOImpl.getInstance().findById(id);
	}

	/**
	 * Find computers by their name
	 * @param name name of the computer to search for
	 * @return List of matching computers
	 * @throws DAOException issues with db
	 * @throws ValidatorException issues with data
	 */
	public List<Computer> findByName(String name) throws DAOException, ValidatorException{
		// Validate name
		ComputerValidator.checkNameNotNull(name);
		ComputerValidator.checkNameNotEmpty(name);
		
		// Retrieve computers
		return ComputerDAOImpl.getInstance().findByName(name);
	}

	/**
	 * Add passed computer to DB
	 * @param computer computer to add to db
	 * @return id of the computer added
	 * @throws DAOException issues with DB
	 * @throws ValidatorException issues with data
	 */
	public int createComputer(Computer computer) throws DAOException, ValidatorException{
		
		// Validate computer
		ComputerValidator.validate(computer);
		
		// Create computers
		return ComputerDAOImpl.getInstance().insertComputer(computer);
	}

	/**
	 * Update passed computer in db
	 * @param computer to update (based on its id)
	 * @throws DAOException issues with db
	 * @throws ValidatorException issues with data
	 */
	public void updateComputer(Computer computer) throws DAOException, ValidatorException{
		
		// Validate computer
		ComputerValidator.validate(computer);
		
		// Update computer
		ComputerDAOImpl.getInstance().updateComputer(computer);
	}

	/**
	 * Delete a computer by its id
	 * @param id id of the computer to delete
	 * @throws DAOException issues with db
	 * @throws ValidatorException issues with data
	 */
	public void deleteComputer(int id) throws DAOException, ValidatorException{
		
		// Validate computer
		ComputerValidator.checkValidId(id);
		
		// Delete computer
		ComputerDAOImpl.getInstance().deleteComputer(id);
	}
}
