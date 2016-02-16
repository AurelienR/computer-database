package com.excilys.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.impl.ComputerDAOImpl;
import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.utils.DateFormatManager;

public class ComputerMapper {
	
	/**
	 * Deserialize DB results to List of computer
	 * @param results Result to process
	 * @return List of computer
	 * @throws SQLException
	 */
	public static List<Computer> getComputersFromResults(ResultSet results) throws SQLException{
		
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		
		// Fetch DB results
		while(results.next()){
			
			// Set computer fields
			
			// Id
			int computerId = results.getInt("computer.id");
			// Name
			String computerName = results.getString("computer.name");
			
			// Introduced
			Timestamp introTimestamp = results.getTimestamp(ComputerDAOImpl.INTRO_COLUMN);
			LocalDateTime introduced;
			if(introTimestamp == null){
				introduced = null;
			}
			else{
				introduced = introTimestamp.toLocalDateTime();
			}
			
			// Discontinued
			Timestamp descTimestamp = results.getTimestamp(ComputerDAOImpl.DISC_COLUMN);
			LocalDateTime discontinued;
			if(descTimestamp == null){
				discontinued = null;
			}
			else{
				discontinued = descTimestamp.toLocalDateTime();
			}
			
			
			// Initialize related company
			Company company =  new Company(results.getInt("company.id"),results.getString("company.name"));
			// Initialize computer
			Computer computer =  new Computer(computerId,computerName,company,discontinued,introduced);
			
			computerList.add(computer);
		}

		return computerList;
	}

	/**
	 * Map a Computer to a ComputerDTO
	 * @param computer Computer to map
	 * @return related ComputerDTO
	 */
	public static ComputerDTO toComputerDTO(Computer computer){
		
		int id = computer.getId();
		String computerName = computer.getName();
		String introStr = DateFormatManager.toHTMLDateString(computer.getIntroduced());
		String discStr = DateFormatManager.toHTMLDateString(computer.getDiscontinued());
		CompanyDTO companyDTO = CompanyMapper.toCompanyDTO(computer.getCompany());
		
		return new ComputerDTO(id,computerName,introStr,discStr, companyDTO);
	}
	
	/**
	 * Map a list of Computer to ComputerDTOs
	 * @param computers list of Computer
	 * @return list of ComputerDTOs
	 */
	public static List<ComputerDTO> toComputerDTOList(List<Computer> computers){	
		List<ComputerDTO> computerDTOs = new ArrayList<ComputerDTO>();
		computers.parallelStream().forEachOrdered(c -> computerDTOs.add(toComputerDTO(c)));
		return computerDTOs;
	}
	
	/**
	 * Map a ComputerDTO to a Computer
	 * @param computerDTO ComputerDTO to map
	 * @return related Computer
	 */
	public static Computer toComputer(ComputerDTO computerDTO){		
		int id = computerDTO.getId();
		String computerName = computerDTO.getName();
		LocalDateTime intro = DateFormatManager.parseHTMLDateString(computerDTO.getIntroduced());
		LocalDateTime disc = DateFormatManager.parseHTMLDateString(computerDTO.getDiscontinued());
		Company company = CompanyMapper.toCompany(computerDTO.getCompany());
		return new Computer(id, computerName, company, disc, intro);
	}
}
