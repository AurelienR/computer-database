package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.dao.impl.ComputerDAOImpl;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.validator.CompanyDTOValidator;
import com.excilys.cdb.validator.ComputerDTOValidator;

/**
 * Service layer to ComputerDAO
 * 
 * @author Aurelien.R
 *
 */
public class ComputerDTOService {

	// Singleton
	private static ComputerDTOService instance;

	// Constructors
	private ComputerDTOService() {
	};

	// Methods
	public static ComputerDTOService getInstance() {
		if (instance == null) {
			instance = new ComputerDTOService();
		}
		return instance;
	}

	public List<ComputerDTO> findAll() throws DAOException {
		
		// Retrieve computers
		List<Computer> computers = ComputerService.getInstance().findAll();
		
		// Map
		return ComputerMapper.toComputerDTOList(computers);
	}

	public List<ComputerDTO> findById(int id){
		
		// Validate id
		ComputerDTOValidator.checkValidId(id);
		
		// Retrieve Computers
		List<Computer> computers = ComputerService.getInstance().findById(id);
		
		// Map
		return ComputerMapper.toComputerDTOList(computers);
	}

	public List<ComputerDTO> findByName(String name){
		
		// Validate name
		ComputerDTOValidator.checkNameNotNull(name);
		ComputerDTOValidator.checkNameNotEmpty(name);
		
		// Retrieve computers
		List<Computer> computers = ComputerService.getInstance().findByName(name);
		
		// Map
		return ComputerMapper.toComputerDTOList(computers);
	}

	public int createComputer(ComputerDTO computerDTO){

		// Validate computerDTO
		ComputerDTOValidator.checkValidId(computerDTO.getId());
		ComputerDTOValidator.checkNameNotNull(computerDTO.getName());
		ComputerDTOValidator.checkNameNotEmpty(computerDTO.getName());
		ComputerDTOValidator.checkDateConsistency(computerDTO);			
		
		// Validate related companyDTO
		if(computerDTO.getCompany() != null){
			CompanyDTOValidator.checkValidId(computerDTO.getCompany().getId());
		}
		
		// Map
		Computer computer = ComputerMapper.toComputer(computerDTO);
		
		// Create computer
		return ComputerService.getInstance().createComputer(computer);
	}

	public void updateComputer(ComputerDTO computerDTO){
		
		// Validate computerDTO
		ComputerDTOValidator.checkValidId(computerDTO.getId());
		ComputerDTOValidator.checkNameNotNull(computerDTO.getName());
		ComputerDTOValidator.checkNameNotEmpty(computerDTO.getName());
		ComputerDTOValidator.checkDateConsistency(computerDTO);			
		
		// Validate related companyDTO
		if(computerDTO.getCompany() != null){
			CompanyDTOValidator.checkValidId(computerDTO.getCompany().getId());
		}
		
		// Map
		Computer computer = ComputerMapper.toComputer(computerDTO);
		
		// Update computer
		ComputerDAOImpl.getInstance().updateComputer(computer);
	}

	public void deleteComputer(int id){
		
		// Validate id
		ComputerDTOValidator.checkValidId(id);
		
		// DeleteComputer
		ComputerDAOImpl.getInstance().deleteComputer(id);
	}

}
