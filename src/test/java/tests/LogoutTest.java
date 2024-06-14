package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void userCanLogOut() {
        loginUserWithCredentials("standard_user", "secret_sauce");

        navbarPage.clickOnHamburgerMenu();
        navbarPage.clickOnHamburgerMenuItem("Logout");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }
}
