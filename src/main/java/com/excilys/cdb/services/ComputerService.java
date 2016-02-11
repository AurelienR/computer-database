package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.dao.impl.ComputerDAOImpl;
import com.excilys.cdb.models.Computer;

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

	public List<Computer> findAll() throws DAOException {
		return ComputerDAOImpl.getInstance().findAll();
	}

	public List<Computer> findById(int id) throws DAOException {
		return ComputerDAOImpl.getInstance().findById(id);
	}

	public List<Computer> findByName(String name) throws DAOException {
		return ComputerDAOImpl.getInstance().findByName(name);
	}

	public int createComputer(Computer computer) throws DAOException {
		validateComputerDates(computer);
		validateComputerName(computer);
		return ComputerDAOImpl.getInstance().insertComputer(computer);
	}

	public void updateComputer(Computer computer) throws DAOException {
		ComputerDAOImpl.getInstance().updateComputer(computer);
	}

	public void deleteComputer(int id) throws DAOException {
		ComputerDAOImpl.getInstance().deleteComputer(id);
	}

	private void validateComputerDates(Computer computer) throws ServiceException {
		if (computer.getIntroduced() != null && computer.getDiscontinued() != null) {
			if (computer.getIntroduced().isAfter(computer.getDiscontinued())) {
				throw new ServiceException("Valitdation: Computer Introduced localdatetime attribute setted after discontinued localdatetime");
			}
		}
	}

	private void validateComputerName(Computer computer) throws ServiceException {
		if (computer.getName() == null || computer.getName().isEmpty()) {
			throw new ServiceException("Validation:Computer name should be not null or empty");
		}
	}
}
