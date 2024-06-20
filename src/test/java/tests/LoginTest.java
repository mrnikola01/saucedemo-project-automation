package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginPageURL);
    }

    @Test(priority = 10)
    public void userCanLogIn() {
        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);

        Assert.assertEquals(driver.getCurrentUrl(), inventoryPageURL);
        Assert.assertEquals(navbarPage.getTitleText(), "Products");
        Assert.assertTrue(isElementDisplayed(navbarPage.shoppingCart));
    }

    @Test(priority = 20)
    public void userCannotLogInInvalidUsername() {
        loginUserWithCredentials(loginPage.invalidUsername, loginPage.validPassword);

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 30)
    public void userCannotLogInInvalidPassword() {
        loginUserWithCredentials(loginPage.validUsername, loginPage.invalidPassword);

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 40)
    public void userCannotLogInEmptyUsernameField() {
        loginUserWithCredentials("", loginPage.validPassword);

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }

    @Test(priority = 50)
    public void userCannotLogInEmptyPasswordField() {
        loginUserWithCredentials(loginPage.validUsername, "");

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
    }

    @Test(priority = 60)
    public void userCannotLogInEmptyFields() {
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), loginPageURL);
        Assert.assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }
}
