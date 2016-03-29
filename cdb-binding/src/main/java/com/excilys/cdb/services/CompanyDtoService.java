package com.excilys.cdb.services;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.utils.CompanyDtoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
  public CompanyDto findById(long id) {

    // Validate id
    CompanyDtoValidator.checkValidId(id);

    // Retrieve Companies
    Company company = companyService.findById(id);

    // Map
    return CompanyMapper.toCompanyDto(company);
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
  public long createCompany(CompanyDto companyDto) {

    // Validate company
    CompanyDtoValidator.validate(companyDto);

    // Map
    Company company = CompanyMapper.toCompany(companyDto);

    // Create company
    return companyService.createCompany(company);
  }

  /**
   * Delete computer.
   *
   * @param id the id
   */
  public void deleteCompany(long id) {

    // Validate id
    CompanyDtoValidator.checkValidId(id);

    // Retrieve Companies
    companyService.deleteCompany(id);
  }
}
