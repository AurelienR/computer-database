package com.excilys.cdb.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cdb.daos.impl.CompanyDaoImpl;
import com.excilys.cdb.models.Company;

public class CompanyRowMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet arg0, int arg1) throws SQLException {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * Map single row based on the resultSet passed to a related company.
     *
     * @param rs one row result set to map
     * @return the company mapped
     * @throws SQLException the SQL exception
     */
    public static Company mapSingleRow(ResultSet rs) throws SQLException {
	return new Company(rs.getInt(CompanyDaoImpl.ID_COLUMN),
	          rs.getString(CompanyDaoImpl.NAME_COLUMN));
    }
}
