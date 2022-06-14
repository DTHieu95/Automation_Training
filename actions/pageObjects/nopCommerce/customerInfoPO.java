package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;



public class customerInfoPO extends BasePage {
    WebDriver driver;

    public customerInfoPO(WebDriver driver){
        this.driver = driver;
    }
}
