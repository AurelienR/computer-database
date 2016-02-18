package com.excilys.cdb.services;

import java.util.List;

import javax.validation.ValidationException;

import com.excilys.cdb.daos.impl.ComputerDAOImpl;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.ComputerDTOValidator;
import com.excilys.cdb.validators.QueryPageParameterValidator;

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

	public List<ComputerDTO> findByQuery(QueryPageParameter qp){
		
		// Validate queryParameter
		QueryPageParameterValidator.validate(qp);
		
		// Retrieve computer
		List<Computer> computers = ComputerService.getInstance().findByQuery(qp);
		
		// Map
		return ComputerMapper.toComputerDTOList(computers);
	}
	
	
	public int count(){
		return ComputerService.getInstance().count();
	}
	
	public List<ComputerDTO> findAll() {
		
		// Retrieve computers
		List<Computer> computers = ComputerService.getInstance().findAll();
		
		// Map
		return ComputerMapper.toComputerDTOList(computers);
	}

	public List<ComputerDTO> findById(int id) throws ValidationException {
		
		// Validate id
		ComputerDTOValidator.checkValidId(id);
		
		// Retrieve Computers
		List<Computer> computers = ComputerService.getInstance().findById(id);
		
		// Map
		return ComputerMapper.toComputerDTOList(computers);
	}

	public List<ComputerDTO> findByName(String name) throws ValidationException {
		
		// Validate name
		ComputerDTOValidator.checkNameNotNull(name);
		ComputerDTOValidator.checkNameNotEmpty(name);
		
		// Retrieve computers
		List<Computer> computers = ComputerService.getInstance().findByName(name);
		
		// Map
		return ComputerMapper.toComputerDTOList(computers);
	}

	public int createComputer(ComputerDTO computerDTO) throws ValidationException {

		// Validate computerDTO
		ComputerDTOValidator.validate(computerDTO);
		
		// Map
		Computer computer = ComputerMapper.toComputer(computerDTO);
		
		// Create computer
		return ComputerService.getInstance().createComputer(computer);
	}

	public void updateComputer(ComputerDTO computerDTO) throws ValidationException {
		
		// validate computerDTO
		ComputerDTOValidator.validate(computerDTO);
		
		// Map
		Computer computer = ComputerMapper.toComputer(computerDTO);
		
		// Update computer
		ComputerDAOImpl.getInstance().updateComputer(computer);
	}

	public void deleteComputer(int id) throws ValidationException {
		
		// Validate id
		ComputerDTOValidator.checkValidId(id);
		
		// DeleteComputer
		ComputerDAOImpl.getInstance().deleteComputer(id);
	}

}
