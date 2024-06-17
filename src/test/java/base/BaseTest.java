package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.*;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public LoginPage loginPage;
    public NavbarPage navbarPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public FooterPage footerPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage();
        navbarPage = new NavbarPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        footerPage = new FooterPage();
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

    //-------------------------------------------------------------------------

    // Helper methods
    public void loginUserWithCredentials(String username, String password) {
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickOnLoginButton();
    }

    public void resetAppState() {
        navbarPage.clickOnHamburgerMenu();
        navbarPage.clickOnHamburgerMenuItem("Reset App State");
        driver.navigate().refresh();
    }

    public void scrollIntoElementView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForAllElementsVisibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementDisplayed(WebElement element) {
        boolean elementIsDisplayed = false;
        try {
            elementIsDisplayed = element.isDisplayed();
        } catch(Exception ignored) {

        }

        return elementIsDisplayed;
    }

    public void switchToNewWindow() {
        String mainWindow = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}
