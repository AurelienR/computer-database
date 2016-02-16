package com.excilys.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.daos.impl.CompanyDAOImpl;
import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.models.Company;

public class CompanyMapper {
	
	/**
	 * Deserialize resultSet to list of companies
	 * @param results results set to process
	 * @return list of related companies
	 * @throws SQLException
	 */
	public static List<Company> getCompaniesFromResults(ResultSet results) throws SQLException{
		
		ArrayList<Company> companyList = new ArrayList<Company>();

		while(results.next()){
			companyList.add(new Company(results.getInt(CompanyDAOImpl.ID_COLUMN),results.getString(CompanyDAOImpl.NAME_COLUMN)));
		}
		
		return companyList;
	}
	
	/**
	 * Map a Company to a CompanyDTO
	 * @param company Company to map
	 * @return related CompanyDTO
	 */
	public static CompanyDTO toCompanyDTO(Company company){
		
		if(company == null){
			return null;
		}
		
		return new CompanyDTO(company.getId(),company.getName());
	}
	
	/**
	 * Map a CompanyDTO to a Company
	 * @param companyDTO Company to map
	 * @return related CompanyDTO
	 */
	public static Company toCompany(CompanyDTO companyDTO){
		if(companyDTO == null){
			return null;
		}
		return new Company(companyDTO.getId(),companyDTO.getName());
	}
	
	/**
	 * Map a list of Company to a list of CompanyDTO
	 * @param companies List of Company to map
	 * @return related List of CompanyDTO
	 */
	public static List<CompanyDTO> toCompanyDTOList(List<Company> companies){		
		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		companies.parallelStream().forEachOrdered(c -> companyDTOs.add(toCompanyDTO(c)));
		return companyDTOs;
	}
}
