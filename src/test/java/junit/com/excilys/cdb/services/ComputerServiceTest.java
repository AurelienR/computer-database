package junit.com.excilys.cdb.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import com.excilys.cdb.daos.ComputerDao;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.models.Order;
import com.excilys.cdb.models.OrderBy;
import com.excilys.cdb.models.QueryPageParameter;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.validators.ValidatorException;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ComputerServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/cdb-context-test.xml")
public class ComputerServiceTest {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceTest.class);

  @Autowired
  ComputerService computerService;

  /** The computer dao. */
  @Autowired
  ComputerDao computerDao;

  /** The computers. */
  List<Computer> computers;
  QueryPageParameter qp;
  Computer computer;

  // Hook methods
  @BeforeClass
  public static void prepareTest() {
    LOGGER.info("---------------- START ComputerServiceTest ----------------\n");
  }

  @AfterClass
  public static void endTest() {
    LOGGER.info("---------------- END ComputerServiceTest ----------------\n");
  }

  @Before
  public void setUp() {
    LOGGER.info("START TEST CASE");
    MockitoAnnotations.initMocks(this);
    computers = new ArrayList<Computer>();
    qp = Mockito.mock(QueryPageParameter.class);
    computer = Mockito.mock(Computer.class);
  }

  @After
  public void tearDown() {
    computers = null;
    qp = null;
    computer = null;
    LOGGER.info("END TEST CASE\n\n");
  }

  // ***************** FINDBYID TEST *****************
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
   * Find by id that is valid param test.
   */
  @Test
  public void findByValidIdTest() {
    long testedId = 64L;
    when(computerDao.findById(testedId)).thenReturn(new Computer(testedId, "TestComputer", null, null, null));
    long result = computerService.findById(testedId).getId();

    assertEquals(testedId, result);
  }

  // ***************** FINDBYNAME TEST *****************
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
   * Test find by name method with a valid name.
   */
  @Test
  public void findByNameValidTest() {
    String testedName = "testComputer";
    computers.add(new Computer(1, testedName, null, null, null));
    when(computerDao.findByName(testedName)).thenReturn(computers);
    String result = computerService.findByName(testedName).get(0).getName();

    assertEquals(testedName, result);
  }

  // ***************** DELETEBYID TEST *****************
  /**
   * Delete by id negative param test.
   */
  @Test(expected = ValidatorException.class)
  public void deleteByIdNegativeParamTest() {
    long testedId = -2L;
    computerService.deleteComputer(testedId);
    EasyMock.expectLastCall().once();
  }

  /**
   * Delete by valid id param test.
   */
  @Test
  public void deleteByIdValidTest() {
    long testedId = 52L;
    try {
      computerService.deleteComputer(testedId);
    } catch (Throwable t) {
      fail("Exception thrown on valid id deletion");
    }
  }

  // ***************** FINDBYQUERYPARAMETER TEST *****************
  /**
   * Find by qp invalid page index test.
   */
  @Test(expected = ValidatorException.class)
  public void findByQpInvalidPageIndexTest() {
    int testedPage = -1;
    when(qp.getLimit()).thenReturn(2);
    when(qp.getOffset()).thenReturn(30);
    when(qp.getPageIndex()).thenReturn(testedPage);
    when(computerDao.findByQuery(qp)).thenReturn(null);
    computerService.findByQuery(qp);
  }

  /**
   * Find by qp invalid page size test.
   */
  @Test(expected = ValidatorException.class)
  public void findByQpInvalidPageSizeTest() {
    int testedPageSize = -1;
    when(qp.getLimit()).thenReturn(2);
    when(qp.getOffset()).thenReturn(30);
    when(qp.getPageSize()).thenReturn(testedPageSize);
    when(computerDao.findByQuery(qp)).thenReturn(null);
    computerService.findByQuery(qp);
  }

  /**
   * Find by qp invalid search test.
   */
  @Test(expected = ValidatorException.class)
  public void findByQpInvalidSearchTest() {
    String testedSearch = null;
    when(qp.getLimit()).thenReturn(2);
    when(qp.getOffset()).thenReturn(30);
    when(qp.getSearch()).thenReturn(testedSearch);
    when(computerDao.findByQuery(qp)).thenReturn(null);
    computerService.findByQuery(qp);
  }

  /**
   * Find by qp invalid order test.
   */
  @Test(expected = ValidatorException.class)
  public void findByQpInvalidOrderTest() {
    Order testedOrder = null;
    when(qp.getLimit()).thenReturn(2);
    when(qp.getOffset()).thenReturn(30);
    when(qp.getOrder()).thenReturn(testedOrder);
    when(computerDao.findByQuery(qp)).thenReturn(null);
    computerService.findByQuery(qp);
  }

  /**
   * Find by qp invalid order by test.
   */
  @Test(expected = ValidatorException.class)
  public void findByQpInvalidOrderByTest() {
    OrderBy testedOrderBy = null;
    when(qp.getLimit()).thenReturn(2);
    when(qp.getOffset()).thenReturn(30);
    when(qp.getOrderBy()).thenReturn(testedOrderBy);
    when(computerDao.findByQuery(qp)).thenReturn(null);
    computerService.findByQuery(qp);
  }

  /**
   * Find by valid qp by test.
   */
  @Test
  public void findByQpValidTest() {

    int testedPage = 2;
    int testedPageSize = 30;
    OrderBy testedOrderBy = OrderBy.id;
    Order testedOrder = Order.ASC;
    String testedSearch = "Mac";

    when(qp.getPageIndex()).thenReturn(testedPage);
    when(qp.getPageSize()).thenReturn(testedPageSize);
    when(qp.getOrder()).thenReturn(testedOrder);
    when(qp.getOrderBy()).thenReturn(testedOrderBy);
    when(qp.getSearch()).thenReturn(testedSearch);
    when(qp.getLimit()).thenReturn(testedPageSize);
    when(qp.getOffset()).thenReturn(testedPage * testedPageSize);

    when(computerDao.findByQuery(qp)).thenReturn(computers);
    List<Computer> results = computerService.findByQuery(qp);
    assertNotNull(results);
  }

  // ***************** COUNT TEST *****************
  /**
   * Count invalid search field.
   */
  @Test(expected = ValidatorException.class)
  public void countInvalidSearchFieldTest() {
    String testedSearch = null;
    long count = 2L;
    when(qp.getLimit()).thenReturn(2);
    when(qp.getOffset()).thenReturn(30);
    when(qp.getSearch()).thenReturn(testedSearch);
    when(computerDao.count(qp)).thenReturn(count);
    long result = computerService.count(qp);
    assertEquals(true, (count != result));
  }

  /**
   * Count invalid search field.
   */
  @Test
  public void countvalidSearchFieldTest() {
    String testedSearch = "MAC";
    long count = 2L;
    when(qp.getLimit()).thenReturn(2);
    when(qp.getOffset()).thenReturn(30);
    when(qp.getSearch()).thenReturn(testedSearch);
    when(computerDao.count(qp)).thenReturn(count);
    long result = computerService.count(qp);
    assertEquals(true, (count == result));
  }

  // ***************** CREATE COMPUTER TEST *****************
  /**
   * Create a valid computer.
   */
  @Test
  public void createAValidComputerTest() {
    long testedId = 58L;
    String testedName = "Mac";
    LocalDateTime testedIntro = LocalDateTime.now();
    LocalDateTime testedDisc = testedIntro.plusDays(2);
    Company company = new Company(20L, null);

    when(computer.getId()).thenReturn(testedId);
    when(computer.getName()).thenReturn(testedName);
    when(computer.getIntroduced()).thenReturn(testedIntro);
    when(computer.getDiscontinued()).thenReturn(testedDisc);
    when(computer.getCompany()).thenReturn(company);

    when(computerDao.insertComputer(computer)).thenReturn(58L);

    computerDao.insertComputer(computer);
  }

  /**
   * Create a computer with inconsistent dates.
   */
  @Test(expected = ValidatorException.class)
  public void createInconsistentDatesTest() {
    long testedId = 58L;
    String testedName = "Mac";
    LocalDateTime testedIntro = LocalDateTime.now();
    LocalDateTime testedDisc = testedIntro.minusDays(2);
    Company company = new Company(20L, null);

    when(computer.getId()).thenReturn(testedId);
    when(computer.getName()).thenReturn(testedName);
    when(computer.getIntroduced()).thenReturn(testedIntro);
    when(computer.getDiscontinued()).thenReturn(testedDisc);
    when(computer.getCompany()).thenReturn(company);

    when(computerDao.insertComputer(computer)).thenReturn(58L);

    computerService.createComputer(computer);
  }

}
