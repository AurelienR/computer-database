package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.dao.impl.CompanyDAOImpl;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validator.CompanyValidator;
import com.excilys.cdb.validator.ValidatorException;

/**
 * Service layer to CompanyDAO
 * 
 * @author Aurelien.R
 *
 */
public class CompanyService {
	// Singleton
	private static CompanyService instance;

	// Constructors
	private CompanyService() {
	};

	// Methods
	public static CompanyService getInstance(){
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}

	public List<Company> findAll() throws DAOException, ValidatorException {
		return CompanyDAOImpl.getInstance().findAll();
	}

	public List<Company> findById(int id) throws DAOException, ValidatorException{
		// Validate id
		CompanyValidator.checkValidId(id);
		
		// Retrieve companies
		return CompanyDAOImpl.getInstance().findById(id);
	}

	public List<Company> findByName(String name) throws DAOException, ValidatorException{
		// Validate Name
		CompanyValidator.checkNameNotNull(name);
		CompanyValidator.checkNameNotEmpty(name);
		
		// Retrieve companies
		return CompanyDAOImpl.getInstance().findByName(name);
	}

	public int createCompany(Company company) throws DAOException, ValidatorException{		
		// Validate Company
		CompanyValidator.checkNameNotEmpty(company.getName());
		CompanyValidator.checkNameNotNull(company.getName());
		
		// Insert Company
		return CompanyDAOImpl.getInstance().insertCompany(company);
	}
}
