package dao;
import java.util.List;
import models.Company;

public interface CompanyDAO {
	
	List<Company> findAll() throws DAOException;
	List<Company> findById(int id) throws DAOException;
	List<Company> findByName(String name) throws DAOException;
	void insertCompany(Company company) throws DAOException;

}
