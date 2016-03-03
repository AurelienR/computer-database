package com.excilys.cdb.daos;

import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.QueryPageParameter;

import java.util.List;

/**
 * Interface for managing Computer data with the database
 * 
 * @author Aurelien.R
 *
 */
public interface ComputerDao {

  /**
   * Return list of computer based on QueryParameters.
   * 
   * @param queryParameter
   *          queryParameters to based the request on
   * @return related List of computers
   * @throws DaoException
   *           issue on db
   */
  List<Computer> findByQuery(QueryPageParameter queryParameter);

  /**
   * Count computer stored in DB.
   * 
   * @return number of computer
   * @throws DaoException
   *           issue on db
   */
  int count();

  /**
   * Find all computer stored in DB.
   * 
   * @return A list of all computer found
   * @throws DaoException
   *           issue on db
   */
  List<Computer> findAll();

  /**
   * Retrieve a range of computers.
   * 
   * @param startRow
   *          starting row of the range
   * @param size
   *          size of the range
   * @return related list of computers
   * @throws DaoException
   *           issue on db
   */
  List<Computer> findRange(int startRow, int size);

  /**
   * Find a computer stored in DB by its id.
   * 
   * @param id
   *          id of the computer to find
   * @return computers related to the id
   * @throws DaoException
   *           issue on db
   */
  List<Computer> findById(int id);

  /**
   * Find all computer stored in DB by its name.
   * 
   * @param name
   *          name of the computer to find
   * @return computers related to the name
   * @throws DaoException
   *           issue on db
   */
  List<Computer> findByName(String name);

  /**
   * Store passed computer in the DB.
   * 
   * @param computer
   *          computer to store in DB
   * @return return the id of the computer inserted
   * @throws DaoException
   *           issue on db
   */
  int insertComputer(Computer computer) throws DaoException;

  /**
   * Update passed computer in the DB (find computer by its id).
   * 
   * @param computer
   *          with the new field to change (keep id ref to update)
   * @throws DaoException
   *           issue on db
   */
  void updateComputer(Computer computer) throws DaoException;

  /**
   * Delete a computer by it's id.
   * 
   * @param id
   *          id of the computer to delete
   * @throws DaoException
   *           issue on db
   */
  void deleteComputer(int id) throws DaoException;

  /**
   * Delete the computers by there company id.
   * 
   * @param companyId
   *          companyId to reset on computers
   * @throws DaoException
   *           issue on db
   */
  void deleteByCompanyId(int companyId) throws DaoException;
}
