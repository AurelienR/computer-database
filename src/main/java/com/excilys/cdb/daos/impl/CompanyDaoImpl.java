package com.excilys.cdb.daos.impl;

import com.excilys.cdb.daos.CompanyDao;
import com.excilys.cdb.daos.ConnectionCloser;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.daos.TransactionManager;
import com.excilys.cdb.mappers.CompanyMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.CompanyValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

  // DB column name
  public static final String NAME_COLUMN = "name";
  public static final String ID_COLUMN = "id";

  // SQL Queries
  private static final String FIND_ALL_QUERY = "SELECT * FROM company";
  private static final String FIND_RANGE_QUERY =
      "SELECT * FROM company ORDER BY id DESC LIMIT ? OFFSET ?";
  private static final String FIND_BYID_QUERY = "SELECT * FROM company WHERE id=?";
  private static final String FIND_BYNAME_QUERY = "SELECT * FROM company WHERE name=?";
  private static final String INSERT_QUERY = "INSERT INTO company (name) VALUES (?)";
  private static final String DELETE_QUERY = "DELETE FROM company WHERE id=?";

  // Singleton
  private static CompanyDao instance;

  // Constructors
  private CompanyDaoImpl() {
  }

  // Methods
  /**
   * CompanyDAO singleton.
   * 
   * @return unique instance access
   */
  public static CompanyDao getInstance() {
    if (instance == null) {
      instance = new CompanyDaoImpl();
    }
    return instance;
  }

  @Override
  public List<Company> findAll() throws DaoException {

    logger.debug("Dao: Find all companies");

    // Local variable declaration
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    ResultSet results = null;
    PreparedStatement ps = null;
    List<Company> companyList = null;

    try {
      // Get connection
      con = tm.getConnection();

      // Query
      ps = con.prepareStatement(FIND_ALL_QUERY);
      results = ps.executeQuery();

      // Map
      companyList = CompanyMapper.getCompaniesFromResults(results);

    } catch (SQLException e) {
      logger.debug("Failed to find all companies, SQLException");
      throw new DaoException("Failed on findAll method, SQLException", e);
    } finally {
      ConnectionCloser.silentCloses(results, ps);
      tm.close();
    }

    return companyList;
  }

  @Override
  public List<Company> findById(int id) throws DaoException {

    logger.debug("Dao: Find company by its id:" + id);

    // Check
    CompanyValidator.checkValidId(id);

    // Init local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    ResultSet results = null;
    PreparedStatement ps = null;
    List<Company> companyList = null;

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.prepareStatement(FIND_BYID_QUERY);
      // Replace query fields
      ps.setString(1, Integer.toString(id));
      results = ps.executeQuery();

      // Deserialize resultSet to a list of company
      companyList = CompanyMapper.getCompaniesFromResults(results);

    } catch (SQLException e) {
      logger.debug("Failed to find a company by its id, SQLException, id" + id);
      throw new DaoException("Failed on findById method, SQLException", e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentCloses(results, ps);
      tm.close();
    }

    return companyList;
  }

  @Override
  public List<Company> findByName(String name) throws DaoException {

    logger.debug("Dao: Find company by its name : " + name);

    // Check
    CompanyValidator.checkNameNotNull(name);
    CompanyValidator.checkNameNotEmpty(name);
    
    // Init local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    ResultSet results = null;
    PreparedStatement ps = null;
    List<Company> companyList = null;

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.prepareStatement(FIND_BYNAME_QUERY);
      // Replace query fields
      ps.setString(1, name);
      results = ps.executeQuery();

      // Close any connection related object
      companyList = CompanyMapper.getCompaniesFromResults(results);

    } catch (SQLException e) {
      logger.debug("Failed to find a company by its name, SQLException, name: " + name);
      throw new DaoException("Failed on findByName method, SQLException", e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentCloses(results, ps);
      tm.close();
    }

    return companyList;
  }

  @Override
  public int insertCompany(Company company) throws DaoException, IllegalArgumentException {

    logger.debug("Dao: insert company: " + company);

    // Check
    CompanyValidator.validate(company);
    
    // Get opened connection
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet results = null;
    int id = -1;

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
      // Replace query fields
      ps.setString(1, company.getName());

      ps.executeUpdate();
      results = ps.getGeneratedKeys();
      if (results.next()) {
        id = results.getInt(1);
      }

    } catch (SQLException e) {
      logger.debug("Failed to insert company in db, SLQException, company:" + company);
      throw new DaoException("Failed on insertCompany method, SQLException", e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentClose(ps);
      tm.close();
    }

    return id;
  }

  @Override
  public List<Company> findRange(int startRow, int size) throws DaoException {

    logger.debug("Dao: Find range of companies, startingRow:" + startRow + ", size:" + size);

    // Check
    CompanyValidator.checkStartingRow(startRow);
    CompanyValidator.checkSize(size);
    
    // Initialize local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    ResultSet results = null;
    PreparedStatement ps = null;
    List<Company> companyList = null;

    try {
      // Get connection
      con = tm.getConnection();

      // Query
      ps = con.prepareStatement(FIND_RANGE_QUERY);
      ps.setInt(1, size);
      ps.setInt(2, startRow);
      results = ps.executeQuery();

      // Map
      companyList = CompanyMapper.getCompaniesFromResults(results);

    } catch (SQLException e) {
      logger.debug("Failed to find a range of computers,SQLException,startingRow:" + startRow
          + ", size:" + size);
      throw new DaoException("Failed on findRange method, SQLException", e);
    } finally {
      ConnectionCloser.silentCloses(results, ps);
      tm.close();
    }

    return companyList;
  }

  @Override
  public void deleteCompany(int id) throws DaoException {

    logger.debug("Dao: delete company by id:" + id);

    // Check
    CompanyValidator.checkValidId(id);
    
    // Local variable declaration
    PreparedStatement ps = null;
    TransactionManager tm = TransactionManager.getInstance();

    try {
      // Retrieve connection
      Connection con = tm.getConnection();
      
      // Prepare query
      ps = con.prepareStatement(DELETE_QUERY);

      // Replace query fields
      ps.setInt(1, id);

      // Execute query
      ps.executeUpdate();

    } catch (SQLException e) {
      logger.debug("Failed to delete company by id : " + id);
      throw new DaoException("Failed to delete company by id : " + id, e);
    } finally {
      ConnectionCloser.silentClose(ps);
      tm.close();
    }
  }

}
