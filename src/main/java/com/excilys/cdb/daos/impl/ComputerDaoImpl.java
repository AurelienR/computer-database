package com.excilys.cdb.daos.impl;

import com.excilys.cdb.daos.ComputerDao;
import com.excilys.cdb.daos.ConnectionCloser;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.daos.TransactionManager;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.mappers.ComputerRowMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.CompanyValidator;
import com.excilys.cdb.validators.ComputerValidator;
import com.excilys.cdb.validators.QueryPageParameterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

/**
 * Manage computer data operation with the Database
 * 
 * @author Aurélien.R
 *
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

  private DataSource dataSource;
  private JdbcTemplate jdbcTemplate;
  private ComputerRowMapper computerRowMapper = new ComputerRowMapper();

  @Autowired
  private void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  // Logger
  static final Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

  // DB column names
  public static final String INTRO_COLUMN = "introduced";
  public static final String DISC_COLUMN = "discontinued";

  // SQL Queries
  private static final String COUNT_QUERY = "SELECT COUNT(*) FROM computer";
  private static final String FIND_ALL_QUERY =
      "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
  private static final String FIND_RANGE_QUERY =
      "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id "
          + "ORDER BY computer.id LIMIT ? OFFSET ?";
  private static final String FIND_BY_QUERY_PARAM_QUERY =
      "SELECT SQL_CALC_FOUND_ROWS * FROM computer "
          + "LEFT JOIN company ON computer.company_id = company.id "
          + " WHERE (computer.name LIKE ? OR company.name LIKE ? ) ORDER BY %s %s LIMIT ? OFFSET ?";
  private static final String COUNT_MATCHING_ROWS_QUERY = "SELECT FOUND_ROWS()";
  private static final String FIND_BYID_QUERY = "SELECT * FROM computer "
      + "LEFT JOIN company ON computer.company_id = company.id WHERE computer.id=?";
  private static final String FIND_BYNAME_QUERY = "SELECT * FROM computer "
      + "LEFT JOIN company ON computer.company_id = company.id WHERE computer.name=?";
  private static final String INSERT_QUERY =
      "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
  private static final String UPDATE_QUERY =
      "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
  private static final String DELETE_QUERY = "DELETE FROM computer WHERE id=?";
  private static final String DELETE_BYCOMPANY_QUERY = "DELETE FROM computer WHERE company_id=?";

  // Constructors
  private ComputerDaoImpl() {
  }

  // Methods
  @Override
  public List<Computer> findAll() throws DaoException {

    logger.debug("Dao: Find all computers");
    List<Computer> computerList = null;

    // Retrieve and map datas
    computerList = jdbcTemplate.query(FIND_ALL_QUERY, computerRowMapper);

    return computerList;
  }

  @Override
  public List<Computer> findById(int id) throws DaoException {

    logger.debug("Dao: find computer by its id: " + id);

    // Check
    ComputerValidator.checkValidId(id);

    List<Computer> computerList = null;

    // Retrieve and map datas
    computerList = jdbcTemplate.query(FIND_BYID_QUERY, computerRowMapper, id);

    return computerList;
  }

  @Override
  public List<Computer> findByName(String name) throws DaoException, IllegalArgumentException {

    logger.debug("Dao: find computer by name:" + name);

    // Check
    ComputerValidator.checkNameNotNull(name);
    ComputerValidator.checkNameNotEmpty(name);

    List<Computer> computerList = null;

    // Retrieve and map datas
    computerList = jdbcTemplate.query(FIND_BYNAME_QUERY, computerRowMapper, name);

    return computerList;
  }

  @Override
  public int insertComputer(Computer computer) throws DaoException, IllegalArgumentException {

    logger.debug("Dao: insert a computer: " + computer);

    // Check
    ComputerValidator.validate(computer);

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
      // Replace query fields
      ps.setString(1, computer.getName());

      if (computer.getIntroduced() == null) {
        ps.setTimestamp(2, null);
      } else {
        ps.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
      }
      if (computer.getDiscontinued() == null) {
        ps.setTimestamp(3, null);
      } else {
        ps.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
      }

      if (computer.getCompany() == null) {
        ps.setNull(4, java.sql.Types.BIGINT);
      } else {
        ps.setInt(4, computer.getCompany().getId());
      }

      ps.executeUpdate();
      results = ps.getGeneratedKeys();
      if (results.next()) {
        id = results.getInt(1);
      }

    } catch (SQLException e) {
      logger.debug("Failed to insert computer, SQLException, computer:" + computer);
      throw new DaoException("Failed on insert method, SQLException", e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentClose(ps);
      tm.close();
    }

    return id;
  }

  @Override
  public void updateComputer(Computer computer) throws DaoException, IllegalArgumentException {

    logger.debug("Dao: update computer with computer: " + computer);

    // Check
    ComputerValidator.validate(computer);

    // Init local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    PreparedStatement ps = null;

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.prepareStatement(UPDATE_QUERY);
      // Replace query fields
      ps.setString(1, computer.getName());
      if (computer.getIntroduced() == null) {
        ps.setTimestamp(2, null);
      } else {
        ps.setTimestamp(2, Timestamp.valueOf(computer.getIntroduced()));
      }
      if (computer.getDiscontinued() == null) {
        ps.setTimestamp(3, null);
      } else {
        ps.setTimestamp(3, Timestamp.valueOf(computer.getDiscontinued()));
      }
      if (computer.getCompany() == null) {
        ps.setNull(4, java.sql.Types.BIGINT);
      } else {
        ps.setInt(4, computer.getCompany().getId());
      }

      ps.setInt(5, computer.getId());

      ps.executeUpdate();

    } catch (SQLException e) {
      logger.debug("Failed to update computer with computer: " + computer);
      throw new DaoException("Failed on update method, SQLException, computer:" + computer, e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentClose(ps);
      tm.close();
    }

  }

  @Override
  public void deleteComputer(int id) {

    logger.debug("Dao: delete computer by id:" + id);

    // Check
    ComputerValidator.checkValidId(id);

    // Init local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    PreparedStatement ps = null;

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.prepareStatement(DELETE_QUERY);
      // Replace query fields
      ps.setInt(1, id);

      ps.executeUpdate();

    } catch (SQLException e) {
      logger.debug("Failed on delete method, SQLException, id:" + id);
      throw new DaoException("Failed on delete method, SQLException, id:" + id, e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentCloses(ps, con);
      tm.close();
    }

  }

  @Override
  public List<Computer> findRange(int offset, int limit) throws DaoException {

    logger.debug("Dao: find computer by range, offset:" + offset + " limit:" + limit);

    // Check
    ComputerValidator.checkStartingRow(offset);
    ComputerValidator.checkSize(limit);

    // Init local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    ResultSet results = null;
    PreparedStatement ps = null;
    List<Computer> computerList = null;

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.prepareStatement(FIND_RANGE_QUERY);
      ps.setInt(1, limit);
      ps.setInt(2, offset);
      results = ps.executeQuery();

      // Deserialize resultSet to a list of computer
      computerList = ComputerMapper.toComputerList(results);

    } catch (SQLException e) {
      logger
          .debug("Failed on find range method, SQLException, offset:" + offset + "limit:" + limit);
      throw new DaoException("Failed on findRange method, SQLException", e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentCloses(results, ps);
      tm.close();
    }

    return computerList;
  }

  @Override
  public int count() throws DaoException {

    logger.debug("Dao: get count of total computers");

    // Init local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    ResultSet results = null;
    Statement ps = null;
    int count = -1;

    try {
      // Get opened connection
      con = tm.getConnection();

      // Prepare query
      ps = con.createStatement();
      results = ps.executeQuery(COUNT_QUERY);

      // Get count
      results.next();
      count = results.getInt(1);

    } catch (SQLException e) {
      logger.debug("Failed on count method, SQLException");
      throw new DaoException("Failed on count method, SQLException", e);
    } finally {
      // Close any connection related object
      ConnectionCloser.silentCloses(results, ps);
      tm.close();
    }

    return count;
  }

  @Override
  public List<Computer> findByQuery(QueryPageParameter qp) {

    logger.debug("Dao: find computer by query" + qp);

    // Check
    QueryPageParameterValidator.validate(qp);

    // Init local variables
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = null;
    ResultSet results = null;
    PreparedStatement ps = null;
    List<Computer> computerList = null;

    try {
      // Get opened connection
      // con = tm.getConnection();
      con = dataSource.getConnection();

      // Prepare query
      String query = String.format(FIND_BY_QUERY_PARAM_QUERY, qp.getOrderBy().toString(),
          qp.getOrder().toString());
      ps = con.prepareStatement(query);

      // Set fields
      ps.setString(1, qp.getQuerySearch());
      ps.setString(2, qp.getQuerySearch());
      ps.setInt(3, qp.getLimit());
      ps.setInt(4, qp.getOffset());
      results = ps.executeQuery();

      // Deserialize resultSet to a list of computer
      computerList = ComputerMapper.toComputerList(results);

      // Close connection elements
      ConnectionCloser.silentClose(results);
      ConnectionCloser.silentClose(ps);

      // Get matching rows
      ps = con.prepareStatement(COUNT_MATCHING_ROWS_QUERY);
      results = ps.executeQuery();

      // Set matching rows count in query parameter
      results.next();
      qp.setMatchingRowCount(results.getInt(1));

    } catch (SQLException e) {
      logger.debug("Failed to find computers by query method" + qp);
      throw new DaoException("Failed to find computers by query method" + qp, e);
    } finally {
      ConnectionCloser.silentCloses(results, ps);
      // tm.close();
      try {
        con.close();
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

    return computerList;
  }

  @Override
  public void deleteByCompanyId(int companyId) throws DaoException {

    logger.debug("Dao: delete computers by company id: " + companyId);

    // Check
    CompanyValidator.checkValidId(companyId);

    PreparedStatement ps = null;
    TransactionManager tm = TransactionManager.getInstance();
    Connection con = tm.getConnection();

    // Prepare query
    try {
      ps = con.prepareStatement(DELETE_BYCOMPANY_QUERY);
      ps.setInt(1, companyId);

      ps.executeUpdate();

    } catch (SQLException e) {
      logger.debug("Failed to delete computers by companyId" + companyId);
      throw new DaoException("Cannot reset following company id: " + companyId, e);
    } finally {
      ConnectionCloser.silentClose(ps);
      tm.close();
    }
  }
}
