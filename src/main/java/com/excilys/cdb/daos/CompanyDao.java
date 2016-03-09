package com.excilys.cdb.daos;

import com.excilys.cdb.models.Company;

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
     *             issue with db
     */
    List<Company> findAll();

    /**
     * Find a company stored in DB by its id.
     * 
     * @param id
     *            id of the company to find
     * @return company related to the id
     * @throws DaoException
     *             issue with db
     */
    Company findById(long id);

    /**
     * Find all company stored in DB by its name.
     *
     * @param name
     *            name of the company to find
     * @return companies related to the name
     * @throws DaoException
     *             issue with db
     */
    List<Company> findByName(String name);

    /**
     * Store passed company in the DB.
     *
     * @param company
     *            company to store in DB
     * @return return id of the company inserted
     * @throws DaoException
     *             issue with db
     */
    long insertCompany(Company company);

    /**
     * Delete a company by its id.
     *
     * @param id
     *            id of the company to delete
     * @throws DaoException
     *             issue with db
     */
    void deleteCompany(long id);

}
