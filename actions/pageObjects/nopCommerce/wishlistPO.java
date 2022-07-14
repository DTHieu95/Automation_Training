package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.wishlistPageUI;

public class wishlistPO extends BasePage {
    WebDriver driver;

    public wishlistPO(WebDriver driver){
        this.driver = driver;
    }

    public void checkChexkboxDynamicProduct(String prodName){
        waitElementClickable(driver , wishlistPageUI.DYNAMIC_CHECKBOX_ADD_CART , prodName);
        checktoCheckBoxOrRadio(driver , wishlistPageUI.DYNAMIC_CHECKBOX_ADD_CART , prodName);
    }

    public void clickDynamicIconRemove(String prodName){
        waitElementClickable(driver , wishlistPageUI.DYNAMIC_ICON_REMOVE , prodName);
        clickToElement(driver , wishlistPageUI.DYNAMIC_ICON_REMOVE , prodName);
    }
}
