package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionCloser{

	public static void silentClose( ResultSet resultSet ) throws DAOException {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	        	throw new DAOException("Failure closing ResultSet",e);
	        }

	    }
	}


	public static void silentClose( Statement statement ) throws DAOException {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	        	throw new DAOException("Failure closing Statement",e);
	        }
	    }
	}

	public static void silentClose( Connection connexion ) throws DAOException {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	        	throw new DAOException("Failure closing connextion",e);
	        }
	    }
	}


	public static void silentCloses( Statement statement, Connection connexion ) throws DAOException {
	    silentClose(statement );
	    silentClose( connexion );
	}


	public static void silentCloses( ResultSet resultSet, Statement statement, Connection connexion ) throws DAOException {
	    silentClose( resultSet );
	    silentClose( statement );
	    silentClose( connexion );
	}
}
