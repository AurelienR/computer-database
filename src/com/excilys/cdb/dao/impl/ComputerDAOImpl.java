package com.excilys.cdb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.excilys.cdb.dao.ComputerDAO;
import com.excilys.cdb.dao.ConnectionCloser;
import com.excilys.cdb.dao.ConnectionFactory;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Computer;

/**
 * Manage computer data operation with the Database
 * @author Aurélien.R
 *
 */
public class ComputerDAOImpl implements ComputerDAO {
	
	// DB column names
	public static final String INTRO_COLUMN = "introduced";
	public static final String DISC_COLUMN = "discontinued";	

	// SQL Queries
	private final String FIND_ALL_QUERY ="SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
	private final String FIND_BYID_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";
	private final String FIND_BYNAME_QUERY ="SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name=?";
	private final String INSERT_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private final String UPDATE_QUERY = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	private final String DELETE_QUERY = "DELETE FROM computer WHERE id=?";
	
	//Singleton
	private static ComputerDAO instance;
	
	// Constructors
	private ComputerDAOImpl(){};	

	// Methods
	/**
	 * ComputerDAO singleton
	 * @return unique instance access
	 */
	public static ComputerDAO getInstance() {
		if(instance == null){
			instance = new ComputerDAOImpl();
		}
		return instance;
	}
	
	@Override
	public List<Computer> findAll() throws DAOException {
		
		// Init local variables
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Computer> computerList = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(FIND_ALL_QUERY);
			results = ps.executeQuery();
			
			// Deserialize resultSet to a list of computer
			computerList = ComputerMapper.getComputersFromResults(results);
			con.commit();
			
		} catch (SQLException e) {
			// Try to recover previous DB state
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on findAll method",e1);
			}
			throw new DAOException("Failed on findAll method, SQLException",e);	
		}
		finally{
			// Close any connection related object
			ConnectionCloser.silentCloses(results, ps, con);
		}
		
		return computerList;
	}
	@Override
	public List<Computer> findById(int id) throws DAOException {
		
		// Init local variables
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Computer> computerList = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(FIND_BYID_QUERY);
			// Replace query fields
			ps.setInt(1, id);
			results = ps.executeQuery();
			
			// Deserialize resultSet to a list of computer
			computerList = ComputerMapper.getComputersFromResults(results);
			
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
		
		return computerList;
	}

	@Override
	public List<Computer> findByName(String name) throws DAOException, IllegalArgumentException {
		
		// Parameter validation
		// Check name field
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		
		// Init local variables
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Computer> computerList = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(FIND_BYNAME_QUERY);
			// Replace query fields
			ps.setString(1, name);
			results = ps.executeQuery();
			
			// Deserialize resultSet to a list of computer
			computerList = ComputerMapper.getComputersFromResults(results);
			
			con.commit();
			
		}catch (SQLException e) {
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
		
		return computerList;
	}

	@Override
	public void insertComputer(Computer computer) throws DAOException, IllegalArgumentException {
		// Parameter validation and DB consistency
		// Check name Field
		if(computer.getName() == null || computer.getName().isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		// Check introduced field
		if(computer.getIntroduced() == null) throw new IllegalArgumentException("Introduced timestamp of computer parameter must be not null");
		
		// Init local variables
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(INSERT_QUERY);
			// Replace query fields
			ps.setString(1,computer.getName());
			ps.setTimestamp(2,Timestamp.valueOf(computer.getIntroduced()));
			ps.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			ps.setInt(4, computer.getCompany().getId());
			ps.setInt(5, computer.getId());
			
			ps.executeUpdate();
			con.commit();		
			
		}catch (SQLException e) {
			// Try to recover previous DB state
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on insert method",e1);
			}
			throw new DAOException("Failed on insert method, SQLException",e);	
		}
		finally{
			// Close any connection related object
			ConnectionCloser.silentCloses(ps, con);
		}
	}

	@Override
	public void updateComputer(Computer computer) throws DAOException, IllegalArgumentException {

		// Init local variables
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(UPDATE_QUERY);
			// Replace query fields
			ps.setString(1,computer.getName());
			ps.setTimestamp(2,Timestamp.valueOf(computer.getIntroduced()));
			ps.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
			ps.setInt(4, computer.getCompany().getId());
			ps.setInt(5, computer.getId());	
			
			ps.executeUpdate();
			con.commit();		
			
		}catch (SQLException e) {
			// Try to recover previous DB state
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on update method",e1);
			}
			throw new DAOException("Failed on update method, SQLException",e);	
		}
		finally{
			// Close any connection related object
			ConnectionCloser.silentCloses(ps, con);
		}
		
	}

	@Override
	public void deleteComputer(int id) {
		// Init local variables
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			// Get opened connection
			con = ConnectionFactory.getInstance().getConnection();
			con.setAutoCommit(false);
			
			// Prepare query
			ps = con.prepareStatement(DELETE_QUERY);
			// Replace query fields
			ps.setInt(1,id);
			
			ps.executeUpdate();
			con.commit();		
			
		} catch (SQLException e) {
			// Try to recover previous DB state
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on delete method",e1);
			}
			throw new DAOException("Failed on delete method, SQLException",e);	
		}
		finally{
			// Close any connection related object
			ConnectionCloser.silentCloses(ps, con);
		}
		
	}




}
