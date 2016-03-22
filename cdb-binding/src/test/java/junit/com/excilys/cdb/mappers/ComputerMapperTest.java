package junit.com.excilys.cdb.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.mappers.ComputerMapper;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.utils.DateFormatManagerException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class ComputerMapperTest {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapperTest.class);
  
  // Mocks
  @Mock
  ComputerDto computerDto;
  @Mock
  Computer computer;
  @Mock
  Company company;
  @Mock
  CompanyDto companyDto;

  
  // Hook methods
  @BeforeClass
  public static void prepareTest() {
    LOGGER.info("---------------- START ComputerMapperTest ----------------\n");
  }

  @AfterClass
  public static void endTest() {
    LOGGER.info("---------------- END ComputerMapperTest ----------------\n");
  }

  /**
   * Before test case.
   */
  @Before
  public void setUp() {
    LOGGER.info("START TEST CASE");    
  }

  /**
   * After test case.
   */
  @After
  public void tearDown() {
    companyDto = null;
    company = null;
    LOGGER.info("END TEST CASE\n\n");
  }

  // TESTS CASES
  /**
   * Test toComputerDTO method with parameter.
   */
  @Test
  public void toComputerDtoNullParameterTest() {
    LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    try {
      ComputerDto resultComputerDto = ComputerMapper.toComputerDto((Computer)null);
      assertNull(resultComputerDto);
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toComputerDTO method with invalid computer as parameter.
   */
  @Test
  public void toComputerDtoInvalidComputerTest() {

    LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    
    when(computer.getId()).thenReturn(-5L);
    when(computer.getName()).thenReturn(null);
    when(computer.getIntroduced()).thenReturn(null);
    when(computer.getDiscontinued()).thenReturn(null);
    when(computer.getCompany()).thenReturn(null);

    try {
      ComputerDto resultComputerDto = ComputerMapper.toComputerDto(computer);
      assertNotNull(resultComputerDto);
      assertEquals(resultComputerDto.getId(), -5);
      assertEquals(resultComputerDto.getName(), null);
      assertNull(resultComputerDto.getIntroduced());
      assertNull(resultComputerDto.getDiscontinued());
      assertNull(resultComputerDto.getCompany());
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toComputerDTO with a valid computer.
   */
  @Test
  public void toComputerDtoValidComputerTest() {

    LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    
    LocalDateTime intro = LocalDateTime.of(2013, 02, 28, 14, 20);
    LocalDateTime disc = LocalDateTime.of(2016, 02, 28, 14, 20);

    when(company.getId()).thenReturn(28L);
    when(company.getName()).thenReturn("testCompany");

    when(computer.getId()).thenReturn(5L);
    when(computer.getName()).thenReturn("testComputer");
    when(computer.getIntroduced()).thenReturn(intro);
    when(computer.getDiscontinued()).thenReturn(disc);
    when(computer.getCompany()).thenReturn(company);

    try {
      ComputerDto resultComputerDto = ComputerMapper.toComputerDto(computer);
      assertNotNull(resultComputerDto);
      assertEquals(5, resultComputerDto.getId());
      assertEquals("testComputer", resultComputerDto.getName());
      assertEquals("28/02/2013", resultComputerDto.getIntroduced());
      assertEquals("28/02/2016", resultComputerDto.getDiscontinued());
      assertEquals(28, resultComputerDto.getCompany().getId());
      assertEquals("testCompany", resultComputerDto.getCompany().getName());
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toComputer with a null ComputerDTO parameter.
   */
  @Test
  public void toComputerNullParameterTest() {
    
    LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    
    try {
      Computer resultComputer = ComputerMapper.toComputer(null);
      assertNull(resultComputer);
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toComputer with an invalid ComputerDTO parameter.
   */
  @Test
  public void toComputerInvalidComputerDtoTest() {

    LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    
    LocalDateTime intro = LocalDateTime.of(2016, 03, 04, 00, 00);
    LocalDateTime disc = LocalDateTime.of(2017, 03, 04, 00, 00);

    when(computerDto.getId()).thenReturn(-5L);
    when(computerDto.getName()).thenReturn(null);
    when(computerDto.getIntroduced()).thenReturn("04/03/2016");
    when(computerDto.getDiscontinued()).thenReturn("04/03/2017");
    when(computerDto.getCompany()).thenReturn(null);

    try {
      Computer resultComputer = ComputerMapper.toComputer(computerDto);
      assertNotNull(resultComputer);
      assertEquals(-5, resultComputer.getId());
      assertNull(resultComputer.getName());
      assertNotNull(resultComputer.getIntroduced());
      assertNotNull(resultComputer.getDiscontinued());
      assertEquals(intro, resultComputer.getIntroduced());
      assertEquals(disc, resultComputer.getDiscontinued());
      assertNull(resultComputer.getCompany());
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

  /**
   * Test toComputer with an invalid ComputerDTO dates format.
   */
  @Test(expected = DateFormatManagerException.class)
  public void toComputerInvalidDateFormatTest() {
    
    LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());

    when(computerDto.getId()).thenReturn(-5L);
    when(computerDto.getName()).thenReturn(null);
    when(computerDto.getIntroduced()).thenReturn("13/50/30091");
    when(computerDto.getDiscontinued()).thenReturn("2017-03-04");
    when(computerDto.getCompany()).thenReturn(null);

    ComputerMapper.toComputer(computerDto);
  }

  /**
   * Test toComputer method with a valid computer.
   */
  @Test
  public void toComputerValidComputerDtoTest() {

    LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    
    LocalDateTime intro = LocalDateTime.of(2016, 03, 04, 00, 00);
    LocalDateTime disc = LocalDateTime.of(2017, 03, 04, 00, 00);

    when(companyDto.getId()).thenReturn(202L);
    when(companyDto.getName()).thenReturn("CompanyTest");

    when(computerDto.getId()).thenReturn(5L);
    when(computerDto.getName()).thenReturn("ComputerTest");
    when(computerDto.getIntroduced()).thenReturn("04/03/2016");
    when(computerDto.getDiscontinued()).thenReturn("04/03/2017");
    when(computerDto.getCompany()).thenReturn(companyDto);

    try {
      Computer resultComputer = ComputerMapper.toComputer(computerDto);
      assertNotNull(resultComputer);
      assertEquals(5, resultComputer.getId());
      assertEquals("ComputerTest", resultComputer.getName());
      assertEquals(intro, resultComputer.getIntroduced());
      assertEquals(disc, resultComputer.getDiscontinued());
      assertNotNull(resultComputer.getCompany());
      assertEquals(202, resultComputer.getCompany().getId());
      assertEquals("CompanyTest", resultComputer.getCompany().getName());
    } catch (Throwable t) {
      fail("Exception thrown should not be thrown");
    }
  }

}
