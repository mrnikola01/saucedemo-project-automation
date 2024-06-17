package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage extends BaseTest {

    public FooterPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[data-test='social-twitter'")
    public WebElement xIcon;

    @FindBy(css = "[data-test='social-facebook'")
    public WebElement facebookIcon;

    @FindBy(css = "[data-test='social-linkedin'")
    public WebElement linkedinIcon;

    //-------------------------------------------------------------------------

    public void clickOnXIcon() {
        xIcon.click();
    }

    public void clickOnFacebookIcon() {
        facebookIcon.click();
    }

    public void clickOnLinkedinIcon() {
        linkedinIcon.click();
    }
}
