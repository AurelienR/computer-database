package junit.com.excilys.cdb.services;

import static org.mockito.Mockito.when;

import com.excilys.cdb.daos.ComputerDao;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.validators.ValidatorException;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * The Class ComputerServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/cdb-context-test.xml")
public class ComputerServiceTest {

  @Autowired
  ComputerService computerService;

  /** The computer dao. */
  @Autowired
  ComputerDao computerDao;

  /** The computers. */
  List<Computer> computers;

  /**
   * Inits the ComputerDaoImpl mock.
   */
  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Find by id negative param test.
   */
  @Test(expected = ValidatorException.class)
  public void findByIdNegativeParamTest() {
    long testedId = -2L;
    when(computerDao.findById(testedId)).thenReturn(null);
    computerService.findById(testedId);
  }

  /**
   * Find by name null param test.
   */
  @Test(expected = ValidatorException.class)
  public void findByNameNullParamTest() {
    String testedName = null;
    when(computerDao.findByName(testedName)).thenReturn(null);
    computerService.findByName(testedName);

  }

  /**
   * Find by name empty string param test.
   */
  @Test(expected = ValidatorException.class)
  public void findByNameEmptyStringParamTest() {
    String testedName = "";
    when(computerDao.findByName(testedName)).thenReturn(null);
    computerService.findByName(testedName);
  }

  /**
   * Delete by id negative param test.
   */
  @Test(expected = ValidatorException.class)
  public void deleteByIdNegativeParamTest() {
    long testedId = -2;
    computerService.deleteComputer(testedId);
    EasyMock.expectLastCall().once();
  }
}
