package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Connection;

public class DAOFactory {

    private static final String PROPERTY_FILE       = "dao/dao.properties";
    private static final String URL_PROPERTY             = "url";
    private static final String DRIVER_PROPERTY          = "driver";
    private static final String USERNAME_PROPERTY = "nomutilisateur";
    private static final String PASSWORD_PROPERTY    = "motdepasse";

    private String url;
    private String username;
    private String password;


    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public static DAOFactory getInstance() throws DAOConfigurationException {

        Properties properties = new Properties();
        String url = null;
        String driver = null;
        String nomUtilisateur = null;
        String motDePasse = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( PROPERTY_FILE );


        if ( fichierProperties == null ) {
            throw new DAOConfigurationException( "Property file " + PROPERTY_FILE + " not found." );
        }


        try {
            properties.load( fichierProperties );
            url = properties.getProperty( URL_PROPERTY );
            driver = properties.getProperty( DRIVER_PROPERTY );
            nomUtilisateur = properties.getProperty( USERNAME_PROPERTY );
            motDePasse = properties.getProperty( PASSWORD_PROPERTY );

        } catch ( IOException e ) {
            throw new DAOConfigurationException( "Cannot load propertied " + PROPERTY_FILE, e );
        }


        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new DAOConfigurationException( "Driver is missiing from classpath.", e );
        }
        
        DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );

        return instance;

    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection( url, username, password );
    }


    public ComputerDAO getComputerDAO() {
        return new ComputerDAOImpl( this );
    }
    
    public CompanyDAO getCompanyDAO() {
        return new CompanyDAOImpl( this );
    }

}