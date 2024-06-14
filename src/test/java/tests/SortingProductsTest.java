package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SortingProductsTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void userCanSortProductsPriceLowToHigh() {
        loginUserWithCredentials("standard_user", "secret_sauce");
        inventoryPage.selectOptionFromProductsSorter("Price (low to high)");
        List<String> productsPrices = inventoryPage.getALlProductsPrices();

        Assert.assertTrue(inventoryPage.isProductsSortedPriceLowToHigh(productsPrices));
    }

    @Test
    public void userCanSortProductsPriceHighToLow() {
        loginUserWithCredentials("standard_user", "secret_sauce");
        inventoryPage.selectOptionFromProductsSorter("Price (high to low)");
        List<String> productsPrices = inventoryPage.getALlProductsPrices();

        Assert.assertTrue(inventoryPage.isProductsSortedPriceHighToLow(productsPrices));
    }

    @Test
    public void userCanSortProductsAToZ() {
        loginUserWithCredentials("standard_user", "secret_sauce");
        inventoryPage.selectOptionFromProductsSorter("Name (A to Z)");
        List<String> productsNames = inventoryPage.getAllProductsNames();

        Assert.assertTrue(inventoryPage.isProductsSortedAToZ(productsNames));
    }

    @Test
    public void userCanSortProductsZToA() {
        loginUserWithCredentials("standard_user", "secret_sauce");
        inventoryPage.selectOptionFromProductsSorter("Name (Z to A)");
        List<String> productsNames = inventoryPage.getAllProductsNames();

        Assert.assertTrue(inventoryPage.isProductsSortedZToA(productsNames));
    }
}
