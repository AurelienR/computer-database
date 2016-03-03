package com.excilys.cdb.services;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.CompanyDtoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Service layer to CompanyDAO.
 *
 * @author Aurelien.R
 */
@Service
public class CompanyDtoService {
  
  /** The instance. */
  // Services
  @Autowired
  private CompanyService companyService;

  /**
   * Instantiates a new company dto service.
   */
  // Constructors
  private CompanyDtoService() {
  }

  // Methods
  /**
   * Find all companies in DB.
   *
   * @return list of all CompanyDTOs objects
   */
  public List<CompanyDto> findAll() {

    // Retrieve companyDTOs
    List<Company> companies = companyService.findAll();
    return CompanyMapper.toCompanyDtoList(companies);
  }

  /**
   * Find a company by its id.
   *
   * @param id          id of the company to search
   * @return List of matching CompanyDTOs
   */
  public List<CompanyDto> findById(int id) {

    // Validate id
    CompanyDtoValidator.checkValidId(id);

    // Retrieve Companies
    List<Company> companies = companyService.findById(id);

    // Map
    return CompanyMapper.toCompanyDtoList(companies);
  }

  /**
   * Find companies by name.
   *
   * @param name          name of the company to search
   * @return List of matching CompanyDTos
   */
  public List<CompanyDto> findByName(String name) {

    // Validate name
    CompanyDtoValidator.checkNameNotNull(name);
    CompanyDtoValidator.checkNameNotEmpty(name);

    // Retrieve Companies
    List<Company> companies = companyService.findByName(name);

    // Map
    return CompanyMapper.toCompanyDtoList(companies);
  }

  /**
   * Add given CompanyDTO to db.
   *
   * @param companyDto          company to add
   * @return id of the created company
   */
  public int createCompany(CompanyDto companyDto) {

    // Validate company
    CompanyDtoValidator.validate(companyDto);

    // Map
    Company company = CompanyMapper.toCompany(companyDto);

    // Create company
    return companyService.createCompany(company);
  }

}
