package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

public class pageGenerator {

    public static loginPO getLoginPage(WebDriver driver){
        return new loginPO(driver);
    }

    public static nopComBasePO getBasePO(WebDriver driver){
        return new nopComBasePO(driver);
    }

    public static homePO getHomePage(WebDriver driver){
        return  new homePO(driver);
    }

    public static registerPO getRegisterPage(WebDriver driver){
        return new registerPO(driver);
    }
}
