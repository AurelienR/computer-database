package com.excilys.cdb.daos.impl;

import com.excilys.cdb.daos.ComputerDao;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.mappers.ComputerRowMapper;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.validators.utils.CompanyValidator;
import com.excilys.cdb.validators.utils.ComputerValidator;
import com.excilys.cdb.validators.utils.QueryPageParameterValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

/**
 * Manage computer data operation with the Database
 * 
 * @author Aurélien.R
 *
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  private ComputerRowMapper computerRowMapper = new ComputerRowMapper();

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDaoImpl.class);

  // DB column names
  public static final String INTRO_COLUMN = "introduced";
  public static final String DISC_COLUMN = "discontinued";

  // SQL Queries
  private static final String COUNT_QUERY =
      "SELECT COUNT(*) FROM computer" + " LEFT JOIN company ON computer.company_id = company.id "
          + " WHERE (computer.name LIKE ? OR company.name LIKE ? )";
  private static final String FIND_ALL_QUERY =
      "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
  private static final String FIND_BY_QUERY_PARAM_QUERY =
      "SELECT * FROM computer " + "LEFT JOIN company ON computer.company_id = company.id "
          + " WHERE (computer.name LIKE ? OR company.name LIKE ? ) ORDER BY %s %s LIMIT ? OFFSET ?";
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

  // Methods
  @Override
  public List<Computer> findAll() {

    LOGGER.debug("Dao: Find all computers");
    List<Computer> computerList = null;

    // Retrieve and map all computers
    try {
      computerList = jdbcTemplate.query(FIND_ALL_QUERY, computerRowMapper);
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to find all computer");
      throw new DaoException("Failed to find all computers", e);
    }

    return computerList;
  }

  @Override
  public Computer findById(long id) {

    LOGGER.debug("Dao: find computer by its id: " + id);

    // Check
    ComputerValidator.checkValidId(id);

    List<Computer> computerList = null;

    // Retrieve and map matching
    try {
      computerList = jdbcTemplate.query(FIND_BYID_QUERY, computerRowMapper, id);
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to find computer with id: {}", id);
      throw new DaoException("Failed to find computer with id " + id, e);
    }

    return computerList.get(0);
  }

  @Override
  public List<Computer> findByName(String name) {

    LOGGER.debug("Dao: find computer by name:" + name);

    // Check
    ComputerValidator.checkNameNotNull(name);
    ComputerValidator.checkNameNotEmpty(name);

    List<Computer> computerList = null;

    // Retrieve and map matching computers
    try {
      computerList = jdbcTemplate.query(FIND_BYNAME_QUERY, computerRowMapper, name);
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to find computer by name: {}", name);
      throw new DaoException("Failed to find computer by name: " + name, e);
    }

    return computerList;
  }

  @Override
  public long insertComputer(Computer computer) {

    LOGGER.debug("Dao: insert a computer: " + computer);

    // Check
    ComputerValidator.validate(computer);

    KeyHolder holder = new GeneratedKeyHolder();

    // Insert computer with custom prepared statement
    try {
      jdbcTemplate.update(new PreparedStatementCreator() {

        @Override
        public PreparedStatement createPreparedStatement(Connection connection)
            throws SQLException {
          PreparedStatement ps =
              connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);

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
            ps.setLong(4, computer.getCompany().getId());
          }

          return ps;
        }
      }, holder);
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to insert following computer: {}", computer);
      throw new DaoException("Failed to insert following computer: " + computer, e);
    }
    return holder.getKey().longValue();
  }

  @Override
  public void updateComputer(Computer computer) {

    LOGGER.debug("Dao: update computer with computer: " + computer);

    // Check
    ComputerValidator.validate(computer);

    // Update computer with custom prepared statement
    try {
      jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection connection)
            throws SQLException {
          PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
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
            ps.setLong(4, computer.getCompany().getId());
          }

          ps.setLong(5, computer.getId());

          return ps;
        }
      });
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to update following computer: {}", computer);
      throw new DaoException("Failed to update following computer: " + computer, e);
    }
  }

  @Override
  public void deleteComputer(long id) {

    LOGGER.debug("Dao: delete computer by id:" + id);

    // Check
    ComputerValidator.checkValidId(id);

    // Delete computers with matching id
    try {
      jdbcTemplate.update(DELETE_QUERY, id);
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to delete computer by id: {}", id);
      throw new DaoException("Failed to delete computer by id: " + id, e);
    }
  }

  @Override
  public long count(QueryPageParameter qp) {

    LOGGER.debug("Dao: get count of total computers");
    int count = 0;
    // Count matching computers
    try {
      count = jdbcTemplate.queryForObject(COUNT_QUERY, Integer.class, qp.getQuerySearch(),
          qp.getQuerySearch());
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to count computers with qp: {}", qp);
      throw new DaoException("Failed to count computers with qp: " + qp, e);
    }

    return count;
  }

  @Override
  public List<Computer> findByQuery(QueryPageParameter qp) {

    LOGGER.debug("Dao: find computer by query" + qp);

    // Check
    QueryPageParameterValidator.validate(qp);

    // Init local variables
    List<Computer> computerList = null;

    // Prepare query
    String query = String.format(FIND_BY_QUERY_PARAM_QUERY, qp.getOrderBy().toString(),
        qp.getOrder().toString());

    // Retrieve matching computers
    try {
      computerList = jdbcTemplate.query(query, computerRowMapper, qp.getQuerySearch(),
          qp.getQuerySearch(), qp.getLimit(), qp.getOffset());
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to find computers with qp: {}", qp);
      throw new DaoException("Failed to find computers with qp: " + qp, e);
    }

    return computerList;
  }

  @Override
  public void deleteByCompanyId(long companyId) {

    LOGGER.debug("Dao: delete computers by company id: " + companyId);

    // Check
    CompanyValidator.checkValidId(companyId);

    // Delete computers
    try {
      jdbcTemplate.update(DELETE_BYCOMPANY_QUERY, companyId);
    } catch (DataAccessException e) {
      LOGGER.error("Dao: Failed to delete computers with companyid: {}", companyId);
      throw new DaoException("Failed to delete computer with companyid: " + companyId, e);
    }
  }
}
