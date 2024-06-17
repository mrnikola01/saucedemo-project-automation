package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwoPage extends BaseTest {

    public CheckoutStepTwoPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-test='subtotal-label']")
    public WebElement itemTotalPrice;

    @FindBy(css = "[data-test='tax-label']")
    public WebElement tax;

    @FindBy(css = "[data-test='total-label']")
    public WebElement totalPrice;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(id = "finish")
    public WebElement finishButton;

    //-------------------------------------------------------------------------

    public String getItemTotalPriceText() {
        return itemTotalPrice.getText();
    }

    public String getTaxText() {
        return tax.getText();
    }

    public String getTotalPriceText() {
        return totalPrice.getText();
    }

    public void clickOnCancelButton() {
        cancelButton.click();
    }

    public void clickOnFinishButton() {
        finishButton.click();
    }
}
