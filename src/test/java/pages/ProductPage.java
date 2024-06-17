package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BaseTest {

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-test='inventory-item-name'")
    public WebElement name;

    @FindBy(css = "[data-test='inventory-item-price']")
    public WebElement price;

    @FindBy(css = "[data-test='inventory-item-desc']")
    public WebElement description;

    @FindBy(css = "[data-test='add-to-cart']")
    public WebElement button;

    //-------------------------------------------------------------------------

    public String getProductName() {
        return name.getText();
    }

    public String getProductPrice() {
        return price.getText();
    }

    public String getDescriptionText() {
        return description.getText();
    }

    public void clickOnProductButton() {
        button.click();
    }
}
