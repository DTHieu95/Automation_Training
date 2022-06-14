package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.nopCommerce.searchPageUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class searchPO extends BasePage {
    WebDriver driver;

    public searchPO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isProductSearchResultsCorrect(ArrayList<String> listProdExpect){
        ArrayList<String> productList = new ArrayList<String>();

        List<WebElement> prodNameList = getElements(driver , searchPageUI.PRODUCT_NAME_LINK);
        for (WebElement prodName : prodNameList){
            productList.add(prodName.getText());
        }
        Collections.sort(productList);
        Collections.sort(listProdExpect);
        return productList.equals(listProdExpect);
    }

    public boolean isProductSearchResultCorrect(String productExpected){
        waitElementVisible(driver , searchPageUI.PRODUCT_NAME_LINK);
        boolean status = false;
        if (!getElement(driver , searchPageUI.PRODUCT_NAME_LINK).getText().equals(productExpected)){
            status = false;
            return status;
        }else {
            status = true;
        }
        return status;
    }

    public boolean isMsgWarningDisplayed(){
        waitElementVisible(driver , searchPageUI.MSG_WARNING);
        return isElementDisplayed(driver , searchPageUI.MSG_WARNING);
    }

    public boolean isMsgNoResultDisplayed(){
        waitElementVisible(driver , searchPageUI.MSG_NO_RESULT);
        return isElementDisplayed(driver , searchPageUI.MSG_NO_RESULT);
    }


}
