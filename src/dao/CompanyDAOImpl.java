package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import models.Company;

public class CompanyDAOImpl implements CompanyDAO {
	
	private final String NAME_COLUMN = "name";
	private final String ID_COLUMN = "id";
	
	private final String FIND_ALL_QUERY = "SELECT * FROM company";
	private final String FIND_BYID_QUERY = "SELECT * FROM company WHERE id=?";
	private final String FIND_BYNAME_QUERY = "SELECT * FROM company WHERE name=?";
	private final String INSERT_QUERY = "INSERT INTO company (name) VALUES (?)";;
	
	private DAOFactory daoFactory;

	
	public CompanyDAOImpl(){};
	
	public CompanyDAOImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Company> findAll() throws DAOException {
		
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Company> companyList = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_ALL_QUERY);
			results = ps.executeQuery();
			
			companyList = getCompaniesFromResults(results);
			
			con.commit();
			
		} catch (SQLException e) {			
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on findAll method",e1);	
			}			
			throw new DAOException("Failed on findAll method, SQLException",e);			
		}
		finally{
			ConnectionCloser.silentCloses(results,ps,con);
		}
		
		return companyList;
	}

	@Override
	public List<Company> findById(int id) throws DAOException {		
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Company> companyList = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_BYID_QUERY);
			ps.setString(1,Integer.toString(id));
			results = ps.executeQuery();
			
			companyList = getCompaniesFromResults(results);
			
			con.commit();
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on findById method",e1);
			}
			throw new DAOException("Failed on findById method, SQLException",e);	
		}
		finally{
			ConnectionCloser.silentCloses(results, ps, con);
		}
		
		return companyList;
	}

	@Override
	public List<Company> findByName(String name) throws DAOException, IllegalArgumentException {
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Company> companyList = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_BYNAME_QUERY);
			ps.setString(1,name);
			results = ps.executeQuery();
			
			companyList = getCompaniesFromResults(results);
			
			con.commit();
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on findByName method",e1);
			}
			throw new DAOException("Failed on findByName method, SQLException",e);	
		}
		finally{
			ConnectionCloser.silentCloses(results, ps, con);
		}
		
		return companyList;
	}

	@Override
	public void insertCompany(Company company) throws DAOException, IllegalArgumentException {
		if(company.getName() == null || company.getName().isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(INSERT_QUERY);
			ps.setString(1,company.getName());
			
			ps.executeUpdate();
			con.commit();		
			
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on insertCompany method",e1);
			}
			throw new DAOException("Failed on insertCompany method, SQLException",e);	
		}
		finally{
			ConnectionCloser.silentCloses(ps, con);
		}
	}
	
	private List<Company> getCompaniesFromResults(ResultSet results) throws SQLException{
		
		ArrayList<Company> companyList = new ArrayList<Company>();

		while(results.next()){
			companyList.add(new Company(results.getInt(ID_COLUMN),results.getString(NAME_COLUMN)));
		}
		
		return companyList;
	}
}
