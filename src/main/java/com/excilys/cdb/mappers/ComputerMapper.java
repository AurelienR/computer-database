package com.excilys.cdb.mappers;

import com.excilys.cdb.daos.impl.ComputerDaoImpl;
import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.utils.DateFormatManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class ComputerMapper.
 */
public class ComputerMapper {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(ComputerMapper.class);

  /**
   * Deserialize DB results to List of computer.
   * 
   * @param results
   *          Result to process
   * @return List of computer
   * @throws SQLException
   *           issue with db
   */
  public static List<Computer> toComputerList(ResultSet results) throws SQLException {

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
      Company company =
          new Company(results.getInt("company.id"), results.getString("company.name"));
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

    ComputerDto computerDto = new ComputerDto(id, computerName, introStr, discStr, companyDto);
    logger.debug("\nMapper: map: computer" + computer + "\n TO: ComputerDto: " + computerDto);

    return computerDto;
  }

  /**
   * Map to computerDto from request object.
   *
   * @param request
   *          the request
   * @return the computer dto
   */
  public static ComputerDto toComputerDto(HttpServletRequest request) {
    // Retrieve Computer information
    int id = Integer.parseInt(request.getParameter("id"));
    String nameStr = request.getParameter("computerName");
    String introducedStr = request.getParameter("introduced");
    String discontinuedStr = request.getParameter("discontinued");

    // Retrieve related Company
    int companyId;
    CompanyDto companyDto;
    try {
      companyId = Integer.parseInt(request.getParameter("companyId"));
      companyDto = new CompanyDto(companyId, null);
    } catch (NumberFormatException e) {
      companyDto = null;
    }

    // Instanciate related ComputerDTO
    ComputerDto computerDto =
        new ComputerDto(id, nameStr, introducedStr, discontinuedStr, companyDto);

    logger.debug("\nMapper: map: request:" + request + "\n TO: ComputerDto: " + computerDto);

    return computerDto;
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

    logger.debug(
        "\nMapper: Map: List<Computer>:" + computers + "\n TO: List<ComputerDto>:" + computerDtos);

    return computerDtos;
  }

  /**
   * Map a ComputerDTO to a Computer.
   *
   * @param computerDto
   *          ComputerDTO to map
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
    Computer computer = new Computer(id, computerName, company, disc, intro);

    logger.debug("\nMapper: map: computerDto: " + computerDto + "\n TO: computer: " + computer);

    return computer;
  }

}
