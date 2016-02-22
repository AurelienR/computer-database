package com.excilys.cdb.mappers;

import com.excilys.cdb.daos.impl.ComputerDaoImpl;
import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.utils.DateFormatManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerMapper.
 */
public class ComputerMapper {

  /**
   * Deserialize DB results to List of computer.
   * 
   * @param results
   *          Result to process
   * @return List of computer
   * @throws SQLException issue with db
   */
  public static List<Computer> getComputersFromResults(ResultSet results) throws SQLException {

    ArrayList<Computer> computerList = new ArrayList<Computer>();

    // Fetch DB results
    while (results.next()) {

      // Set computer fields

      // Id
      int computerId = results.getInt("computer.id");
      // Name
      String computerName = results.getString("computer.name");

      // Introduced
      Timestamp introTimestamp = results.getTimestamp(ComputerDaoImpl.INTRO_COLUMN);
      LocalDateTime introduced;
      if (introTimestamp == null) {
        introduced = null;
      } else {
        introduced = introTimestamp.toLocalDateTime();
      }

      // Discontinued
      Timestamp descTimestamp = results.getTimestamp(ComputerDaoImpl.DISC_COLUMN);
      LocalDateTime discontinued;
      if (descTimestamp == null) {
        discontinued = null;
      } else {
        discontinued = descTimestamp.toLocalDateTime();
      }

      // Initialize related company
      Company company = new Company(results.getInt("company.id"),
          results.getString("company.name"));
      // Initialize computer
      Computer computer = new Computer(computerId, computerName, company, discontinued, introduced);

      computerList.add(computer);
    }

    return computerList;
  }

  /**
   * Map a Computer to a ComputerDTO.
   * 
   * @param computer
   *          Computer to map
   * @return related ComputerDTO
   */
  public static ComputerDto toComputerDto(Computer computer) {

    if (computer == null) {
      return null;
    }

    int id = computer.getId();
    String computerName = computer.getName();
    String introStr = DateFormatManager.toHtmlDateString(computer.getIntroduced());
    String discStr = DateFormatManager.toHtmlDateString(computer.getDiscontinued());
    CompanyDto companyDto = CompanyMapper.toCompanyDto(computer.getCompany());

    return new ComputerDto(id, computerName, introStr, discStr, companyDto);
  }

  /**
   * Map a list of Computer to ComputerDTOs.
   * 
   * @param computers
   *          list of Computer
   * @return list of ComputerDTOs
   */
  public static List<ComputerDto> toComputerDtoList(List<Computer> computers) {
    if (computers == null) {
      return null;
    }
    List<ComputerDto> computerDtos = new ArrayList<ComputerDto>();
    computers.parallelStream().forEachOrdered(c -> computerDtos.add(toComputerDto(c)));
    return computerDtos;
  }

  /**
   * Map a ComputerDTO to a Computer.
   *
   * @param computerDto          ComputerDTO to map
   * @return related Computer
   */
  public static Computer toComputer(ComputerDto computerDto) {
    if (computerDto == null) {
      return null;
    }
    int id = computerDto.getId();
    String computerName = computerDto.getName();
    LocalDateTime intro = DateFormatManager.parseHtmlDateString(computerDto.getIntroduced());
    LocalDateTime disc = DateFormatManager.parseHtmlDateString(computerDto.getDiscontinued());
    Company company = CompanyMapper.toCompany(computerDto.getCompany());
    return new Computer(id, computerName, company, disc, intro);
  }
}
