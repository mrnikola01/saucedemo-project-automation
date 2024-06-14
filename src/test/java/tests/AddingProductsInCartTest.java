package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddingProductsInCartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void userCanAddItemInCart() {
        loginUserWithCredentials("standard_user", "secret_sauce");

        inventoryPage.addProductInCart("Sauce Labs Onesie");
    }

}
