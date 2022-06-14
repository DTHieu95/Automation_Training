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

    public static customerInfoPO getCustomerInfoPage(WebDriver driver){
        return new customerInfoPO(driver);
    }

    public static myProductReviewPO getMyProductReviewPage(WebDriver driver){
        return new myProductReviewPO(driver);
    }

    public static changePasswordPO getChangePasswordPage(WebDriver driver){
        return new changePasswordPO(driver);
    }

    public static addressPO getAddressPage(WebDriver driver){
        return new addressPO(driver);
    }

    public static prodDetailPO getProdDetailPage(WebDriver driver){
        return new prodDetailPO(driver);
    }

    public static searchPO getSearchPage(WebDriver driver){
        return new searchPO(driver);
    }

    public static shopCartPO getShopCartPage(WebDriver driver){
        return new shopCartPO(driver);
    }


}
