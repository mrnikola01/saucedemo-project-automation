package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NavbarPage extends BaseTest {

    public NavbarPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement hamburgerMenu;

    @FindBy(className = "bm-item")
    public List<WebElement> hamburgerMenuItems;

    @FindBy(css = "[data-test='title']")
    public WebElement title;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCart;

    @FindBy(css = "[data-test='shopping-cart-badge']")
    public WebElement shoppingCartBadge;

    //-------------------------------------------------------------------------

    public void clickOnHamburgerMenu() {
        hamburgerMenu.click();
    }

    public void clickOnHamburgerMenuItem(String itemName) {
        waitForAllElementsVisibility(hamburgerMenuItems);

        for(WebElement item : hamburgerMenuItems) {
            if(item.getText().equals(itemName)) {
                item.click();
                break;
            }
        }
    }

    public void clickOnShoppingCart() {
        shoppingCart.click();
    }

    public boolean isShoppingCartDisplayed() {
        return shoppingCart.isDisplayed();
    }

    public String getTitleText() {
        return title.getText();
    }

    public String getShoppingCartBadgeText() {
        return shoppingCartBadge.getText();
    }
}
