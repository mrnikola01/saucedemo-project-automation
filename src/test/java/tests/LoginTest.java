package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void userCanLogIn() {
        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(navbarPage.getTitleText(), "Products");
        Assert.assertTrue(isElementDisplayed(navbarPage.shoppingCart));
    }

    @Test
    public void userCannotLogInInvalidUsername() {
        loginUserWithCredentials(loginPage.invalidUsername, loginPage.validPassword);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void userCannotLogInInvalidPassword() {
        loginUserWithCredentials(loginPage.validUsername, loginPage.invalidPassword);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void userCannotLogInEmptyUsernameField() {
        loginUserWithCredentials("", loginPage.validPassword);

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }

    @Test
    public void userCannotLogInEmptyPasswordField() {
        loginUserWithCredentials(loginPage.validUsername, "");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
    }

    @Test
    public void userCannotLogInEmptyFields() {
        loginUserWithCredentials("", "");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }
}
