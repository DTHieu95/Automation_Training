package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.addressPageUI;


public class addressPO extends BasePage {
    WebDriver driver;

    public addressPO(WebDriver driver){
        this.driver = driver;
    }

    public String getInfoAddressByName(WebDriver driver , String fullName , String className){
        waitElementVisible(driver , addressPageUI.DYNAMIC_INFO_ADDRESS_BY_NAME , fullName , className);
        return getElementText(driver , addressPageUI.DYNAMIC_INFO_ADDRESS_BY_NAME , fullName , className);
    }
}
