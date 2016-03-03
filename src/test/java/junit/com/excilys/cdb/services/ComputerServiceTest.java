package junit.com.excilys.cdb.services;

import com.excilys.cdb.daos.impl.ComputerDaoImpl;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.validators.ValidatorException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerServiceTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ComputerDaoImpl.class)
public class ComputerServiceTest {

  @Autowired
  ComputerService computerService;
  
  /** The computer dao. */
  ComputerDaoImpl computerDao;
  
  /** The computers. */
  List<Computer> computers;

  /**
   * Inits the ComputerDaoImpl mock.
   */
  @Before
  public void init() {
    // Mock ComputerDAO
    PowerMockito.mockStatic(ComputerDaoImpl.class);
    computerDao = EasyMock.createMock(ComputerDaoImpl.class);

    try {
      PowerMockito.whenNew(ComputerDaoImpl.class).withAnyArguments().thenReturn(computerDao);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Find by id negative param test.
   */
  @Test(expected = ValidatorException.class)
  public void findByIdNegativeParamTest() {
    int testedId = -2;
    EasyMock.expect(computerDao.findById(testedId)).andReturn(null);
    computerService.findById(testedId);
  }

  /**
   * Find by name null param test.
   */
  @Test(expected = ValidatorException.class)
  public void findByNameNullParamTest() {
    String testedName = null;
    EasyMock.expect(computerDao.findByName(testedName)).andReturn(null);
    computerService.findByName(testedName);
    
  }

  /**
   * Find by name empty string param test.
   */
  @Test(expected = ValidatorException.class)
  public void findByNameEmptyStringParamTest() {
    String testedName = "";
    EasyMock.expect(computerDao.findByName(testedName)).andReturn(null);
    computerService.findByName(testedName);
  }

  /**
   * Find range negative param test.
   */
  @Test(expected = ValidatorException.class)
  public void findRangeNegativeParamTest() {
    int startingRow = -2;
    int size = -3;
    EasyMock.expect(computerDao.findRange(startingRow, size)).andReturn(null);
    computerService.findRange(startingRow, size);
  }

  /**
   * Delete by id negative param test.
   */
  @Test(expected = ValidatorException.class)
  public void deleteByIdNegativeParamTest() {
    int testedId = -2;
    computerService.deleteComputer(testedId);
    EasyMock.expectLastCall().once();
  }
}
