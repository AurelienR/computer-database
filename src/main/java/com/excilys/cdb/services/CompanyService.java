package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.daos.DAOException;
import com.excilys.cdb.daos.impl.CompanyDAOImpl;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.CompanyValidator;
import com.excilys.cdb.validators.ValidatorException;

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
		CompanyValidator.validate(company);
		
		// Insert Company
		return CompanyDAOImpl.getInstance().insertCompany(company);
	}
}
