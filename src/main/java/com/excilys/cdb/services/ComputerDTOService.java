package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.daos.DAOException;
import com.excilys.cdb.daos.impl.ComputerDAOImpl;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.validators.ComputerDTOValidator;

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
		ComputerDTOValidator.validate(computerDTO);
		
		// Map
		Computer computer = ComputerMapper.toComputer(computerDTO);
		
		// Create computer
		return ComputerService.getInstance().createComputer(computer);
	}

	public void updateComputer(ComputerDTO computerDTO){
		
		// validate computerDTO
		ComputerDTOValidator.validate(computerDTO);
		
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
