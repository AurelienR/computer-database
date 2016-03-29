package com.excilys.cdb.controllers;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;
import com.excilys.cdb.mappers.ComputerPageMapper;
import com.excilys.cdb.mappers.QueryPageParameterMapper;
import com.excilys.cdb.models.OrderBy;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.services.CompanyDtoService;
import com.excilys.cdb.services.ComputerDtoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

@RestController
@RequestMapping("/json")
public class CliJsonController {

  @Autowired
  MessageSource messageSource;

  @Autowired
  ComputerDtoService computerDtoService;

  @Autowired
  CompanyDtoService companyDtoService;

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(CliJsonController.class);

  // Constants
  private static final int PAGE_SIZE = 30;

  @RequestMapping("/localDateFormat")
  public String getLocalDateFormat() {
    LOGGER.info("CliJsonController: REST api: GET /localDateFormat");
    Locale local = LocaleContextHolder.getLocale();
    String dateFormatStr = messageSource.getMessage("property.dateFormat", null, local);

    return dateFormatStr;
  }

  @RequestMapping("/computers")
  public ComputerPageDto getComputerPage(@RequestParam("page") int page) {
    LOGGER.info("CliJsonController: REST api: GET /computers : parameters:\n\tpage={}", page);

    // Get related queryPageParameter
    QueryPageParameter qp = QueryPageParameterMapper.toQueryPageParameter(page, PAGE_SIZE, "",
        OrderBy.id, Direction.ASC);

    // Retrieve DTOs
    Page<ComputerDto> computerDtoPage = computerDtoService.findByQuery(qp);

    // Get total matching computers
    ComputerPageDto pageDto = ComputerPageMapper.toComputerPageDto(qp.getSearch(), computerDtoPage);

    return pageDto;
  }

  @RequestMapping(value = "computers/{id}", method = RequestMethod.GET)
  public ComputerDto getComputer(@PathVariable Long id) {

    LOGGER.info("CliJsonController: REST api: GET REST api: /computer/{}", id);

    // Retrieve company and computers
    ComputerDto computerDto = computerDtoService.findById(id);

    // Display edit view
    return computerDto;
  }

  @RequestMapping(value = "computers/edit", method = RequestMethod.POST)
  public ResponseEntity<ComputerDto> editComputer(@Valid @RequestBody ComputerDto computerDto,
      BindingResult result) {

    LOGGER.info("CliJsonController: REST api: POST: /computers/{} parameters:\n\tComputerDto: {} ",
        computerDto);

    // Manage validation DTO errors
    if (result.hasErrors()) {

      LOGGER.error("CliJsonController: REST api: Error on validating Dto");

      // Return to edit page
      return new ResponseEntity<>(computerDto, HttpStatus.BAD_REQUEST);
    }

    // Edit computer
    computerDtoService.updateComputer(computerDto);

    // Redirect to dashboard
    return new ResponseEntity<>(computerDto, HttpStatus.OK);
  }

  @RequestMapping(value = "computers/new", method = RequestMethod.POST)
  public ResponseEntity<ComputerDto> newComputer(@Valid @RequestBody ComputerDto computerDto,
      BindingResult result) {

    LOGGER.info("CliJsonController: REST api: POST /computers/new parameters\n\tComputerDto: {}",
        computerDto);

    // Manage validation DTO errors
    if (result.hasErrors()) {

      LOGGER.error("CliJsonController: Error on validating Dto");
      return new ResponseEntity<ComputerDto>(HttpStatus.BAD_REQUEST);
    }

    if (computerDto.getCompany() == null || computerDto.getCompany().getId() == 0) {
      computerDto.setCompany(null);
    }

    // Create computer
    computerDtoService.createComputer(computerDto);

    return new ResponseEntity<ComputerDto>(computerDto, HttpStatus.BAD_REQUEST);

  }

  @RequestMapping(value = "/companies", method = RequestMethod.GET)
  public List<CompanyDto> getCompanyList() {
    LOGGER.info("Controller: REST api: GET /companies :");
    return companyDtoService.findAll();
  }

  @RequestMapping(value = "computers/delete", method = RequestMethod.POST)
  public ResponseEntity<Long> deleteComputer(@RequestParam("id") long id) {

    LOGGER.info("CliJsonController: REST api: POST /computers/delete parameters:\n\tIds: {}", id);

    // Delete each computer by id
    computerDtoService.deleteComputer(id);

    // Ok response status
    return new ResponseEntity<Long>(id, HttpStatus.OK);
  }

  @RequestMapping(value = "companies/delete", method = RequestMethod.POST)
  public ResponseEntity<Long> deleteCompany(@RequestParam("id") long id) {

    LOGGER.info("CliJsonController: REST api: POST /computers/delete parameters:\n\tIds: {}", id);

    // Delete company by its id
    companyDtoService.deleteCompany(id);

    // Ok response status
    return new ResponseEntity<Long>(id, HttpStatus.OK);
  }

}
