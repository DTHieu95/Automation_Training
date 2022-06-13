package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.registerPageUI;

public class registerPO extends BasePage {
    WebDriver driver;

    public registerPO(WebDriver driver){
        this.driver = driver;
    }


    public boolean isRegisterSuccessMsgDisplayed(){
        waitElementVisible(driver , registerPageUI.REGISTER_SUCCESS_MSG);
        return isElementDisplayed(driver , registerPageUI.REGISTER_SUCCESS_MSG);
    }

}
