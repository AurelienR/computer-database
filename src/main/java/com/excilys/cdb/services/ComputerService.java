package com.excilys.cdb.services;

import com.excilys.cdb.daos.ComputerDao;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.ValidatorException;
import com.excilys.cdb.validators.utils.ComputerValidator;
import com.excilys.cdb.validators.utils.QueryPageParameterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer to ComputerDAO
 * 
 * @author Aurelien.R
 *
 */
@Service
public class ComputerService {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(ComputerService.class);

  // Dao
  @Autowired
  ComputerDao computerDao;

  // Methods
  /**
   * Find Computers by query parameters criterias.
   * 
   * @param qp query parameters to based search on
   * @return Ordering list of matching Computers
   * @throws DaoException issues with DB
   * @throws ValidatorException issues with data
   */
  public List<Computer> findByQuery(QueryPageParameter qp) {

    logger.debug("Service: find commputer by queryPageParameter, qp:" + qp);

    // Validate queryParameter
    QueryPageParameterValidator.validate(qp);

    // Return computers
    return computerDao.findByQuery(qp);
  }

  /**
   * Count all computers in db.
   * 
   * @return total number of computers in DB
   * @throws DaoException issue with db
   */
  public long count(QueryPageParameter qp) {

    logger.debug("Service: count all computers");

    // Check
    QueryPageParameterValidator.checkSearch(qp.getSearch());

    // Get count
    return computerDao.count(qp);
  }

  /**
   * Find the full list of computers stored in DB.
   * 
   * @return list of all computers
   * @throws DaoException issues with db
   */
  public List<Computer> findAll() {

    logger.debug("Service: find all computers");

    return computerDao.findAll();
  }

  /**
   * Find computers by their id.
   * 
   * @param id id of the computer to search for
   * @return List of matching computers
   * @throws DaoException issues with DB
   * @throws ValidatorException issues with data
   */
  public List<Computer> findById(long id) {

    logger.debug("Service: find computer by id: " + id);

    // Validate id
    ComputerValidator.checkValidId(id);

    // Retrieve computers
    return computerDao.findById(id);
  }

  /**
   * Find computers by their name.
   * 
   * @param name name of the computer to search for
   * @return List of matching computers
   * @throws DaoException issues with db
   * @throws ValidatorException issues with data
   */
  public List<Computer> findByName(String name) {

    logger.debug("Service: find computer by name:" + name);

    // Validate name
    ComputerValidator.checkNameNotNull(name);
    ComputerValidator.checkNameNotEmpty(name);

    // Retrieve computers
    return computerDao.findByName(name);
  }

  /**
   * Add passed computer to DB.
   * 
   * @param computer computer to add to db
   * @return id of the computer added
   * @throws DaoException issues with DB
   * @throws ValidatorException issues with data
   */
  public long createComputer(Computer computer) {

    logger.debug("Service: create computer:" + computer);

    // Validate computer
    ComputerValidator.validate(computer);

    // Create computers
    return computerDao.insertComputer(computer);
  }

  /**
   * Update passed computer in db.
   * 
   * @param computer to update (based on its id)
   * @throws DaoException issues with db
   * @throws ValidatorException issues with data
   */
  public void updateComputer(Computer computer) {

    logger.debug("Service: update computer:" + computer);

    // Validate computer
    ComputerValidator.validate(computer);

    // Update computer
    computerDao.updateComputer(computer);
  }

  /**
   * Delete a computer by its id.
   * 
   * @param id id of the computer to delete
   * @throws DaoException issues with db
   * @throws ValidatorException issues with data
   */
  public void deleteComputer(long id) {

    logger.debug("Service: delete computer by id:" + id);

    // Validate computer
    ComputerValidator.checkValidId(id);

    // Delete computer
    computerDao.deleteComputer(id);
  }
}
