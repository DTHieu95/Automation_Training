package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;


public class computerPO extends BasePage {
    WebDriver driver;

    public computerPO(WebDriver driver){
        this.driver = driver;
    }
}
