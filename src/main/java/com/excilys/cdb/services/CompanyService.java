package com.excilys.cdb.services;

import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.daos.TransactionManager;
import com.excilys.cdb.daos.impl.CompanyDaoImpl;
import com.excilys.cdb.daos.impl.ComputerDaoImpl;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.CompanyValidator;
import com.excilys.cdb.validators.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service layer to CompanyDAO
 * 
 * @author Aurelien.R
 *
 */
public class CompanyService {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

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
    
    logger.debug("Service: find all companies");
    
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
    
    logger.debug("Service find company by id: " + id);
    
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

    logger.debug("Service: find company by name: " + name);

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

    logger.debug("Service: create company:" + company);

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

    logger.debug("Service: Delete Company");

    // Check
    CompanyValidator.checkValidId(id);

    // Transaction
    logger.debug("Initialize transaction");
    TransactionManager tm = TransactionManager.getInstance();

    try {

      tm.startTransaction();

      logger.debug("Delete computers by company id");
      ComputerDaoImpl.getInstance().deleteByCompanyId(id);
      logger.debug("Delete company");
      CompanyDaoImpl.getInstance().deleteCompany(id);

      tm.commit();

    } catch (Exception e) {
      tm.rollback();
    } finally {
      tm.endTransaction();
    }
  }
}
