package com.excilys.cdb.services;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.ComputerDtoValidator;
import com.excilys.cdb.validators.QueryPageParameterValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.validation.ValidationException;

// TODO: Auto-generated Javadoc
/**
 * Service layer to ComputerDAO.
 *
 * @author Aurelien.R
 */
@Service
public class ComputerDtoService {

  // Services
  @Autowired
  private ComputerService computerService;
  

  // Methods
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
    List<Computer> computers = computerService.findByQuery(qp);

    // Map
    return ComputerMapper.toComputerDtoList(computers);
  }

  /**
   * Count of total searched computer.
   *
   * @return the related count
   */
  public int count(QueryPageParameter qp) {
      
    // Check
    QueryPageParameterValidator.checkSearch(qp.getSearch());
      
    // Get count
    return computerService.count(qp);
  }

  /**
   * Retrive all computer dto from db.
   *
   * @return the list of all computerdto
   */
  public List<ComputerDto> findAll() {

    // Retrieve computers
    List<Computer> computers = computerService.findAll();

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
  public List<ComputerDto> findById(int id) {

    // Validate id
    ComputerDtoValidator.checkValidId(id);

    // Retrieve Computers
    List<Computer> computers = computerService.findById(id);

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
  public List<ComputerDto> findByName(String name) {

    // Validate name
    ComputerDtoValidator.checkNameNotNull(name);
    ComputerDtoValidator.checkNameNotEmpty(name);

    // Retrieve computers
    List<Computer> computers = computerService.findByName(name);

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
  public int createComputer(ComputerDto computerDto) {

    // Validate computerDTO
    ComputerDtoValidator.validate(computerDto);

    // Map
    Computer computer = ComputerMapper.toComputer(computerDto);

    // Create computer
    return computerService.createComputer(computer);
  }

  /**
   * Update computer.
   *
   * @param computerDto the computer dto
   * @throws ValidationException the validation exception
   */
  public void updateComputer(ComputerDto computerDto) {

    // validate computerDTO
    ComputerDtoValidator.validate(computerDto);

    // Map
    Computer computer = ComputerMapper.toComputer(computerDto);

    // Update computer
    computerService.updateComputer(computer);
  }

  /**
   * Delete computer.
   *
   * @param id the id
   * @throws ValidationException the validation exception
   */
  public void deleteComputer(int id) {

    // Validate id
    ComputerDtoValidator.checkValidId(id);

    // DeleteComputer
    computerService.deleteComputer(id);
  }

}
