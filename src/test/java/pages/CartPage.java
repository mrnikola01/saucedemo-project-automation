package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart_list .cart_item")
    public List<WebElement> products;

    //-------------------------------------------------------------------------

    public String getProductName(WebElement product) {
        return product.findElement(By.className("inventory_item_name")).getText();
    }

    public String getProductPrice(WebElement product) {
        return product.findElement(By.className("inventory_item_price")).getText();
    }
}
