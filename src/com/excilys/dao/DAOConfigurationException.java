package com.excilys.dao;

/**
 * Custom exception for config and setup DB connnection
 * @author Aurelien.R
 *
 */
public class DAOConfigurationException extends RuntimeException{
    public DAOConfigurationException( String message ) {
        super( message );
    }


    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }


    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
