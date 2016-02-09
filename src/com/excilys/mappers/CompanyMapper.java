package com.excilys.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.dao.impl.CompanyDAOImpl;
import com.excilys.models.Company;

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
}
