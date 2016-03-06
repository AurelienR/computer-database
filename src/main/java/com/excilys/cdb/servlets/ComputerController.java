package com.excilys.cdb.servlets;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;
import com.excilys.cdb.mappers.ComputerPageMapper;
import com.excilys.cdb.mappers.QueryPageParameterMapper;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.services.CompanyDtoService;
import com.excilys.cdb.services.ComputerDtoService;
import com.excilys.cdb.validators.spring.SpringCompanyDtoValidator;
import com.excilys.cdb.validators.spring.SpringComputerDtoValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/computers")
public class ComputerController {

  // Services
  @Autowired
  ComputerDtoService computerDtoService;
  @Autowired
  CompanyDtoService companyDtoService;

  // Validators
  @Autowired
  SpringComputerDtoValidator springComputerDtoValidator;
  @Autowired
  SpringCompanyDtoValidator springCompanyDtoValidator;

  /**
   * Display computer dashborad view.
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

    // Get related queryPageParameter
    QueryPageParameter qp =
        QueryPageParameterMapper.toQueryPageParameter(page, pageSize, search, orderBy, order);

    // Retrieve DTOs
    List<ComputerDto> computerDtos = computerDtoService.findByQuery(qp);

    // Get total matching computers
    qp.setMatchingRowCount(computerDtoService.count(qp));
    ComputerPageDto pageDto = ComputerPageMapper.toComputerPageDto(qp, computerDtos);

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

    // Retrieve company and computers
    ComputerDto computerDto = computerDtoService.findById(id).get(0);
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

    // Retrieve company and computers
    List<CompanyDto> companyDtos = companyDtoService.findAll();

    // Prepare request
    model.addAttribute("companies", companyDtos);
    model.addAttribute("computerDto", new ComputerDto(0, "", null, null, new CompanyDto(0, "")));

    return "addComputer";
  }

  /**
   * Edit an existing computer.
   *
   * @param id id of the computer to edit
   * @param computerDto the computer dto
   * @param result the result
   * @return the string
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public String editComputer(@PathVariable Long id, @ModelAttribute ComputerDto computerDto,
      BindingResult result) {

    if (!id.equals(computerDto.getId())) {
      throw new DataIntegrityViolationException(
          "Inconsistent url computer id and POST request informations");
    }

    // Validate dto
    springCompanyDtoValidator.validate(computerDto, result);

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
  public String newComputer(@ModelAttribute("computerDto") ComputerDto computerDto,
      BindingResult result) {

    if (computerDto.getCompany() == null || computerDto.getCompany().getId() == 0) {
      computerDto.setCompany(null);
    }

    // Validate dto
    springComputerDtoValidator.validate(computerDto, result);

    // Create computer
    computerDtoService.createComputer(computerDto);

    // Delete computer
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
  public String deleteComputer(@ModelAttribute("selection")  String idsStr, BindingResult result) {

    // Retrieve parameters
    String[] ids = idsStr.split(",");

    // Delete each computer by id
    for (String idStr : ids) {
      // Car can throw exception
      long id = Long.parseLong(idStr);
      computerDtoService.deleteComputer(id);
    }

    return "redirect:/computers";
  }
}
