package junit.com.excilys.cdb.dao.impl;

import static org.junit.Assert.assertEquals;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.cdb.daos.ConnectionFactory;
import com.excilys.cdb.daos.impl.CompanyDAOImpl;
import com.excilys.cdb.models.Company;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionFactory.class)
public class CompanyDAOImplTest{
	
	private static ConnectionFactoryMocker cfm;
	
	public static final String TABLE_NAME = "company";
	public static final String DATA_SET_PATH ="src/main/resources/DBUnit/dataset.xml";
	
	@BeforeClass
	public static void initialize() throws Exception{
		cfm = new ConnectionFactoryMocker();
		cfm.initialize();
		cfm.createSchema();
		cfm.MockConnectionFactory();
	}
	
	@Before
	public void initTestDB() throws Exception{
		// Set up DB
		cfm.importDataSet(DATA_SET_PATH);
		cfm.MockConnectionFactory();
	}
	
	@Test
	public void createCompanyTest() throws Exception {
		
		// Prepare company to insert
		IDatabaseConnection conn = cfm.getDatabaseTester().getConnection();
		Company company = new Company();
		String refCompanyName = "CompanyTest";
		int refRowCount = conn.createDataSet().getTable("company").getRowCount() + 1;
		company.setName(refCompanyName);
		

		// Insert company
		CompanyDAOImpl.getInstance().insertCompany(company);
		
		// Get DB state
		conn = cfm.getDatabaseTester().getConnection();
		IDataSet dbSet = conn.createDataSet();
		ITable companyTable = dbSet.getTable(TABLE_NAME);
		String testCompanyName = companyTable.getValue(companyTable.getRowCount()-1, "name").toString();
		int testRowCount = companyTable.getRowCount();		
		
		// Tests
		assertEquals(refCompanyName,testCompanyName);
		assertEquals(refRowCount,testRowCount);
	}
}