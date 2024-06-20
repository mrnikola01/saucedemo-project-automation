package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFormTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginPageURL);

        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);
        resetAppState();
    }

    @Test(priority = 10)
    public void userIsOnCheckoutStepOnePage() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();

        Assert.assertEquals(driver.getCurrentUrl(), checkoutStepOnePageURL);
        Assert.assertEquals(navbarPage.getTitleText(), "Checkout: Your Information");
    }

    @Test(priority = 20)
    public void userCanFillCheckoutForm() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm(checkoutStepOnePage.validFirstName, checkoutStepOnePage.validLastName, checkoutStepOnePage.validPostalCode);

        Assert.assertTrue(checkoutStepTwoPage.itemTotalPrice.isDisplayed());
        Assert.assertTrue(checkoutStepTwoPage.tax.isDisplayed());
    }

    @Test(priority = 30)
    public void userCannotFillCheckoutFormEmptyFirstName() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("", checkoutStepOnePage.validLastName, checkoutStepOnePage.validPostalCode);

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: First Name is required");
    }

    @Test(priority = 40)
    public void userCannotFillCheckoutFormEmptyLastName() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm(checkoutStepOnePage.validFirstName, "", checkoutStepOnePage.validPostalCode);

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: Last Name is required");
    }

    @Test(priority = 50)
    public void userCannotFillCheckoutFormEmptyPostalCode() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm(checkoutStepOnePage.validFirstName, checkoutStepOnePage.validLastName, "");

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: Postal Code is required");
    }

    @Test(priority = 60)
    public void userCannotFillCheckoutFormEmptyFields() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        checkoutStepOnePage.clickOnContinueButton();

        Assert.assertTrue(isElementDisplayed(checkoutStepOnePage.error));
        Assert.assertEquals(checkoutStepOnePage.getErrorText(), "Error: First Name is required");
    }
}
