package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddProductsToCartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginUserWithCredentials("standard_user", "secret_sauce");
        resetAppState();
    }

    @Test(priority = 10)
    public void userCanAddProductInCart() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");

        Assert.assertEquals(navbarPage.getShoppingCartBadgeText(), "1");
    }

    @Test(priority = 20)
    public void userCanAddMoreProductsInCart() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        inventoryPage.addProductInCart("Sauce Labs Fleece Jacket");

        Assert.assertEquals(navbarPage.getShoppingCartBadgeText(), "3");

    }

    @Test(priority = 30)
    public void addedProductIsInCart() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        navbarPage.clickOnShoppingCart();
        WebElement product = cartPage.products.getFirst();

        Assert.assertEquals(cartPage.products.size(), 1);
        Assert.assertEquals(cartPage.getProductName(product), "Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(cartPage.getProductPrice(product), "$15.99");
    }

    @Test(priority = 40)
    public void addedProductsAreInCart() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        inventoryPage.addProductInCart("Sauce Labs Fleece Jacket");
        navbarPage.clickOnShoppingCart();

        WebElement firstProduct = cartPage.products.getFirst();
        WebElement secondProduct = cartPage.products.get(1);
        WebElement thirdProduct = cartPage.products.get(2);

        Assert.assertEquals(cartPage.products.size(), 3);
        Assert.assertEquals(cartPage.getProductName(firstProduct), "Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(cartPage.getProductPrice(firstProduct), "$15.99");
        Assert.assertEquals(cartPage.getProductName(secondProduct), "Sauce Labs Onesie");
        Assert.assertEquals(cartPage.getProductPrice(secondProduct), "$7.99");
        Assert.assertEquals(cartPage.getProductName(thirdProduct), "Sauce Labs Fleece Jacket");
        Assert.assertEquals(cartPage.getProductPrice(thirdProduct), "$49.99");
    }
}
