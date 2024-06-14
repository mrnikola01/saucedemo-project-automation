package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_list .inventory_item")
    public List<WebElement> products;

    //-------------------------------------------------------------------------

    public String getProductName(WebElement product) {
        return product.findElement(By.className("inventory_item_name")).getText();
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
}
