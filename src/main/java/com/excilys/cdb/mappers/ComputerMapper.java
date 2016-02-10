package com.excilys.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.impl.ComputerDAOImpl;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;

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
			int computerId = results.getInt("computer.id");
			String computerName = results.getString("computer.name");

			Timestamp introTimestamp = results.getTimestamp(ComputerDAOImpl.INTRO_COLUMN);
			LocalDateTime introduced;
			if(introTimestamp == null){
				introduced = null;
			}
			else{
				introduced = introTimestamp.toLocalDateTime();
			}
			
			
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
}
