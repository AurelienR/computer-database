package DBUnit;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)

public class DBUnitManager {
	
	private static final String PROPERTY_FILE = "properties/dao.properties";
	private static final String URL_PROPERTY = "url";
	private static final String DRIVER_PROPERTY = "driver";
	private static final String USERNAME_PROPERTY = "nomutilisateur";
	private static final String PASSWORD_PROPERTY = "motdepasse";
	public static final String SCHEMA_SQL ="src/test/resources/config/1-SCHEMA.sql";
	public static final String PRIVILEGES_SQL ="src/test/resources/config/2-PRIVILEGES.sql";
	public static final String ENTRIES_SQL ="src/test/resources/config/3-ENTRIES.sql";
	
	private String url;
	private String username;
	private String password;
	private String driver;
	private IDatabaseTester databaseTester;
	
	public DBUnitManager() throws Exception {
		initialize();
	};

	public void initialize() throws Exception {
		// Local variables initialization
		Properties properties = new Properties();
	    
		// Read DAO file property
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fileProperties = classLoader.getResourceAsStream(PROPERTY_FILE);

		// Check DAO file has been read correctly
		if (fileProperties == null) {
			throw new Exception("Property file " + PROPERTY_FILE + " not found.");
		}

		// Extract file property fields
		properties.load(fileProperties);
		this.url = properties.getProperty(URL_PROPERTY);
		this.driver = Class.forName(properties.getProperty(DRIVER_PROPERTY)).getName();
		this.username = properties.getProperty(USERNAME_PROPERTY);
		this.password = properties.getProperty(PASSWORD_PROPERTY);
	}

	/**
	 * Create db schema
	 * @throws Exception
	 */
	public void createSchema() throws Exception {
		RunScript.execute(url, username, password,SCHEMA_SQL, StandardCharsets.UTF_8, false);
	}

	/**
	 * Create db privileges
	 * @throws Exception
	 */
	public void createPrivilegies() throws Exception {
		RunScript.execute(url, username,password,password, StandardCharsets.UTF_8, false);
	}
	
	/**
	 * Create db entries
	 * @throws Exception
	 */
	public void createEntries() throws Exception {
		RunScript.execute(url, username, password,ENTRIES_SQL, StandardCharsets.UTF_8, false);
	}
	
	/**
	 * Setup db for test
	 * @throws Exception
	 */
	public void setupDB() throws Exception{
		createSchema();
		//createPrivilegies();
		createEntries();
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
	
	public IDatabaseTester getDatabaseTester() throws ClassNotFoundException {
		return databaseTester;
	}
}
