package com.excilys.cdb.daos;

import com.excilys.cdb.models.Company;

import java.sql.Connection;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface CompanyDao.
 */
public interface CompanyDao {

  /**
   * Find all company stored in DB.
   * 
   * @return A list of all company found
   * @throws DaoException
   *           issue with db
   */
  List<Company> findAll() throws DaoException;

  /**
   * Retrieve a range of company from db.
   * 
   * @param startRow
   *          starting row of the range
   * @param size
   *          size of the range
   * @return related List of company
   * @throws DaoException
   *           issue with db
   */
  public List<Company> findRange(int startRow, int size) throws DaoException;

  /**
   * Find a company stored in DB by its id.
   * 
   * @param id
   *          id of the company to find
   * @return companies related to the id
   * @throws DaoException
   *           issue with db
   */
  List<Company> findById(int id) throws DaoException;

  /**
   * Find all company stored in DB by its name.
   *
   * @param name          name of the company to find
   * @return companies related to the name
   * @throws DaoException           issue with db
   */
  List<Company> findByName(String name) throws DaoException;

  /**
   * Store passed company in the DB.
   *
   * @param company          company to store in DB
   * @return return id of the company inserted
   * @throws DaoException           issue with db
   */
  int insertCompany(Company company) throws DaoException;

  /**
   * Delete a company by its id.
   *
   * @param con the con
   * @param id          id of the company to delete
   * @throws DaoException           issue with db
   */
  void deleteCompany(Connection con, int id) throws DaoException;

}
