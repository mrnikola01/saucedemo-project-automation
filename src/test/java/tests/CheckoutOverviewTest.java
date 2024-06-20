package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutOverviewTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get(loginPageURL);

        loginUserWithCredentials(loginPage.validUsername, loginPage.validPassword);
        resetAppState();

        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm(checkoutStepOnePage.validFirstName, checkoutStepOnePage.validLastName, checkoutStepOnePage.validPostalCode);
    }

    @Test
    public void userIsOnCheckoutStepTwoPage() {
        Assert.assertEquals(driver.getCurrentUrl(), checkoutStepTwoPageURL);
        Assert.assertEquals(navbarPage.getTitleText(), "Checkout: Overview");
    }

    @Test
    public void itemTotalPriceIsCorrect() {
        List<String> productsPrices = cartPage.getALlProductsPrices();

        Assert.assertEquals(checkoutStepTwoPage.getItemTotalPrice(), cartPage.sumAllProductsPrices(productsPrices));
    }

    @Test
    public void taxIsCorrect() {
        List<String> productsPrices = cartPage.getALlProductsPrices();
        double priceOfProducts = cartPage.sumAllProductsPrices(productsPrices);

        Assert.assertEquals(checkoutStepTwoPage.getTax(), cartPage.calculateTax(priceOfProducts));
    }

    @Test
    public void totalPriceIsCorrect() {
        List<String> productsPrices = cartPage.getALlProductsPrices();
        double priceOfProducts = cartPage.sumAllProductsPrices(productsPrices);
        double tax = cartPage.calculateTax(priceOfProducts);

        Assert.assertEquals(checkoutStepTwoPage.getTotalPrice(), (priceOfProducts + tax));
    }
}
