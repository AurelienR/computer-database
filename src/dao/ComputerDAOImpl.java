package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Company;
import models.Computer;

public class ComputerDAOImpl implements ComputerDAO {
	
	private final String INTRO_COLUMN = "introduced";
	private final String DISC_COLUMN = "discontinued";	

	private final String FIND_ALL_QUERY ="SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
	private final String FIND_BYID_QUERY = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";
	private final String FIND_BYNAME_QUERY ="SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name=?";
	private final String INSERT_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	private final String UPDATE_QUERY = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	private final String DELETE_QUERY = "DELETE FROM computer WHERE id=?";
	
	private DAOFactory daoFactory;
	
	public ComputerDAOImpl(){};
	
	public ComputerDAOImpl(DAOFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Computer> findAll() throws DAOException {
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Computer> computerList = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_ALL_QUERY);
			results = ps.executeQuery();
			
			computerList = getComputersFromResults(results);
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
			ConnectionCloser.silentCloses(results, ps, con);
		}
		
		return computerList;
	}

	@Override
	public List<Computer> findById(int id) throws DAOException {
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Computer> computerList = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_BYID_QUERY);
			ps.setInt(1, id);
			results = ps.executeQuery();
			
			computerList = getComputersFromResults(results);
			
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
		
		return computerList;
	}

	@Override
	public List<Computer> findByName(String name) throws DAOException, IllegalArgumentException {
		if(name == null || name.isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		
		Connection con = null;
		ResultSet results = null;
		PreparedStatement ps = null;
		List<Computer> computerList = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(FIND_BYNAME_QUERY);
			ps.setString(1, name);
			results = ps.executeQuery();
			
			computerList = getComputersFromResults(results);
			
			con.commit();
			
		}catch (SQLException e) {
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
		
		return computerList;
	}

	@Override
	public void insertComputer(Computer computer) throws DAOException, IllegalArgumentException {
		if(computer.getName() == null || computer.getName().isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		if(computer.getIntroduced() == null) throw new IllegalArgumentException("Introduced timestamp of computer parameter must be not null");
		if(computer.getDiscontinued() != null && computer.getDiscontinued().before(computer.getIntroduced())) throw new IllegalArgumentException("Discontinued computer timestamp is set before Introduced computer timestamp");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(INSERT_QUERY);
			ps.setString(1,computer.getName());
			ps.setTimestamp(2,computer.getIntroduced());
			ps.setTimestamp(3, computer.getDiscontinued());
			ps.setInt(4, computer.getCompany().getId());			
			
			ps.executeUpdate();
			con.commit();		
			
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on insert method",e1);
			}
			throw new DAOException("Failed on insert method, SQLException",e);	
		}
		finally{
			ConnectionCloser.silentCloses(ps, con);
		}
	}

	@Override
	public void updateComputer(Computer computer) throws DAOException, IllegalArgumentException {
		if(computer.getName() == null || computer.getName().isEmpty()) throw new IllegalArgumentException("Name parameter must be not null or empty");
		if(computer.getDiscontinued() != null && computer.getIntroduced()!= null && computer.getIntroduced().after(computer.getDiscontinued()))throw new IllegalArgumentException("Discontinued computer timestamp is set before Introduced computer timestamp");
		else if(computer.getDiscontinued() != null && computer.getIntroduced() == null){
			for(Computer c: findById(computer.getId())){
				if(c.getIntroduced().after(computer.getDiscontinued())) throw new IllegalArgumentException("Inconsistency with DB, Discontinued timestamp is before introduced DB timestamp");	
			}
		}
		else if(computer.getIntroduced() != null && computer.getDiscontinued() == null){
			for(Computer c: findById(computer.getId())){
				if(c.getDiscontinued().before(computer.getIntroduced())) throw new IllegalArgumentException("Inconsistency with DB, Introduced timestamp is after discontinued DB timestamp");	
			}
		}

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(UPDATE_QUERY);
			ps.setString(1,computer.getName());
			ps.setTimestamp(2,computer.getIntroduced());
			ps.setTimestamp(3, computer.getDiscontinued());
			ps.setInt(4, computer.getCompany().getId());
			ps.setInt(5, computer.getId());	
			
			ps.executeUpdate();
			con.commit();		
			
		}catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on update method",e1);
			}
			throw new DAOException("Failed on update method, SQLException",e);	
		}
		finally{
			ConnectionCloser.silentCloses(ps, con);
		}
		
	}

	@Override
	public void deleteComputer(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = daoFactory.getConnection();
			con.setAutoCommit(false);
			
			ps = con.prepareStatement(DELETE_QUERY);
			ps.setInt(1,id);
			
			ps.executeUpdate();
			con.commit();		
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DAOException("Failed to Rollback on delete method",e1);
			}
			throw new DAOException("Failed on delete method, SQLException",e);	
		}
		finally{
			ConnectionCloser.silentCloses(ps, con);
		}
		
	}
	
	private List<Computer> getComputersFromResults(ResultSet results) throws SQLException{
		
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		
		while(results.next()){
			int computerId = results.getInt("computer.id");
			String computerName = results.getString("computer.name");
			Timestamp introduced = results.getTimestamp(INTRO_COLUMN);
			Timestamp discontinued = results.getTimestamp(DISC_COLUMN);
			
			Company company =  new Company(results.getInt("company.id"),results.getString("company.name"));
			Computer computer =  new Computer(computerId,computerName,company,discontinued,introduced);
			
			computerList.add(computer);
		}

		return computerList;
	}
}
