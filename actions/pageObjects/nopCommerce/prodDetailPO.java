package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.productDetailPageUI;


public class prodDetailPO extends BasePage {
    WebDriver driver;

    public prodDetailPO(WebDriver driver){
        this.driver = driver;
    }

    public String getDynamicInfoProduct(String idField){
        waitElementVisible(driver , productDetailPageUI.DYNAMIC_INFO_PRODUCT , idField);
        return getElementText(driver , productDetailPageUI.DYNAMIC_INFO_PRODUCT, idField).trim();
    }
}
