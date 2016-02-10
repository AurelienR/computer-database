package com.excilys.cdb.services;

import java.util.List;

import com.excilys.cdb.dao.impl.CompanyDAOImpl;
import com.excilys.cdb.models.Company;

public class CompanyService {
	// Singleton
	private static CompanyService instance;

	// Constructors
	private CompanyService() {
	};

	// Methods
	public static CompanyService getInstance() {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}

	public List<Company> findAll() {
		return CompanyDAOImpl.getInstance().findAll();
	}

	public List<Company> findById(int id) {
		return CompanyDAOImpl.getInstance().findById(id);
	}

	public List<Company> findByName(String name) {
		return CompanyDAOImpl.getInstance().findByName(name);
	}

	public void createCompany(Company company) {
		validateCompanyName(company);
		CompanyDAOImpl.getInstance().insertCompany(company);
	}

	private void validateCompanyName(Company Company) throws IllegalArgumentException {
		if (Company.getName() == null || Company.getName().isEmpty()) {
			throw new IllegalArgumentException("Company name should be not null or empty");
		}
	}
}
