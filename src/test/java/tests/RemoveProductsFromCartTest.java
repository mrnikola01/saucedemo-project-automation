package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveProductsFromCartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void userCanRemoveProductFromCart() {
        loginUserWithCredentials("standard_user", "secret_sauce");
        resetAppState();

        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.removeProductFromCart("Sauce Labs Bolt T-Shirt");

        Assert.assertFalse(isElementDisplayed(navbarPage.shoppingCartBadge));
    }
}
