package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(className = "error-message-container")
    public WebElement error;

    //-------------------------------------------------------------------------

    public String getErrorText() {
        return error.getText();
    }

    public void insertUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void insertPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }
}
