package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;


public class shopCartPO extends BasePage {
    WebDriver driver;

    public shopCartPO(WebDriver driver){
        this.driver = driver;
    }
}
