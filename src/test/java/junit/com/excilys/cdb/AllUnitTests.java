package junit.com.excilys.cdb;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import DBUnit.DBUnitManager;
import junit.com.excilys.cdb.mappers.CompanyMapperTest;
import junit.com.excilys.cdb.mappers.ComputerMapperTest;


@RunWith(Suite.class)
@SuiteClasses({CompanyMapperTest.class, ComputerMapperTest.class})
public class AllUnitTests {
	
	private static DBUnitManager dbUnit;

    @BeforeClass
    public static void setUp() throws Exception {
    	System.out.println("STARTING UNIT TESTS");
    	System.out.println("Set up db");
    	dbUnit = new DBUnitManager();
    	dbUnit.setupDB();
    	
    	System.out.println("Starting tests");
    }

    @AfterClass
    public static void tearDown() {
    }
}
