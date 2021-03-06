package com.excilys.cdb.mappers;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.utils.DateFormatManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class ComputerMapper.
 */
public class ComputerMapper {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

  /**
   * Map a Computer to a ComputerDTO.
   * 
   * @param computer Computer to map
   * @return related ComputerDTO
   */
  public static ComputerDto toComputerDto(Computer computer) {

    if (computer == null) {
      return null;
    }

    long id = computer.getId();
    String computerName = computer.getName();
    String introStr = DateFormatManager.toLocalDateStringFormat(computer.getIntroduced());
    String discStr = DateFormatManager.toLocalDateStringFormat(computer.getDiscontinued());
    CompanyDto companyDto = CompanyMapper.toCompanyDto(computer.getCompany());

    ComputerDto computerDto = new ComputerDto(id, computerName, introStr, discStr, companyDto);
    LOGGER.debug("\n\t\tMapper: map: computer: {}\n\t\tTO: ComputerDto: {}", computer, computerDto);

    return computerDto;
  }

  /**
   * Map to computerDto from request object.
   *
   * @param request the request
   * @return the computer dto
   */
  public static ComputerDto toComputerDto(HttpServletRequest request) {
    // Retrieve Computer information
    long id = Long.parseLong(request.getParameter("id"));
    String nameStr = request.getParameter("computerName");
    String introducedStr = request.getParameter("introduced");
    String discontinuedStr = request.getParameter("discontinued");

    // Retrieve related Company
    long companyId;
    CompanyDto companyDto;
    try {
      companyId = Long.parseLong(request.getParameter("companyId"));
      companyDto = new CompanyDto(companyId, null);
    } catch (NumberFormatException e) {
      companyDto = null;
    }

    // Instanciate related ComputerDTO
    ComputerDto computerDto =
        new ComputerDto(id, nameStr, introducedStr, discontinuedStr, companyDto);

    LOGGER.debug("\n\t\tMapper: map: request: {}\n\t\tTO: ComputerDto: {}", request, computerDto);

    return computerDto;
  }

  /**
   * Map a list of Computer to ComputerDTOs.
   * 
   * @param computers list of Computer
   * @return list of ComputerDTOs
   */
  public static List<ComputerDto> toComputerDtoList(List<Computer> computers) {
    if (computers == null) {
      return null;
    }
    List<ComputerDto> computerDtos = new ArrayList<ComputerDto>();
    computers.stream().forEachOrdered(c -> computerDtos.add(toComputerDto(c)));

    LOGGER.debug("\n\t\tMapper: Map: List<Computer>: {}\n\t\tTO: List<ComputerDto>: {}", computers,
        computerDtos);

    return computerDtos;
  }

  /**
   * Convert computer to computerDto page.
   *
   * @param computerPage the computer page
   * @return the page
   */
  public static Page<ComputerDto> toComputerDtoPage(Page<Computer> computerPage) {

    Converter<Computer, ComputerDto> conv = new Converter<Computer, ComputerDto>() {
      @Override
      public ComputerDto convert(Computer computer) {
        return toComputerDto(computer);
      }
    };
    
    return computerPage.map(conv);
  }

  /**
   * Map a ComputerDTO to a Computer.
   *
   * @param computerDto ComputerDTO to map
   * @return related Computer
   */
  public static Computer toComputer(ComputerDto computerDto) {
    if (computerDto == null) {
      return null;
    }
    long id = computerDto.getId();
    String computerName = computerDto.getName();
    LocalDateTime intro = DateFormatManager.parseLocal(computerDto.getIntroduced());
    LocalDateTime disc = DateFormatManager.parseLocal(computerDto.getDiscontinued());
    Company company = CompanyMapper.toCompany(computerDto.getCompany());
    Computer computer = new Computer(id, computerName, company, disc, intro);

    LOGGER.debug("\n\t\tMapper: map: computerDto: {}\n\t\tTO: computer: {}", computerDto, computer);

    return computer;
  }

}
