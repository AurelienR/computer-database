package com.excilys.cdb.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.daos.ConnectionCloser;
import com.excilys.cdb.daos.ConnectionFactory;
import com.excilys.cdb.daos.DAOException;
import com.excilys.cdb.daos.impl.CompanyDAOImpl;
import com.excilys.cdb.daos.impl.ComputerDAOImpl;
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
	/**
	 * Singleton access method
	 * @return unique instance of CompanyService
	 */
	public static CompanyService getInstance() {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}

	/**
	 * All companies stored in DB
	 * @return List of all Companies
	 * @throws DAOException issues with DB
	 * @throws ValidatorException validation of data
	 */
	public List<Company> findAll() throws DAOException, ValidatorException {
		return CompanyDAOImpl.getInstance().findAll();
	}

	/**
	 * Find a Company by its id
	 * @param id id of the Company to find
	 * @return List of matching Companies
	 * @throws DAOException issues with DB
	 * @throws ValidatorException issue with validation of Data
	 */
	public List<Company> findById(int id) throws DAOException, ValidatorException {
		// Validate id
		CompanyValidator.checkValidId(id);
		
		// Retrieve companies
		return CompanyDAOImpl.getInstance().findById(id);
	}

	/**
	 * Find a companies by name
	 * @param name name of companies to find
	 * @return return a list of matching Company
	 * @throws DAOException issue with DB
	 * @throws ValidatorException issue with data
	 */
	public List<Company> findByName(String name) throws DAOException, ValidatorException {
		// Validate Name
		CompanyValidator.checkNameNotNull(name);
		CompanyValidator.checkNameNotEmpty(name);
		
		// Retrieve companies
		return CompanyDAOImpl.getInstance().findByName(name);
	}

	/**
	 * Add the passed company to de DB
	 * @param company company to add to DB
	 * @return id of the company created
	 * @throws DAOException issues with DB
	 * @throws ValidatorException issues with data
	 */
	public int createCompany(Company company) throws DAOException, ValidatorException {		
		// Validate Company
		CompanyValidator.validate(company);
		
		// Insert Company
		return CompanyDAOImpl.getInstance().insertCompany(company);
	}
	
	/**
	 * Delete the Company by its 
	 * @param id id of the Company to find
	 * @throws DAOException issues with DB
	 * @throws ValidatorException issues with data
	 */
	public void deleteCompany(int id) throws DAOException, ValidatorException {
		
		CompanyValidator.checkValidId(id);
		
		Connection con = null;
		
		try{
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			ComputerDAOImpl.getInstance().deleteByCompanyId(con, id);
			CompanyDAOImpl.getInstance().deleteCompany(con,id);	
			
			con.commit();
			
		} catch (Exception e) {			
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on delete company method, id:"+id,e1);	
			}					
		} finally{
			ConnectionCloser.silentClose(con);
		}		
	}
}
