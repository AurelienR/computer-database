package com.excilys.cdb.mappers;

import com.excilys.cdb.daos.impl.CompanyDaoImpl;
import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.models.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyMapper {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(CompanyMapper.class);

  /**
   * Gets deserialized resultSet to list of companies.
   * 
   * @param results results set to process
   * @return list of related companies
   * @throws SQLException issue on db
   */
  public static List<Company> getCompaniesFromResults(ResultSet results) throws SQLException {

    ArrayList<Company> companyList = new ArrayList<Company>();

    while (results.next()) {
      companyList.add(new Company(results.getInt(CompanyDaoImpl.ID_COLUMN),
          results.getString(CompanyDaoImpl.NAME_COLUMN)));
    }

    return companyList;
  }

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
    logger.debug("\n\t\tMapper: map: company: " + company + "\n\t\t TO: companyDto: " + companyDto);
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

    logger.debug("\n\t\tMapper: map: companyDto: " + companyDto + "\n\t\t TO: company: " + company);

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

    logger.debug(
        "\n\t\tMapper: map: List<Company>:" + companies + "\n\t\t TO: companyDtos: " + companyDtos);

    return companyDtos;
  }
}
