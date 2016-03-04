package com.excilys.cdb.daos.impl;

import com.excilys.cdb.daos.ComputerDao;
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
  static final Logger logger = LoggerFactory.getLogger(ComputerDaoImpl.class);

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

  // Constructors
  private ComputerDaoImpl() {
  }

  // Methods
  @Override
  public List<Computer> findAll() {

    logger.debug("Dao: Find all computers");
    List<Computer> computerList = null;

    // Retrieve and map all computers
    computerList = jdbcTemplate.query(FIND_ALL_QUERY, computerRowMapper);

    return computerList;
  }

  @Override
  public List<Computer> findById(long id) {

    logger.debug("Dao: find computer by its id: " + id);

    // Check
    ComputerValidator.checkValidId(id);

    List<Computer> computerList = null;

    // Retrieve and map matching
    computerList = jdbcTemplate.query(FIND_BYID_QUERY, computerRowMapper, id);

    return computerList;
  }

  @Override
  public List<Computer> findByName(String name) {

    logger.debug("Dao: find computer by name:" + name);

    // Check
    ComputerValidator.checkNameNotNull(name);
    ComputerValidator.checkNameNotEmpty(name);

    List<Computer> computerList = null;

    // Retrieve and map matching computers
    computerList = jdbcTemplate.query(FIND_BYNAME_QUERY, computerRowMapper, name);

    return computerList;
  }

  @Override
  public long insertComputer(Computer computer) {

    logger.debug("Dao: insert a computer: " + computer);

    // Check
    ComputerValidator.validate(computer);

    KeyHolder holder = new GeneratedKeyHolder();

    // Insert computer with custom prepared statement
    jdbcTemplate.update(new PreparedStatementCreator() {

      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
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
    
    return holder.getKey().longValue();
  }

  @Override
  public void updateComputer(Computer computer) {

    logger.debug("Dao: update computer with computer: " + computer);

    // Check
    ComputerValidator.validate(computer);

    // Update computer with custom prepared statement
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
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
  }

  @Override
  public void deleteComputer(long id) {

    logger.debug("Dao: delete computer by id:" + id);

    // Check
    ComputerValidator.checkValidId(id);

    // Delete computers with matching id
    jdbcTemplate.update(DELETE_QUERY, id);
  }

  @Override
  public long count(QueryPageParameter qp) {

    logger.debug("Dao: get count of total computers");

    // Count matching computers
    int count = jdbcTemplate.queryForObject(COUNT_QUERY, Integer.class, qp.getQuerySearch(),
        qp.getQuerySearch());

    return count;
  }

  @Override
  public List<Computer> findByQuery(QueryPageParameter qp) {

    logger.debug("Dao: find computer by query" + qp);

    // Check
    QueryPageParameterValidator.validate(qp);

    // Init local variables
    List<Computer> computerList = null;

    // Prepare query
    String query = String.format(FIND_BY_QUERY_PARAM_QUERY, qp.getOrderBy().toString(),
        qp.getOrder().toString());

    // Retrieve matching computers
    computerList = jdbcTemplate.query(query, computerRowMapper, qp.getQuerySearch(),
        qp.getQuerySearch(), qp.getLimit(), qp.getOffset());

    return computerList;
  }

  @Override
  public void deleteByCompanyId(long companyId) {

    logger.debug("Dao: delete computers by company id: " + companyId);

    // Check
    CompanyValidator.checkValidId(companyId);

    // Delete computers
    jdbcTemplate.update(DELETE_BYCOMPANY_QUERY, companyId);
  }
}
