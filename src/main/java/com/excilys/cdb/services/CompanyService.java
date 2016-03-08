package com.excilys.cdb.services;

import com.excilys.cdb.daos.CompanyDao;
import com.excilys.cdb.daos.ComputerDao;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.ValidatorException;
import com.excilys.cdb.validators.utils.CompanyValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer to CompanyDAO
 * 
 * @author Aurelien.R
 *
 */
@Service
public class CompanyService {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

  // Dao
  @Autowired
  private CompanyDao companyDao;
  @Autowired
  private ComputerDao computerDao;

  // Methods
  /**
   * All companies stored in DB.
   * 
   * @return List of all Companies
   * @throws DaoException issues with DB
   * @throws ValidatorException validation of data
   */
  public List<Company> findAll() {

    LOGGER.debug("Service: find all companies");

    return companyDao.findAll();
  }

  /**
   * Find a Company by its id.
   * 
   * @param id id of the Company to find
   * @return List of matching Companies
   * @throws DaoException issues with DB
   * @throws ValidatorException issue with validation of Data
   */
  public List<Company> findById(long id) {

    LOGGER.debug("Service find company by id: " + id);

    // Validate id
    CompanyValidator.checkValidId(id);

    // Retrieve companies
    return companyDao.findById(id);
  }

  /**
   * Find a companies by name.
   * 
   * @param name name of companies to find
   * @return return a list of matching Company
   * @throws DaoException issue with DB
   * @throws ValidatorException issue with data
   */
  public List<Company> findByName(String name) {

    LOGGER.debug("Service: find company by name: " + name);

    // Validate Name
    CompanyValidator.checkNameNotNull(name);
    CompanyValidator.checkNameNotEmpty(name);

    // Retrieve companies
    return companyDao.findByName(name);
  }

  /**
   * Add the passed company to de DB.
   * 
   * @param company company to add to DB
   * @return id of the company created
   * @throws DaoException issues with DB
   * @throws ValidatorException issues with data
   */
  public long createCompany(Company company) {

    LOGGER.debug("Service: create company:" + company);

    // Validate Company
    CompanyValidator.validate(company);

    // Insert Company
    return companyDao.insertCompany(company);
  }

  /**
   * Delete the Company by its id.
   * 
   * @param id id of the Company to find
   * @throws DaoException issues with DB
   * @throws ValidatorException issues with data
   */
  @Transactional
  public void deleteCompany(long id) {

    LOGGER.debug("Service: Delete Company");

    // Check
    CompanyValidator.checkValidId(id);

    // Transaction
    LOGGER.debug("Initialize transaction");
    LOGGER.debug("Delete computers by company id");
    computerDao.deleteByCompanyId(id);
    LOGGER.debug("Delete company");
    companyDao.deleteCompany(id);

  }
}
