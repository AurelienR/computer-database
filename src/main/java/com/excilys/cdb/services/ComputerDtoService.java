package com.excilys.cdb.services;

import com.excilys.cdb.daos.impl.ComputerDaoImpl;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.ComputerDtoValidator;
import com.excilys.cdb.validators.QueryPageParameterValidator;

import java.util.List;

import javax.validation.ValidationException;

// TODO: Auto-generated Javadoc
/**
 * Service layer to ComputerDAO.
 *
 * @author Aurelien.R
 */
public class ComputerDtoService {

  /** The instance. */
  // Singleton
  private static ComputerDtoService instance;

  /**
   * Instantiates a new computer dto service.
   */
  // Constructors
  private ComputerDtoService() {
  }

  // Methods
  /**
   * Singleton method access.
   *
   * @return unique instance of ComputerDTOService
   */
  public static ComputerDtoService getInstance() {
    if (instance == null) {
      instance = new ComputerDtoService();
    }
    return instance;
  }

  /**
   * Find computers based on QueryPageParameter criterias.
   *
   * @param qp          query parameter to search with
   * @return List of computers matching
   */
  public List<ComputerDto> findByQuery(QueryPageParameter qp) {

    // Validate queryParameter
    QueryPageParameterValidator.validate(qp);

    // Retrieve computer
    List<Computer> computers = ComputerService.getInstance().findByQuery(qp);

    // Map
    return ComputerMapper.toComputerDtoList(computers);
  }

  /**
   * Count.
   *
   * @return the int
   */
  public int count() {
    return ComputerService.getInstance().count();
  }

  /**
   * Retrive all computer dto from db.
   *
   * @return the list of all computerdto
   */
  public List<ComputerDto> findAll() {

    // Retrieve computers
    List<Computer> computers = ComputerService.getInstance().findAll();

    // Map
    return ComputerMapper.toComputerDtoList(computers);
  }

  /**
   * Find by id.
   *
   * @param id the id of the ComputerDto
   * @return the list of related ComputerDto
   * @throws ValidationException issue with date
   */
  public List<ComputerDto> findById(int id) throws ValidationException {

    // Validate id
    ComputerDtoValidator.checkValidId(id);

    // Retrieve Computers
    List<Computer> computers = ComputerService.getInstance().findById(id);

    // Map
    return ComputerMapper.toComputerDtoList(computers);
  }

  
  /**
   * Find computerdto by its name.
   *
   * @param name the name
   * @return the list
   * @throws ValidationException the validation exception
   */
  public List<ComputerDto> findByName(String name) throws ValidationException {

    // Validate name
    ComputerDtoValidator.checkNameNotNull(name);
    ComputerDtoValidator.checkNameNotEmpty(name);

    // Retrieve computers
    List<Computer> computers = ComputerService.getInstance().findByName(name);

    // Map
    return ComputerMapper.toComputerDtoList(computers);
  }

  /**
   * Creates the computer.
   *
   * @param computerDto the computer dto
   * @return the int
   * @throws ValidationException the validation exception
   */
  public int createComputer(ComputerDto computerDto) throws ValidationException {

    // Validate computerDTO
    ComputerDtoValidator.validate(computerDto);

    // Map
    Computer computer = ComputerMapper.toComputer(computerDto);

    // Create computer
    return ComputerService.getInstance().createComputer(computer);
  }

  /**
   * Update computer.
   *
   * @param computerDto the computer dto
   * @throws ValidationException the validation exception
   */
  public void updateComputer(ComputerDto computerDto) throws ValidationException {

    // validate computerDTO
    ComputerDtoValidator.validate(computerDto);

    // Map
    Computer computer = ComputerMapper.toComputer(computerDto);

    // Update computer
    ComputerDaoImpl.getInstance().updateComputer(computer);
  }

  /**
   * Delete computer.
   *
   * @param id the id
   * @throws ValidationException the validation exception
   */
  public void deleteComputer(int id) throws ValidationException {

    // Validate id
    ComputerDtoValidator.checkValidId(id);

    // DeleteComputer
    ComputerDaoImpl.getInstance().deleteComputer(id);
  }

}
