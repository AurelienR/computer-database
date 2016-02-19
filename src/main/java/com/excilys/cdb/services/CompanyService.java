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
	public static CompanyService getInstance() {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}

	public List<Company> findAll() throws DAOException, ValidatorException {
		return CompanyDAOImpl.getInstance().findAll();
	}

	public List<Company> findById(int id) throws DAOException, ValidatorException {
		// Validate id
		CompanyValidator.checkValidId(id);
		
		// Retrieve companies
		return CompanyDAOImpl.getInstance().findById(id);
	}

	public List<Company> findByName(String name) throws DAOException, ValidatorException {
		// Validate Name
		CompanyValidator.checkNameNotNull(name);
		CompanyValidator.checkNameNotEmpty(name);
		
		// Retrieve companies
		return CompanyDAOImpl.getInstance().findByName(name);
	}

	public int createCompany(Company company) throws DAOException, ValidatorException {		
		// Validate Company
		CompanyValidator.validate(company);
		
		// Insert Company
		return CompanyDAOImpl.getInstance().insertCompany(company);
	}
	
	public void deleteCompany(int id) throws DAOException, ValidatorException {
		
		CompanyValidator.checkValidId(id);
		Connection con = null;
		
		try{
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			ComputerDAOImpl.getInstance().resetCompanyId(con, id);
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
