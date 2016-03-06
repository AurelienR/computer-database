package com.excilys.cdb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
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

    @ModelAttribute("companies")
    public List<CompanyDto> getAllCompanies() {
	// Delegate to service
	return companyDtoService.findAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dashboard(@RequestParam(value = "page", required = false) int page,
	    @RequestParam(value = "pageSize", required = false) int pageSize,
	    @RequestParam(value = "orderBy", required = false) String orderBy,
	    @RequestParam(value = "order", required = false) String order,
	    @RequestParam(value = "search", required = false) String search, Model model) {

	// Get related queryPageParameter
	QueryPageParameter qp = QueryPageParameterMapper.toQueryPageParameter(page, pageSize, search, orderBy, order);

	// Retrieve DTOs
	List<ComputerDto> computerDtos = computerDtoService.findByQuery(qp);

	// Get total matching computers
	qp.setMatchingRowCount(computerDtoService.count(qp));
	ComputerPageDto pageDto = ComputerPageMapper.toComputerPageDto(qp, computerDtos);

	// Prepare request
	model.addAttribute("page", pageDto);

	return "dashboard";
    }

    @RequestMapping(value = "/{idStr}", method = RequestMethod.GET)
    public String editView(@PathVariable Long id, Model model) {

	// Retrieve company and computers
	ComputerDto computerDto = computerDtoService.findById(id).get(0);

	// Prepare request
	model.addAttribute("computer", computerDto);

	// Display edit view
	return "editComputer";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newView() {

	return "addComputer";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String editComputer(@PathVariable String id, @ModelAttribute ComputerDto computerDto, BindingResult result) {

	// Validate dto
	springCompanyDtoValidator.validate(computerDto, result);
	
	// Edit computer
	computerDtoService.updateComputer(computerDto);
	
	// Redirect to dashboard
	return "redirect:/computers";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newComputer(@ModelAttribute ComputerDto computerDto , BindingResult result) {

	// Validate dto
	springCompanyDtoValidator.validate(computerDto, result);
	
	// Create computer
	computerDtoService.createComputer(computerDto);
	
	// Delete computer
	return "redirect:/computers";
    }
}
