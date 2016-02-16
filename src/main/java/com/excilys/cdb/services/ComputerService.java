package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.daos.DAOException;
import com.excilys.cdb.daos.impl.ComputerDAOImpl;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.validators.CompanyValidator;
import com.excilys.cdb.validators.ComputerValidator;
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
	public static ComputerService getInstance() {
		if (instance == null) {
			instance = new ComputerService();
		}
		return instance;
	}

	public List<Computer> findAll() throws DAOException, ValidatorException {
		return ComputerDAOImpl.getInstance().findAll();
	}

	public List<Computer> findById(int id) throws DAOException, ValidatorException{
		// Validate id
		ComputerValidator.checkValidId(id);
		
		// Retrieve computers
		return ComputerDAOImpl.getInstance().findById(id);
	}

	public List<Computer> findByName(String name) throws DAOException, ValidatorException{
		// Validate name
		ComputerValidator.checkNameNotNull(name);
		ComputerValidator.checkNameNotEmpty(name);
		
		// Retrieve computers
		return ComputerDAOImpl.getInstance().findByName(name);
	}

	public int createComputer(Computer computer) throws DAOException, ValidatorException{
		
		// Validate Computer
		ComputerValidator.checkNameNotNull(computer.getName());
		ComputerValidator.checkNameNotEmpty(computer.getName());
		ComputerValidator.checkDatesConsistence(computer);
		// Check related company
		if(computer.getCompany() != null){
			CompanyValidator.checkValidId(computer.getCompany().getId());
		}
		
		// Create computers
		return ComputerDAOImpl.getInstance().insertComputer(computer);
	}

	public void updateComputer(Computer computer) throws DAOException, ValidatorException{
		
		// Validate computer
		ComputerValidator.checkValidId(computer.getId());
		ComputerValidator.checkNameNotNull(computer.getName());
		ComputerValidator.checkNameNotEmpty(computer.getName());
		// Check related company
		if(computer.getCompany() != null){
			CompanyValidator.checkValidId(computer.getCompany().getId());
		}
		
		// Update computer
		ComputerDAOImpl.getInstance().updateComputer(computer);
	}

	public void deleteComputer(int id) throws DAOException, ValidatorException{
		
		// Validate computer
		ComputerValidator.checkValidId(id);
		
		// Delete computer
		ComputerDAOImpl.getInstance().deleteComputer(id);
	}
}
