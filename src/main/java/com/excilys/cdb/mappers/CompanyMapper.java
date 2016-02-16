package com.excilys.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dao.impl.CompanyDAOImpl;
import com.excilys.cdb.dtos.CompanyDTO;
import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;

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
	
	public static CompanyDTO toCompanyDTO(Company company){
		return new CompanyDTO(company.getId(),company.getName());
	}
	
	public static Company toCompany(CompanyDTO companyDTO){
		return new Company(companyDTO.getId(),companyDTO.getName());
	}
	
	public static List<CompanyDTO> toCompanyDTOList(List<Company> companies){		
		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		companies.parallelStream().forEachOrdered(c -> companyDTOs.add(toCompanyDTO(c)));
		return companyDTOs;
	}
}
