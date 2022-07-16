package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.shopCartPageUI;


public class shopCartPO extends BasePage {
    WebDriver driver;

    public shopCartPO(WebDriver driver){
        this.driver = driver;
    }

    public void clickToDynamicIconRemoveInCart(String prodName){
        waitElementClickable(driver , shopCartPageUI.DYNAMIC_ICON_REMOVE , prodName);
        clickToElement(driver , shopCartPageUI.DYNAMIC_ICON_REMOVE , prodName);
    }
}
