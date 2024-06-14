package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

public class BaseTest {
    public static WebDriver driver;

    public LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();

        loginPage = new LoginPage();
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

    // Helper methods
    public void loginUserWithCredentials(String username, String password) {
        loginPage.insertUsername("standard_user");
        loginPage.insertPassword("secret_sauce");
        loginPage.clickOnLoginButton();
    }
}
