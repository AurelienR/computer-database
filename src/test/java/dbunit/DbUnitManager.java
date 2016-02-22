package dbunit;

import org.h2.tools.RunScript;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class DbUnitManager.
 */
@RunWith(PowerMockRunner.class)

public class DbUnitManager {

  /** The Constant PROPERTY_FILE. */
  private static final String PROPERTY_FILE = "properties/dao.properties";
  
  /** The Constant URL_PROPERTY. */
  private static final String URL_PROPERTY = "url";
  
  /** The Constant DRIVER_PROPERTY. */
  private static final String DRIVER_PROPERTY = "driver";
  
  /** The Constant USERNAME_PROPERTY. */
  private static final String USERNAME_PROPERTY = "nomutilisateur";
  
  /** The Constant PASSWORD_PROPERTY. */
  private static final String PASSWORD_PROPERTY = "motdepasse";
  
  /** The Constant SCHEMA_SQL. */
  public static final String SCHEMA_SQL = "src/test/resources/config/1-SCHEMA.sql";
  
  /** The Constant PRIVILEGES_SQL. */
  public static final String PRIVILEGES_SQL = "src/test/resources/config/2-PRIVILEGES.sql";
  
  /** The Constant ENTRIES_SQL. */
  public static final String ENTRIES_SQL = "src/test/resources/config/3-ENTRIES.sql";

  /** The url. */
  private String url;
  
  /** The username. */
  private String username;
  
  /** The password. */
  private String password;
  
  /** The driver. */
  private String driver;

  /**
   * Instantiates a new db unit manager.
   *
   * @throws Exception the exception
   */
  public DbUnitManager() throws Exception {
    initialize();
  }

  /**
   * Initialize initializ db unit manage.
   *
   * @throws Exception           the exception
   */
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
   * Create db schema.
   *
   * @throws Exception           failed to run sql file
   */
  public void createSchema() throws Exception {
    RunScript.execute(url, username, password, SCHEMA_SQL, StandardCharsets.UTF_8, false);
  }

  /**
   * Create db privileges.
   *
   * @throws Exception           failed to run sql file
   */
  public void createPrivilegies() throws Exception {
    RunScript.execute(url, username, password, password, StandardCharsets.UTF_8, false);
  }

  /**
   * Create db entries.
   *
   * @throws Exception           failed to run sql file
   */
  public void createEntries() throws Exception {
    RunScript.execute(url, username, password, ENTRIES_SQL, StandardCharsets.UTF_8, false);
  }

  /**
   * Setup db for test.
   *
   * @throws Exception           failed to run sql files
   */
  public void setupDb() throws Exception {
    createSchema();
    // createPrivilegies();
    createEntries();
  }

}
