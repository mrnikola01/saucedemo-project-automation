package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginPageURL);
    }

    @Test
    public void userCanLogOut() {
        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);

        navbarPage.clickOnHamburgerMenu();
        navbarPage.clickOnHamburgerMenuItem("Logout");

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
        Assert.assertTrue(isElementDisplayed(loginPage.loginButton));
    }
}
