package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HamburgerMenuTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginUserWithCredentials("standard_user", "secret_sauce");
    }

    @Test
    public void userCanClickOnHamburgerMenu() {
        navbarPage.clickOnHamburgerMenu();
        waitForAllElementsVisibility(navbarPage.hamburgerMenuItems);

        Assert.assertEquals(navbarPage.hamburgerMenuItems.size(), 4);
        Assert.assertEquals(navbarPage.hamburgerMenuItems.getFirst().getText(), "All Items");
        Assert.assertEquals(navbarPage.hamburgerMenuItems.get(1).getText(), "About");
        Assert.assertEquals(navbarPage.hamburgerMenuItems.get(2).getText(), "Logout");
        Assert.assertEquals(navbarPage.hamburgerMenuItems.getLast().getText(), "Reset App State");
    }

    @Test
    public void userCanClickOnFirstHamburgerItem() {
        navbarPage.clickOnShoppingCart();
        navbarPage.clickOnHamburgerMenu();

        waitForAllElementsVisibility(navbarPage.hamburgerMenuItems);
        navbarPage.clickOnHamburgerMenuItem("All Items");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertFalse(isElementDisplayed(navbarPage.hamburgerMenuItems.getFirst()));
    }

    @Test
    public void userCanClickOnSecondHamburgerItem() {
        navbarPage.clickOnHamburgerMenu();

        waitForAllElementsVisibility(navbarPage.hamburgerMenuItems);
        navbarPage.clickOnHamburgerMenuItem("About");

        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @Test
    public void userCanClickOnThirdHamburgerItem() {
        navbarPage.clickOnHamburgerMenu();

        waitForAllElementsVisibility(navbarPage.hamburgerMenuItems);
        navbarPage.clickOnHamburgerMenuItem("Logout");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    public void userCanClickOnFourthHamburgerItem() {
        inventoryPage.addProductInCart( "Sauce Labs Onesie");
        resetAppState();

        Assert.assertFalse(isElementDisplayed(navbarPage.hamburgerMenuItems.getFirst()));
        Assert.assertFalse(isElementDisplayed(navbarPage.shoppingCartBadge));
    }
}
