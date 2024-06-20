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
    public String mainWindow;

    // URLs
    public String loginPageURL = "https://www.saucedemo.com/";
    public String checkoutStepTwoPageURL = "https://www.saucedemo.com/checkout-step-two.html";
    public String checkoutStepOnePageURL = "https://www.saucedemo.com/checkout-step-one.html";
    public String checkoutCompletePageURL = "https://www.saucedemo.com/checkout-complete.html";
    public String xPageURL = "https://x.com/saucelabs";
    public String facebookPageURL = "https://www.facebook.com/saucelabs";
    public String linkedinPageURL = "https://www.linkedin.com/company/sauce-labs/";

    // Pages
    public LoginPage loginPage;
    public NavbarPage navbarPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public FooterPage footerPage;
    public CheckoutStepOnePage checkoutStepOnePage;
    public CheckoutStepTwoPage checkoutStepTwoPage;
    public CheckoutCompletePage checkoutCompletePage;
    public ProductPage productPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage();
        navbarPage = new NavbarPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        footerPage = new FooterPage();
        checkoutStepOnePage = new CheckoutStepOnePage();
        checkoutStepTwoPage = new CheckoutStepTwoPage();
        checkoutCompletePage = new CheckoutCompletePage();
        productPage = new ProductPage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    //-------------------------------------------------------------------------

    // Helper methods
    public void loginUserWithCredentials(String username, String password) {
        loginPage.insertUsername(username);
        loginPage.insertPassword(password);
        loginPage.clickOnLoginButton();
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        checkoutStepOnePage.insertFirstName(firstName);
        checkoutStepOnePage.insertLastName(lastName);
        checkoutStepOnePage.insertPostalCode(postalCode);
        System.out.println(checkoutStepOnePage.firstNameInput.getText());
        checkoutStepOnePage.clickOnContinueButton();
    }

    public void resetAppState() {
        navbarPage.clickOnHamburgerMenu();
        navbarPage.clickOnHamburgerMenuItem("Reset App State");
        driver.navigate().refresh();

        inventoryPage.numberOfProductsInCart = 0;
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

        // Closing all tabs except main, then switch to new tab
        closeAllWindowsExceptMain();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void closeAllWindowsExceptMain() {
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
            break;
        }
    }
}
