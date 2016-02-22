package junit.com.excilys.cdb.services;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.cdb.daos.impl.ComputerDAOImpl;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.validators.ValidatorException;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDAOImpl.class)
public class ComputerServiceTest {

	ComputerDAOImpl computerDAO;
	List<Computer> computers;
	
	
	
	@Before
	public void init(){		
		// Mock ComputerDAO
		PowerMockito.mockStatic(ComputerDAOImpl.class);
		computerDAO = EasyMock.createMock(ComputerDAOImpl.class);

		try {
			PowerMockito.whenNew(ComputerDAOImpl.class).withAnyArguments().thenReturn(computerDAO);
		} catch (Exception e) {
				e.printStackTrace();
		}
		
	}	
	

	@Test(expected = ValidatorException.class) 
	public void findByIdNegativeParamTest() {
		int testedId = -2;
		EasyMock.expect(computerDAO.findById(testedId)).andReturn(null);
		ComputerService.getInstance().findById(testedId);				
	}
	
	@Test(expected = ValidatorException.class) 
	public void findByNameNullParamTest() {
		String testedName = null;
		EasyMock.expect(computerDAO.findByName(testedName)).andReturn(null);
		ComputerService.getInstance().findByName(testedName);				
	}

	@Test(expected = ValidatorException.class) 
	public void findByNameEmptyStringParamTest() {
		String testedName = "";
		EasyMock.expect(computerDAO.findByName(testedName)).andReturn(null);
		ComputerService.getInstance().findByName(testedName);				
	}
	
	@Test(expected = ValidatorException.class) 
	public void findRangeNegativeParamTest() {
		int startingRow = -2;
		int size = -3;
		EasyMock.expect(computerDAO.findRange(startingRow,size)).andReturn(null);
		ComputerService.getInstance().findRange(startingRow, size);				
	}
	
	
	@Test(expected = ValidatorException.class) 
	public void deleteByIdNegativeParamTest() {
		int testedId = -3;
		//EasyMock.expect(computerDAO.deleteComputer(testedId))
		//ComputerService.getInstance().deleteComputer(testedId);			
	}
}
