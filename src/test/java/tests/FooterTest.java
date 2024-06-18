package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FooterTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginPageURL);
        mainWindow = driver.getWindowHandle();

        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);
    }

    @Test
    public void userCanClickOnXIcon() {
        footerPage.clickOnXIcon();
        switchToNewWindow();

        Assert.assertEquals(driver.getCurrentUrl(), xPageURL);
    }

    @Test
    public void userCanClickOnFacebookIcon() {
        footerPage.clickOnFacebookIcon();
        switchToNewWindow();

        Assert.assertEquals(driver.getCurrentUrl(), facebookPageURL);
    }

    @Test
    public void userCanClickOnLinkedinIcon() {
        footerPage.clickOnLinkedinIcon();
        switchToNewWindow();

        Assert.assertEquals(driver.getCurrentUrl(), linkedinPageURL);
    }
}
