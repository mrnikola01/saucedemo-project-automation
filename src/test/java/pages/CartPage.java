package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart_list .cart_item")
    public List<WebElement> products;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppingButton;

    //-------------------------------------------------------------------------

    public List<String> getALlProductsPrices() {
        List<String> productsPrices = new ArrayList<>();

        for(WebElement product : products) {
            productsPrices.add(getProductPrice(product));
        }

        return productsPrices;
    }

    // Converting the list of prices from String to Integer (without $ symbol) and returning the sum of them
    public double sumAllProductsPrices(List<String> prices) {
        double sum = 0;

        for(String price : prices) {
            double currentPrice = Double.parseDouble(price.replace("$", ""));
            sum += currentPrice;
        }

        return sum;
    }

    // Calculating the tax - > product's price * 0.08 (rounded to 2 decimals)
    public double calculateTax(double price) {
        return (double) Math.round((price * 0.08) * 100) / 100;
    }

    public String getProductName(WebElement product) {
        return product.findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductPrice(WebElement product) {
        return product.findElement(By.className("inventory_item_price")).getText();
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }

    public void clickOnContinueShoppingButton() {
        continueShoppingButton.click();
    }
}
