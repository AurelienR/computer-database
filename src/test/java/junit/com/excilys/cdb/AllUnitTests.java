package junit.com.excilys.cdb;

import com.excilys.cdb.services.ComputerService;

import dbunit.DbUnitManager;

import junit.com.excilys.cdb.mappers.CompanyMapperTest;
import junit.com.excilys.cdb.mappers.ComputerMapperTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CompanyMapperTest.class, ComputerMapperTest.class, ComputerService.class })
public class AllUnitTests {

  private static DbUnitManager dbUnit;

  /**
   * Setup unit test suite.
   * 
   * @throws Exception
   *           Failed to setup test
   */
  @BeforeClass
  public static void setUp() throws Exception {
    System.out.println("STARTING UNIT TESTS");
    System.out.println("Set up db");
    dbUnit = new DbUnitManager();
    dbUnit.setupDb();

    System.out.println("Starting tests");
  }

  @AfterClass
  public static void tearDown() {
  }
}
