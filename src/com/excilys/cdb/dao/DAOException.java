package com.excilys.cdb.dao;


/**
 * Custom exception for DAO database access
 * @author Aurelien.R
 *
 */
public class DAOException extends RuntimeException {

    public DAOException( String message ) {
        super( message );
    }
    
    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }


    public DAOException( Throwable cause ) {
        super( cause );
    }
}