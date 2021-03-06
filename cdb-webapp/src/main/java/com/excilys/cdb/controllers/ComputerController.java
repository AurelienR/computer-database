package com.excilys.cdb.controllers;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;
import com.excilys.cdb.mappers.ComputerPageMapper;
import com.excilys.cdb.mappers.QueryPageParameterMapper;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.services.CompanyDtoService;
import com.excilys.cdb.services.ComputerDtoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/computers")
public class ComputerController {

  // Logger
  static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

  // Services
  @Autowired
  ComputerDtoService computerDtoService;
  @Autowired
  CompanyDtoService companyDtoService;

  /**
   * ComputerDto provider for model attribute.
   * 
   * @return an empty ComputerDto
   */
  @ModelAttribute("computerDto")
  public ComputerDto getComputerDto() {
    return new ComputerDto(0, "", null, null, new CompanyDto(0, ""));
  }
  
  /**
   * Gets the username model attribute.
   *
   * @return the username
   */
  @ModelAttribute("user")
  public String getUsername() {
    return LoginController.getPrincipal();
  }
  

  /**
   * Display computer dashboard view.
   *
   * @param page index of computer list
   * @param pageSize number of computers displayed per pages
   * @param orderBy grouping computers criteria
   * @param order way of ordering computers
   * @param search search criteria on company or computers
   * @param model spring model
   * @return name of the view to redirect to
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String dashboard(@RequestParam(value = "page", required = false) String page,
      @RequestParam(value = "pageSize", required = false) String pageSize,
      @RequestParam(value = "orderBy", required = false) String orderBy,
      @RequestParam(value = "order", required = false) String order,
      @RequestParam(value = "search", required = false) String search, Model model) {

    LOGGER.info(
        "Controller: GET /computers : parameters:\n\tpage={} pageSize={} orderBy={}  "
        + "order={} search={}",
        page, pageSize, orderBy, order, search);

    // Get related queryPageParameter
    QueryPageParameter qp =
        QueryPageParameterMapper.toQueryPageParameter(page, pageSize, search, orderBy, order);

    // Retrieve DTOs
    Page<ComputerDto> computerDtoPage = computerDtoService.findByQuery(qp);

    // Get total matching computers
    // long totalmumber = computerDtoService.count(qp);
    ComputerPageDto pageDto = ComputerPageMapper.toComputerPageDto(qp.getSearch(),computerDtoPage);

    // Prepare request
    model.addAttribute("page", pageDto);

    return "dashboard";
  }

  /**
   * Display edit computer view.
   *
   * @param id id of the computer to display
   * @param model spring model
   * @return name of the view to redirect to
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String editView(@PathVariable Long id, Model model) {

    LOGGER.info("Controller: GET /computer/{}", id);

    // Retrieve company and computers
    ComputerDto computerDto = computerDtoService.findById(id);
    List<CompanyDto> companyDtos = companyDtoService.findAll();

    // Prepare request
    model.addAttribute("computer", computerDto);
    model.addAttribute("companies", companyDtos);

    // Display edit view
    return "editComputer";
  }

  /**
   * Display edition view for adding a new computer.
   *
   * @param model spring model
   * @return name of the view to display
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public String newView(Model model) {

    LOGGER.info("ComputerController: GET /computers/new");

    // Retrieve company and computers
    List<CompanyDto> companyDtos = companyDtoService.findAll();

    // Prepare request
    model.addAttribute("companies", companyDtos);

    return "addComputer";
  }

  /**
   * Edit an existing computer.
   *
   * @param computerDto the computer dto
   * @param result the result
   * @return the string
   */
  @RequestMapping(value = "/edit", method = RequestMethod.POST)
  public String editComputer(@Valid @ModelAttribute ComputerDto computerDto,
      BindingResult result, Model model) {

    LOGGER.info("ComputerController: POST: /computers/{} parameters:\n\tComputerDto: {} ",
        computerDto);

    // Manage validation DTO errors
    if (result.hasErrors()) {

      LOGGER.error("ComputerController: Error on validating Dto");

      // Prepare models for edit view
      List<CompanyDto> companyDtos = companyDtoService.findAll();
      model.addAttribute("computer", computerDto);
      model.addAttribute("companies", companyDtos);
      // Return to edit page
      return "editComputer";
    }

    // Edit computer
    computerDtoService.updateComputer(computerDto);

    // Redirect to dashboard
    return "redirect:/computers";
  }

  /**
   * Add a new computer
   *
   * @param computerDto the computer dto to add
   * @param result the result
   * @return the string view to redirect to.
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String newComputer(@Valid @ModelAttribute("computerDto") ComputerDto computerDto,
      BindingResult result, Model model) {

    LOGGER.info("ComputerController: POST /computers/new parameters\n\tComputerDto: {}",
        computerDto);

    // Manage validation DTO errors
    if (result.hasErrors()) {

      LOGGER.error("ComputerController: Error on validating Dto");

      // Prepare models for edit view
      List<CompanyDto> companyDtos = companyDtoService.findAll();
      model.addAttribute("computerDto", computerDto);
      model.addAttribute("companies", companyDtos);
      // Return to edit page
      return "addComputer";
    }

    if (computerDto.getCompany() == null || computerDto.getCompany().getId() == 0) {
      computerDto.setCompany(null);
    }

    // Create computer
    computerDtoService.createComputer(computerDto);

    return "redirect:/computers";
  }

  /**
   * Delete computers based on the ids POSTed.
   *
   * @param idsStr ids splited by comma
   * @param result the result
   * @return name of the view to display
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String deleteComputer(@ModelAttribute("selection") String idsStr, BindingResult result) {

    LOGGER.info("ComputerController: POST /computers/delete parameters:\n\tIds: {}", idsStr);

    // Retrieve parameters
    String[] ids = idsStr.split(",");

    // Delete each computer by id
    for (String idStr : ids) {
      long id = Long.parseLong(idStr);
      computerDtoService.deleteComputer(id);
    }

    return "redirect:/computers";
  }
}
