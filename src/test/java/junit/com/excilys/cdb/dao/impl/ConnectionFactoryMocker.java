package junit.com.excilys.cdb.dao.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.cdb.daos.ConnectionFactory;
import com.excilys.cdb.daos.DAOConfigurationException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionFactory.class)
public class ConnectionFactoryMocker {
	
	private static final String PROPERTY_FILE = "DBUnit/dao-test.properties";
	private static final String URL_PROPERTY = "url";
	private static final String DRIVER_PROPERTY = "driver";
	private static final String USERNAME_PROPERTY = "nomutilisateur";
	private static final String PASSWORD_PROPERTY = "motdepasse";
	public static final String SCHEMA ="src/main/resources/DBUnit/schema.sql";
	
	private String url;
	private String username;
	private String password;
	private String driver;
	private IDatabaseTester databaseTester;
	
	public ConnectionFactoryMocker() {};

	public void initialize() throws IOException, ClassNotFoundException {
		// Local variables initialization
		Properties properties = new Properties();

		// Read DAO file property
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fileProperties = classLoader.getResourceAsStream(PROPERTY_FILE);

		// Check DAO file has been read correctly
		if (fileProperties == null) {
			throw new DAOConfigurationException("Property file " + PROPERTY_FILE + " not found.");
		}

		// Extract file property fields
		properties.load(fileProperties);
		this.url = properties.getProperty(URL_PROPERTY);
		this.driver = Class.forName(properties.getProperty(DRIVER_PROPERTY)).getName();
		this.username = properties.getProperty(USERNAME_PROPERTY);
		this.password = properties.getProperty(PASSWORD_PROPERTY);
	}

	public void createSchema() throws Exception {
		RunScript.execute(url, username, password,SCHEMA, StandardCharsets.UTF_8, false);
	}

	public void importDataSet(String dataSetPath) throws Exception {
		IDataSet dataSet = readDataSet(dataSetPath);
		cleanlyInsert(dataSet);
	}

	public IDataSet readDataSet(String dataSetPath) throws Exception {
		return new FlatXmlDataSetBuilder().build(new File(dataSetPath));
	}

	public void cleanlyInsert(IDataSet dataSet) throws Exception {
		databaseTester = new JdbcDatabaseTester( driver, url, username, password);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setDataSet(dataSet); 
		databaseTester.onSetup();
	}

	public DataSource dataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	public void MockConnectionFactory() throws Exception {
		
		ConnectionFactory connectionFactory =  Mockito.mock(ConnectionFactory.class);
	
		try {
			Mockito.when(connectionFactory.getConnection()).thenReturn(dataSource().getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PowerMockito.mockStatic(ConnectionFactory.class);
        BDDMockito.given(ConnectionFactory.getInstance()).willReturn(connectionFactory);
	}
	
	public IDatabaseTester getDatabaseTester() throws ClassNotFoundException {
		return databaseTester;
	}
}
