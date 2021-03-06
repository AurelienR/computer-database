package com.excilys.cdb.services;

import com.excilys.cdb.daos.repositories.ComputerRepository;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.ValidatorException;
import com.excilys.cdb.validators.utils.ComputerValidator;
import com.excilys.cdb.validators.utils.QueryPageParameterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Service layer to ComputerDAO
 *
 * @author Aurelien.R
 *
 */
@Service
public class ComputerService {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private ComputerRepository computerRepository;

  // Methods
  /**
   * Find Computers by query parameters criterias.
   *
   * @param qp query parameters to based search on
   * @return Ordering list of matching Computers
   * @throws DaoException issues with DB
   * @throws ValidatorException issues with data
   */
  public Page<Computer> findByQuery(QueryPageParameter qp) {

    LOGGER.debug("Service: find commputer by queryPageParameter, qp: {}", qp);

    // Validate queryParameter
    QueryPageParameterValidator.validate(qp);

    // Return computers
    return computerRepository.findByNameOrCompanyName(qp.getSearch(),
        qp.getPageable());
  }

  /**
   * Count all computers in db.
   *
   * @return total number of computers in DB
   * @throws DaoException issue with db
   */
  public long count(QueryPageParameter qp) {

    LOGGER.debug("Service: count all computers");

    // Check
    QueryPageParameterValidator.checkSearch(qp.getSearch());

    // Get count
    return computerRepository.countByNameOrCompanyName(qp.getSearch());
  }

  /**
   * Find the full list of computers stored in DB.
   *
   * @return list of all computers
   * @throws DaoException issues with db
   */
  public List<Computer> findAll() {

    LOGGER.debug("Service: find all computers");
    return computerRepository.findAll();
  }

  /**
   * Find computers by their id.
   *
   * @param id id of the computer to search for
   * @return List of matching computers
   * @throws DaoException issues with DB
   * @throws ValidatorException issues with data
   */
  public Computer findById(long id) {

    LOGGER.debug("Service: find computer by id: {}", id);

    // Validate id
    ComputerValidator.checkValidId(id);

    // Retrieve computers
    return computerRepository.findOne(id);
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

    LOGGER.debug("Service: find computer by name:{}", name);

    // Validate name
    ComputerValidator.checkNameNotNull(name);
    ComputerValidator.checkNameNotEmpty(name);

    // Retrieve computers
    return computerRepository.findByName(name);
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

    LOGGER.debug("Service: create computer: {}", computer);

    // Validate computer
    ComputerValidator.validate(computer);

    // Create computers
    return computerRepository.save(computer).getId();
  }

  /**
   * Update passed computer in db.
   *
   * @param computer to update (based on its id)
   * @throws DaoException issues with db
   * @throws ValidatorException issues with data
   */
  public void updateComputer(Computer computer) {

    LOGGER.debug("Service: update computer: {}", computer);

    // Validate computer
    ComputerValidator.validate(computer);

    // Find computer by id
    Computer updateComputer = computerRepository.findOne(computer.getId());

    // Update computer
    updateComputer.setName(computer.getName());
    updateComputer.setIntroduced(computer.getIntroduced());
    updateComputer.setDiscontinued(computer.getDiscontinued());
    updateComputer.setCompany(computer.getCompany());

    // Update
    computerRepository.saveAndFlush(updateComputer);

  }

  /**
   * Delete a computer by its id.
   *
   * @param id id of the computer to delete
   * @throws DaoException issues with db
   * @throws ValidatorException issues with data
   */
  public void deleteComputer(long id) {

    LOGGER.debug("Service: delete computer by id: {}", id);

    // Validate computer
    ComputerValidator.checkValidId(id);

    // Delete computer
    computerRepository.delete(id);
  }
}
