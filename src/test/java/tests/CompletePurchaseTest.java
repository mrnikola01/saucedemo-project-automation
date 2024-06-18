package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CompletePurchaseTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);
        resetAppState();
    }

    @Test
    public void userCanCompletePurchase() {
        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("Petar", "Petrovic", "11000");
        checkoutStepTwoPage.clickOnFinishButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(navbarPage.getTitleText(), "Checkout: Complete!");
        Assert.assertEquals(checkoutCompletePage.getCompleteHeaderText(), "Thank you for your order!");
        Assert.assertTrue(isElementDisplayed(checkoutCompletePage.completeHeader));
    }
}
