package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFormTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);
        resetAppState();
    }

    @Test
    public void userIsOnCheckoutStepOnePage() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(navbarPage.getTitleText(), "Checkout: Your Information");
    }

    @Test
    public void userCanFillCheckoutForm() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("Petar", "Petrovic", "11000");

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
