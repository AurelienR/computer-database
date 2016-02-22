package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.daos.impl.CompanyDAOImpl;
import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.CompanyDTOValidator;

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

	/**
	 * Find all companies in DB
	 * @return list of all CompanyDTOs objects
	 */
	public List<CompanyDTO> findAll() {
		
		// Retrieve companyDTOs
		List<Company> companies = CompanyService.getInstance().findAll();		
		return CompanyMapper.toCompanyDTOList(companies);
	}

	/**
	 * Find a company by its id
	 * @param id id of the company to search
	 * @return List of matching CompanyDTOs
	 */
	public List<CompanyDTO> findById(int id) {		
		
		// Validate id
		CompanyDTOValidator.checkValidId(id);
		
		// Retrieve Companies
		List<Company> companies = CompanyDAOImpl.getInstance().findById(id);
		
		// Map
		return CompanyMapper.toCompanyDTOList(companies);
	}

	/**
	 * Find companies by name
	 * @param name name of the company to search
	 * @return List of matching CompanyDTos
	 */
	public List<CompanyDTO> findByName(String name) {
		
		// Validate name
		CompanyDTOValidator.checkNameNotNull(name);
		CompanyDTOValidator.checkNameNotEmpty(name);
		
		// Retrieve Companies
		List<Company> companies= CompanyDAOImpl.getInstance().findByName(name);
		
		// Map
		return CompanyMapper.toCompanyDTOList(companies);
	}

	/**
	 * Add given CompanyDTO to db
	 * @param companyDTO company to add
	 * @return id of the created company
	 */
	public int createCompany(CompanyDTO companyDTO) {
		
		// Validate company
		CompanyDTOValidator.validate(companyDTO);
		
		// Map
		Company company = CompanyMapper.toCompany(companyDTO);
		
		// Create company
		return CompanyService.getInstance().createCompany(company);
	}

}
