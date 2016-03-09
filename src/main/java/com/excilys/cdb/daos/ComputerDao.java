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
   * @param qp queryparameter related to the search to get the count from
   * @return number of computer
   * @throws DaoException
   *           issue on db
   */
  long count(QueryPageParameter qp);

  /**
   * Find all computer stored in DB.
   * 
   * @return A list of all computer found
   * @throws DaoException
   *           issue on db
   */
  List<Computer> findAll();


  /**
   * Find a computer stored in DB by its id.
   * 
   * @param id
   *          id of the computer to find
   * @return computer related to the id
   * @throws DaoException
   *           issue on db
   */
  Computer findById(long id);

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
  long insertComputer(Computer computer);

  /**
   * Update passed computer in the DB (find computer by its id).
   * 
   * @param computer
   *          with the new field to change (keep id ref to update)
   * @throws DaoException
   *           issue on db
   */
  void updateComputer(Computer computer);

  /**
   * Delete a computer by it's id.
   * 
   * @param id
   *          id of the computer to delete
   * @throws DaoException
   *           issue on db
   */
  void deleteComputer(long id);

  /**
   * Delete the computers by there company id.
   * 
   * @param companyId
   *          companyId to reset on computers
   * @throws DaoException
   *           issue on db
   */
  void deleteByCompanyId(long companyId);
}
