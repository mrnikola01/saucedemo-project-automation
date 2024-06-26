package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginPageURL);

        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);
    }

    @Test
    public void userCanClickOnProduct() {
        inventoryPage.clickOnProduct("Sauce Labs Fleece Jacket");

        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.saucedemo.com/inventory-item.html?id="));
        Assert.assertTrue(isElementDisplayed(productPage.name));
        Assert.assertTrue(isElementDisplayed(productPage.description));
    }

    @Test
    public void productDetailsAreCorrect() {
        inventoryPage.clickOnProduct("Sauce Labs Fleece Jacket");

        Assert.assertEquals(productPage.getProductName(), inventoryPage.getClickedProductName());
        Assert.assertEquals(productPage.getProductPrice(), inventoryPage.getClickedProductPrice());
        Assert.assertTrue(isElementDisplayed(productPage.description));
    }
}
