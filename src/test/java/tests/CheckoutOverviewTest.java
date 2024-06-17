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
        driver.get("https://www.saucedemo.com/");

        loginUserWithCredentials("standard_user", "secret_sauce");
        resetAppState();

        inventoryPage.addProductInCart("Sauce Labs Bolt T-Shirt");
        inventoryPage.addProductInCart("Sauce Labs Onesie");
        navbarPage.clickOnShoppingCart();
        cartPage.clickOnCheckoutButton();
        fillCheckoutForm("Petar", "Petrovic", "11000");
    }

    @Test
    public void userIsOnCheckoutStepTwoPage() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertEquals(navbarPage.getTitleText(), "Checkout: Overview");
    }

    @Test
    public void itemTotalPriceIsCorrect() {
        List<String> productsPrices = cartPage.getALlProductsPrices();

        Assert.assertEquals(cartPage.sumAllProductPrices(productsPrices), 23.98);
    }

    @Test
    public void taxIsCorrect() {
        List<String> productsPrices = cartPage.getALlProductsPrices();
        double priceOfProducts = cartPage.sumAllProductPrices(productsPrices);

        Assert.assertEquals(cartPage.calculateTax(priceOfProducts), 1.92);
    }

    @Test
    public void totalPriceIsCorrect() {
        List<String> productsPrices = cartPage.getALlProductsPrices();
        double priceOfProducts = cartPage.sumAllProductPrices(productsPrices);
        double tax = cartPage.calculateTax(priceOfProducts);

        Assert.assertEquals((priceOfProducts + tax), 25.90);
    }
}
