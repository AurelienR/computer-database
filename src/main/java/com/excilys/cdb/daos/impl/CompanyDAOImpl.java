package com.excilys.cdb.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.excilys.cdb.daos.CompanyDAO;
import com.excilys.cdb.daos.ConnectionCloser;
import com.excilys.cdb.daos.ConnectionFactory;
import com.excilys.cdb.daos.DAOException;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;

public class CompanyDAOImpl implements CompanyDAO {
	
	// DB column name
	public static final String NAME_COLUMN = "name";
	public static final String ID_COLUMN = "id";
	
	// SQL Queries
	private final String FIND_ALL_QUERY = "SELECT * FROM company";
	private final String FIND_RANGE_QUERY = "SELECT * FROM company ORDER BY id DESC LIMIT ? OFFSET ?";
	private final String FIND_BYID_QUERY = "SELECT * FROM company WHERE id=?";
	private final String FIND_BYNAME_QUERY = "SELECT * FROM company WHERE name=?";
	private final String INSERT_QUERY = "INSERT INTO company (name) VALUES (?)";
	private final String DELETE_QUERY = "DELETE FROM company WHERE id=?";
	
	// Singleton
	private static CompanyDAO instance;

	// Constructors
	private CompanyDAOImpl(){};
	
	// Methods
	/**
	 * CompanyDAO singleton
	 * @return unique instance access
	 */
	public static CompanyDAO getInstance() {
		if(instance == null){
			instance = new CompanyDAOImpl();
		}
		return instance;
	}	
	
	@Override
	public List<Company> findAll() throws DAOException {
		
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Company> companyList = null;
		
		try {
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_ALL_QUERY);
			results = ps.executeQuery();
			
			companyList = CompanyMapper.getCompaniesFromResults(results);
			
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
		
		// Init local variables
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Company> companyList = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(FIND_BYID_QUERY);
			// Replace query fields
			ps.setString(1,Integer.toString(id));
			results = ps.executeQuery();
			
			// Deserialize resultSet to a list of company
			companyList = CompanyMapper.getCompaniesFromResults(results);
			
			con.commit();
			
		} catch (SQLException e) {
			// Try to recover previous DB state
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on findById method",e1);
			}
			throw new DAOException("Failed on findById method, SQLException",e);	
		}
		finally{
			// Close any connection related object
			ConnectionCloser.silentCloses(results, ps, con);
		}
		
		return companyList;
	}
	@Override
	public List<Company> findByName(String name) throws DAOException, IllegalArgumentException {
		
		// Parameter validation
		// Check name field
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		
		// Init local variables
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Company> companyList = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(FIND_BYNAME_QUERY);
			// Replace query fields
			ps.setString(1,name);
			results = ps.executeQuery();
			
			// Close any connection related object
			companyList = CompanyMapper.getCompaniesFromResults(results);
			
			con.commit();
			
		} catch (SQLException e) {
			// Try to recover previous DB state
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on findByName method",e1);
			}
			throw new DAOException("Failed on findByName method, SQLException",e);	
		}
		finally{
			// Close any connection related object
			ConnectionCloser.silentCloses(results, ps, con);
		}
		
		return companyList;
	}
	@Override
	public int insertCompany(Company company) throws DAOException, IllegalArgumentException {
		
		// Parameter validation
		// Check name field
		if(company.getName() == null || company.getName().isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		
		// Get opened connection
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet results = null;
		int id = -1;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(INSERT_QUERY,Statement.RETURN_GENERATED_KEYS);
			// Replace query fields
			ps.setString(1,company.getName());
			
			ps.executeUpdate();
			results= ps.getGeneratedKeys();
			if(results.next()){
				id = results.getInt(1);
			}
			con.commit();		
			
		}catch (SQLException e) {
			// Try to recover previous DB state
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on insertCompany method",e1);
			}
			throw new DAOException("Failed on insertCompany method, SQLException",e);	
		}
		finally{
			// Close any connection related object
			ConnectionCloser.silentCloses(ps, con);
		}
		
		return id;
	}
	
	@Override
	public List<Company> findRange(int startRow, int size) throws DAOException {
		
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Company> companyList = null;
		
		try {
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_RANGE_QUERY);
			ps.setInt(1,size);
			ps.setInt(2, startRow);
			results = ps.executeQuery();
			
			companyList = CompanyMapper.getCompaniesFromResults(results);
			
			con.commit();
			
		} catch (SQLException e) {			
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on findAll method",e1);	
			}			
			throw new DAOException("Failed on findRange method, SQLException",e);			
		}
		finally{
			ConnectionCloser.silentCloses(results,ps,con);
		}
		
		return companyList;
	}

	@Override
	public void deleteCompany(Connection con, int id) throws DAOException {
		
		PreparedStatement ps = null;		
		
		try {			
			// Prepare query
			ps = con.prepareStatement(DELETE_QUERY);
			
			// Replace query fields
			ps.setInt(1,id);
			
			// Execute query
			ps.executeUpdate();

		}  catch (SQLException e) {
			throw new DAOException("Failed to delete company");
		}
		finally {
			ConnectionCloser.silentClose(ps);
		}
	}
	
	
	
}
