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

  private static final String PROPERTY_FILE = "properties/dao.properties";
  private static final String URL_PROPERTY = "url";
  private static final String USERNAME_PROPERTY = "nomutilisateur";
  private static final String PASSWORD_PROPERTY = "motdepasse";
  public static final String SCHEMA_SQL = "src/test/resources/config/1-SCHEMA.sql";
  public static final String PRIVILEGES_SQL = "src/test/resources/config/2-PRIVILEGES.sql";
  public static final String ENTRIES_SQL = "src/test/resources/config/3-ENTRIES.sql";


  private String url;


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
  }

  /**
   * Create db schema.
   *
   * @throws Exception           failed to run sql file
   */
  public void createSchema() throws Exception {
    RunScript.execute(url, "root", "admin" ,SCHEMA_SQL, StandardCharsets.UTF_8, false);
  }

  /**
   * Create db privileges.
   *
   * @throws Exception           failed to run sql file
   */
  public void createPrivilegies() throws Exception {
    RunScript.execute(url, "root", "admin", PRIVILEGES_SQL,StandardCharsets.UTF_8, false);
  }

  /**
   * Create db entries.
   *
   * @throws Exception           failed to run sql file
   */
  public void createEntries() throws Exception {
    RunScript.execute(url, "root", "admin", ENTRIES_SQL, StandardCharsets.UTF_8, false);
  }

  /**
   * Setup db for test.
   *
   * @throws Exception           failed to run sql files
   */
  public void setupDb() throws Exception {
    createSchema();
    createPrivilegies();
    createEntries();
  }

}
