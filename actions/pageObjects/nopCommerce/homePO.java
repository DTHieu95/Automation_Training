package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.homePageUI;

public class homePO extends BasePage {
    WebDriver driver ;

    public homePO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isWelcomeMsgDisplayed(){
        waitElementVisible(driver , homePageUI.WELCOME_MSG);
        return isElementDisplayed(driver , homePageUI.WELCOME_MSG);
    }
}
