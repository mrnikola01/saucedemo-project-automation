package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginUserWithCredentials("standard_user", "secret_sauce");
        resetAppState();
    }

    @Test
    public void userCanFillCheckoutForm() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("Petar", "Petrovic", "11000");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertTrue(checkoutStepTwoPage.itemTotalPrice.isDisplayed());
        Assert.assertTrue(checkoutStepTwoPage.tax.isDisplayed());
    }

    @Test
    public void userCannotFillCheckoutFormEmptyFirstName() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("", "Petrovic", "11000");

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: First Name is required");
    }

    @Test
    public void userCannotFillCheckoutFormEmptyLastName() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("Petar", "", "11000");

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: Last Name is required");
    }

    @Test
    public void userCannotFillCheckoutFormEmptyPostalCode() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("Petar", "Petrovic", "");

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: Postal Code is required");
    }

    @Test
    public void userCannotFillCheckoutFormEmptyFields() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("", "", "");

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: First Name is required");
    }
}
