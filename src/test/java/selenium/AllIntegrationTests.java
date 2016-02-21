package selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import DBUnit.DBUnitManager;
import selenium.dashboard.PageSizeTest;

@RunWith(Suite.class)
@SuiteClasses({PageSizeTest.class})
public class AllIntegrationTests {
	
	private static DBUnitManager dbUnit;
	
    @BeforeClass
    public static void setUp() throws Exception {
    	System.out.println("STARTING INTEGRATION TESTS");
    	//System.out.println("Set up db");
    	//dbUnit = new DBUnitManager();
    	//dbUnit.setupDB();
    	
    	System.out.println("Starting tests");
    }

    @AfterClass
    public static void tearDown() {
    }
}
