package selenium.dashboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageSizeTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  private final String RESULTS_XPATH = "//tbody[@id='results']/tr";
  
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/computerdatabase/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPageSize30() throws Exception {
	
    driver.get(baseUrl + "computers");
    
    // Set page size Ref
    int pageSizeRef = 30;
    driver.findElement(By.linkText("50")).click();
    driver.findElement(By.linkText(String.valueOf(pageSizeRef))).click();
    int rowCount = driver.findElements(By.xpath(RESULTS_XPATH)).size();
    
    // Test
    assertEquals(pageSizeRef,rowCount);       
  }
  
  @Test
  public void testPageSize50() throws Exception {
	
    driver.get(baseUrl + "computers");
    
    // Set page size Ref
    int pageSizeRef = 50;
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.linkText(String.valueOf(pageSizeRef))).click();
    int rowCount = driver.findElements(By.xpath(RESULTS_XPATH)).size();
    
    // Test
    assertEquals(pageSizeRef,rowCount);       
  }
  
  @Test
  public void testPageSize100() throws Exception {
	
    driver.get(baseUrl + "computers");
    
    // Set page size Ref
    int pageSizeRef = 50;
    driver.findElement(By.linkText("30")).click();
    driver.findElement(By.linkText(String.valueOf(pageSizeRef))).click();
    int rowCount = driver.findElements(By.xpath(RESULTS_XPATH)).size();
    
    // Test
    assertEquals(pageSizeRef,rowCount);       
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}
