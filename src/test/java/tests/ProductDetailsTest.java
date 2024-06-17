package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginUserWithCredentials("standard_user", "secret_sauce");
    }

    @Test
    public void productDetailsAreCorrect() {
        inventoryPage.clickOnProduct("Sauce Labs Fleece Jacket");

        Assert.assertEquals(productPage.getProductName(), inventoryPage.getClickedProductName());
        Assert.assertEquals(productPage.getProductPrice(), inventoryPage.getClickedProductPrice());
    }
}
