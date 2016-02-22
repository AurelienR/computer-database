package com.excilys.cdb.services;

import com.excilys.cdb.daos.ConnectionCloser;
import com.excilys.cdb.daos.ConnectionFactory;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.daos.impl.CompanyDaoImpl;
import com.excilys.cdb.daos.impl.ComputerDaoImpl;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.CompanyValidator;
import com.excilys.cdb.validators.ValidatorException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
  }

  // Methods
  /**
   * Singleton access method.
   * 
   * @return unique instance of CompanyService
   */
  public static CompanyService getInstance() {
    if (instance == null) {
      instance = new CompanyService();
    }
    return instance;
  }

  /**
   * All companies stored in DB.
   * 
   * @return List of all Companies
   * @throws DaoException
   *           issues with DB
   * @throws ValidatorException
   *           validation of data
   */
  public List<Company> findAll() throws DaoException, ValidatorException {
    return CompanyDaoImpl.getInstance().findAll();
  }

  /**
   * Find a Company by its id.
   * 
   * @param id
   *          id of the Company to find
   * @return List of matching Companies
   * @throws DaoException
   *           issues with DB
   * @throws ValidatorException
   *           issue with validation of Data
   */
  public List<Company> findById(int id) throws DaoException, ValidatorException {
    // Validate id
    CompanyValidator.checkValidId(id);

    // Retrieve companies
    return CompanyDaoImpl.getInstance().findById(id);
  }

  /**
   * Find a companies by name.
   * 
   * @param name
   *          name of companies to find
   * @return return a list of matching Company
   * @throws DaoException
   *           issue with DB
   * @throws ValidatorException
   *           issue with data
   */
  public List<Company> findByName(String name) throws DaoException, ValidatorException {
    // Validate Name
    CompanyValidator.checkNameNotNull(name);
    CompanyValidator.checkNameNotEmpty(name);

    // Retrieve companies
    return CompanyDaoImpl.getInstance().findByName(name);
  }

  /**
   * Add the passed company to de DB.
   * 
   * @param company
   *          company to add to DB
   * @return id of the company created
   * @throws DaoException
   *           issues with DB
   * @throws ValidatorException
   *           issues with data
   */
  public int createCompany(Company company) throws DaoException, ValidatorException {
    // Validate Company
    CompanyValidator.validate(company);

    // Insert Company
    return CompanyDaoImpl.getInstance().insertCompany(company);
  }

  /**
   * Delete the Company by its id.
   * 
   * @param id
   *          id of the Company to find
   * @throws DaoException
   *           issues with DB
   * @throws ValidatorException
   *           issues with data
   */
  public void deleteCompany(int id) throws DaoException, ValidatorException {

    CompanyValidator.checkValidId(id);

    Connection con = null;

    try {
      con = ConnectionFactory.getInstance().getConnection();
      con.setAutoCommit(false);
      ComputerDaoImpl.getInstance().deleteByCompanyId(con, id);
      CompanyDaoImpl.getInstance().deleteCompany(con, id);

      con.commit();

    } catch (Exception e) {
      try {
        con.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
        throw new DaoException("Failed to Rollback on delete company method, id:" + id, e1);
      }
    } finally {
      ConnectionCloser.silentClose(con);
    }
  }
}
