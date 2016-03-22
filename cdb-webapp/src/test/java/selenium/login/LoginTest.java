package selenium.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest {
  
  private static final String USERNAME_USER = "user";
  private static final String PASSWORD_USER = "user";
  private static final String USERNAME_ADMIN = "admin";
  private static final String PASSWORD_ADMIN = "admin";

  public static void logout(WebDriver driver, String baseUrl) {
    driver.get(baseUrl + "logout");
  }

  public static void goToLoginPage(WebDriver driver, String baseUrl) {
    driver.get(baseUrl + "login");
  }

  public static void logAsUser(WebDriver driver){
    login(driver,USERNAME_USER,PASSWORD_USER);
  }
  
  public static void logAsAdmin(WebDriver driver){
    login(driver,USERNAME_ADMIN,PASSWORD_ADMIN);
  }
  
  public static void login(WebDriver driver, String username, String password) {
    WebElement usernameInput = driver.findElement(By.id("username"));
    WebElement passwordInput = driver.findElement(By.id("password"));
    WebElement loginBtn =  driver.findElement(By.name("Submit"));
    usernameInput.sendKeys(username);
    passwordInput.sendKeys(password);
    loginBtn.click();   
  }
}
