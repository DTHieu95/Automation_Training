package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.compareProdPageUI;

public class compareProdPO extends BasePage {
    WebDriver driver;

    public compareProdPO(WebDriver driver){
        this.driver = driver;
    }

    public String getTextInDynamicTable(String className , String columnIndex){
        waitElementVisible(driver , compareProdPageUI.DYNAMIC_VALUE_TABLE , className , columnIndex);
        return getElementText(driver ,compareProdPageUI.DYNAMIC_VALUE_TABLE , className , columnIndex);
    }

    public String getAttributeDynamicTable(String attribute , String className , String columnIndex){
        waitElementVisible(driver , compareProdPageUI.DYNAMIC_VALUE_TABLE , className , columnIndex);
        return getAttributeValue(driver , compareProdPageUI.DYNAMIC_VALUE_TABLE , attribute , className , columnIndex);
    }
}
