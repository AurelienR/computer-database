package junit.com.excilys.cdb.services;

import com.excilys.cdb.daos.repositories.CompanyRepository;
import com.excilys.cdb.services.CompanyService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/service-context-test.xml")
public class CompanyServiceTest {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceTest.class);
  
  @Autowired
  CompanyService companyService;

  @Autowired
  CompanyRepository companyRepository;

  // Hook methods
  @BeforeClass
  public static void prepareTest() {
    LOGGER.info("---------------- START ComputerServiceTest ----------------\n");
  }

  @AfterClass
  public static void endTest() {
    LOGGER.info("---------------- END ComputerServiceTest ----------------\n");
  }

  
  @Test
  public void test() {
    //LOGGER.info("---------------- END ComputerServiceTest ----------------\n");
  }
  
  @Before
  public void setUp() {
    LOGGER.info("START TEST CASE");
  }

  @After
  public void tearDown() {
    LOGGER.info("END TEST CASE\n\n");
  }
    
}
