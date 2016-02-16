package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.dao.impl.CompanyDAOImpl;
import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validator.CompanyDTOValidator;

/**
 * Service layer to CompanyDAO
 * 
 * @author Aurelien.R
 *
 */
public class CompanyDTOService {
	// Singleton
	private static CompanyDTOService instance;

	// Constructors
	private CompanyDTOService() {
	};

	// Methods
	public static CompanyDTOService getInstance() {
		if (instance == null) {
			instance = new CompanyDTOService();
		}
		return instance;
	}

	public List<CompanyDTO> findAll() {
		
		// Retrieve companyDTOs
		List<Company> companies = CompanyService.getInstance().findAll();		
		return CompanyMapper.toCompanyDTOList(companies);
	}

	public List<CompanyDTO> findById(int id) {		
		
		// Validate id
		CompanyDTOValidator.checkValidId(id);
		
		// Retrieve Companies
		List<Company> companies = CompanyDAOImpl.getInstance().findById(id);
		
		// Map
		return CompanyMapper.toCompanyDTOList(companies);
	}

	public List<CompanyDTO> findByName(String name) {
		
		// Validate name
		CompanyDTOValidator.checkNameNotNull(name);
		CompanyDTOValidator.checkNameNotEmpty(name);
		
		// Retrieve Companies
		List<Company> companies= CompanyDAOImpl.getInstance().findByName(name);
		
		// Map
		return CompanyMapper.toCompanyDTOList(companies);
	}

	public int createCompany(CompanyDTO companyDTO) {
		
		// Validate company
		CompanyDTOValidator.checkNameNotNull(companyDTO.getName());
		CompanyDTOValidator.checkNameNotEmpty(companyDTO.getName());
		CompanyDTOValidator.checkValidId(companyDTO.getId());
		
		// Map
		Company company = CompanyMapper.toCompany(companyDTO);
		
		// Create company
		return CompanyService.getInstance().createCompany(company);
	}

}
