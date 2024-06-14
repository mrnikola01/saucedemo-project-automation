package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_list .inventory_item")
    public List<WebElement> products;

    @FindBy(css = "[data-test='product-sort-container']")
    public WebElement productsSorter;

    //-------------------------------------------------------------------------

    // Sorting products
    public Select selectProductsSorter() {
        return new Select(productsSorter);
    }

    public void selectOptionFromProductsSorter(String optionName) {
        selectProductsSorter().selectByVisibleText(optionName);
    }

    public boolean isProductsSortedPriceLowToHigh(List<String> prices) {
        double previousPrice = Double.MIN_VALUE;

        for(String price : prices) {
            double currentPrice = Double.parseDouble(price.replace("$", ""));

            if(currentPrice < previousPrice) return false;
            previousPrice = currentPrice;
        }

        return true;
    }

    public boolean isProductsSortedPriceHighToLow(List<String> prices) {
        double previousPrice = Double.MAX_VALUE;

        for(String price : prices) {
            double currentPrice = Double.parseDouble(price.replace("$", ""));

            if(currentPrice > previousPrice) return false;
            previousPrice = currentPrice;
        }

        return true;
    }

    public boolean isProductsSortedAToZ(List<String> names) {
        for(int i = 1; i < names.size(); i++) {
            String previousName = names.get(i - 1).toLowerCase();
            String currentName = names.get(i).toLowerCase();

            if(currentName.compareTo(previousName) < 0) return false;
        }

        return true;
    }

    public boolean isProductsSortedZToA(List<String> names) {
        for(int i = 1; i < names.size(); i++) {
            String previousName = names.get(i - 1).toLowerCase();
            String currentName = names.get(i).toLowerCase();

            if(currentName.compareTo(previousName) > 0) return false;
        }

        return true;
    }

    //-------------------------------------------------------------------------

    // Products
    public List<String> getALlProductsPrices() {
        List<String> productsPrices = new ArrayList<>();

        for(WebElement product : products) {
            productsPrices.add(getProductPrice(product));
        }

        return productsPrices;
    }

    public List<String> getAllProductsNames() {
        List<String> productsNames = new ArrayList<>();

        for(WebElement product : products) {
            productsNames.add(getProductName(product));
        }

        return productsNames;
    }

    public String getProductName(WebElement product) {
        return product.findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductPrice(WebElement product) {
        return product.findElement(By.className("inventory_item_price")).getText();
    }

    public void clickOnProductButton(WebElement product) {
        product.findElement(By.className("btn_inventory")).click();
    }

    public void addProductInCart(String productName) {
        for(WebElement product : products) {
            if(getProductName(product).equals(productName)) {
                clickOnProductButton(product);
                break;
            }
        }
    }

    public void removeProductFromCart(String productName) {
        for(WebElement product : products) {
            if(getProductName(product).equals(productName)) {
                clickOnProductButton(product);
                break;
            }
        }
    }
}
