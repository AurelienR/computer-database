package com.excilys.cdb.mappers;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.models.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CompanyMapper {

  // Logger
  static final Logger LOGGER = LoggerFactory.getLogger(CompanyMapper.class);

  /**
   * Map a Company to a CompanyDTO.
   * 
   * @param company Company to map
   * @return related CompanyDTO
   */
  public static CompanyDto toCompanyDto(Company company) {

    if (company == null) {
      return null;
    }
    CompanyDto companyDto = new CompanyDto(company.getId(), company.getName());
    LOGGER.debug("\n\t\tMapper: map: company: {}\n\t\tTO: companyDto: {}",company, companyDto);
    return companyDto;
  }

  /**
   * Map a CompanyDTO to a Company.
   * 
   * @param companyDto Company to map
   * @return related CompanyDTO
   */
  public static Company toCompany(CompanyDto companyDto) {
    if (companyDto == null) {
      return null;
    }

    Company company = new Company(companyDto.getId(), companyDto.getName());

    LOGGER.debug("\n\t\tMapper: map: companyDto: {}\n\t\tTO: company: {}", companyDto,company);

    return company;
  }

  /**
   * Map a list of Company to a list of CompanyDTO.
   * 
   * @param companies List of Company to map
   * @return related List of CompanyDTO
   */
  public static List<CompanyDto> toCompanyDtoList(List<Company> companies) {
    if (companies == null) {
      return null;
    }
    List<CompanyDto> companyDtos = new ArrayList<CompanyDto>();
    companies.parallelStream().forEachOrdered(c -> companyDtos.add(toCompanyDto(c)));

    LOGGER.debug(
        "\n\t\tMapper: map: List<Company>:{}\n\t\tTO: companyDtos: {}" ,companies,companyDtos);

    return companyDtos;
  }
}
