package com.excilys.cdb.daos.impl;

import com.excilys.cdb.daos.CompanyDao;
import com.excilys.cdb.mappers.CompanyRowMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.validators.CompanyValidator;

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
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // Mapper
  private static final CompanyRowMapper companyRowMapper = new CompanyRowMapper();

  // Logger
  static final Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

  // DB column name
  public static final String NAME_COLUMN = "name";
  public static final String ID_COLUMN = "id";

  // SQL Queries
  private static final String FIND_ALL_QUERY = "SELECT * FROM company";
  private static final String FIND_BYID_QUERY = "SELECT * FROM company WHERE id=?";
  private static final String FIND_BYNAME_QUERY = "SELECT * FROM company WHERE name=?";
  private static final String INSERT_QUERY = "INSERT INTO company (name) VALUES (?)";
  private static final String DELETE_QUERY = "DELETE FROM company WHERE id=?";

  // Constructors
  private CompanyDaoImpl() {
  }

  // Methods
  @Override
  public List<Company> findAll() {
    logger.debug("Dao: Find all companies");

    // Local variable declaration
    List<Company> companyList = null;

    // Retrieve and map all companies
    companyList = jdbcTemplate.query(FIND_ALL_QUERY, companyRowMapper);

    return companyList;
  }

  @Override
  public List<Company> findById(int id) {

    logger.debug("Dao: Find company by its id:" + id);

    // Check
    CompanyValidator.checkValidId(id);

    // Init local variables
    List<Company> companyList;

    // Retrieve and map all matching companies by id
    companyList = jdbcTemplate.query(FIND_BYID_QUERY, companyRowMapper, id);

    return companyList;
  }

  @Override
  public List<Company> findByName(String name) {

    logger.debug("Dao: Find company by its name : " + name);

    // Check
    CompanyValidator.checkNameNotNull(name);
    CompanyValidator.checkNameNotEmpty(name);

    // Init local variables
    List<Company> companyList = null;

    // Retrieve and map all matching companies
    companyList = jdbcTemplate.query(FIND_BYNAME_QUERY, companyRowMapper, name);

    return companyList;
  }

  @Override
  public int insertCompany(Company company) {

    logger.debug("Dao: insert company: " + company);

    // Check
    CompanyValidator.validate(company);

    KeyHolder holder = new GeneratedKeyHolder();

    // Insert computer with custom prepared statement
    jdbcTemplate.update(new PreparedStatementCreator() {

      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement ps =
            connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, company.getName());
        return ps;
      }
    }, holder);

    int id = (int) holder.getKey().longValue();

    return id;
  }

  @Override
  public void deleteCompany(int id) {

    logger.debug("Dao: delete company by id:" + id);

    // Check
    CompanyValidator.checkValidId(id);

    // Delete company by its id
    jdbcTemplate.update(DELETE_QUERY, id);
  }

}
