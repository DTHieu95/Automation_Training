package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class loginPO extends BasePage {
    WebDriver driver;

    public loginPO(WebDriver driver){
        this.driver = driver;
    }
}
