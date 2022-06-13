package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.nopComBaseUI;


public class nopComBasePO extends BasePage {

    WebDriver driver;

    public nopComBasePO(WebDriver driver){
        this.driver = driver;
    }


}
