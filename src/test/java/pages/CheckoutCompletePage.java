package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage extends BaseTest {

    public CheckoutCompletePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "pony_express")
    public WebElement successImage;

    @FindBy(className = "complete-header")
    public WebElement completeHeader;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    //-------------------------------------------------------------------------

    public String getCompleteHeaderText() {
        return completeHeader.getText();
    }

    public void clickOnBackHomeButton() {
        backHomeButton.click();
    }
}
