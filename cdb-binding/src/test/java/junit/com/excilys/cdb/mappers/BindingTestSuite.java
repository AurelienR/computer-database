package junit.com.excilys.cdb.mappers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Suite.class)
@SuiteClasses({CompanyMapperTest.class,ComputerMapperTest.class})
public class BindingTestSuite {
//  private static DbUnitManager dbUnit;
  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(BindingTestSuite.class);
  
  /**
   * Setup unit test suite.
   * 
   * @throws Exception Failed to setup test
   */
  @BeforeClass
  public static void setUp() throws Exception {
    LOGGER.info("********************* BEFORE UNIT TEST SUITE *****************************");
//    LOGGER.info("Setup DB");
//    dbUnit = new DbUnitManager();
//    dbUnit.setupDb();

    LOGGER.info("Starting tests");
  }

  @AfterClass
  public static void tearDown() {
    LOGGER.info("********************* END UNIT TEST SUITE *****************************");
  }
}
