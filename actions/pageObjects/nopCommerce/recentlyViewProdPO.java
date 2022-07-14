package pageObjects.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.nopCommerce.recentlyViewProdPageUI;
import pageUIs.nopCommerce.searchPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class recentlyViewProdPO extends BasePage {
    WebDriver driver;

    public recentlyViewProdPO(WebDriver driver){
        this.driver = driver;
    }

    public boolean isListProdViewCorrect(ArrayList<String> listProdExpect){
        ArrayList<String> productList = new ArrayList<String>();

        List<WebElement> prodNameList = getElements(driver , recentlyViewProdPageUI.LIST_PRODUCT);
        for (WebElement prodName : prodNameList){
            productList.add(prodName.getText());
        }
        Collections.sort(productList);
        Collections.sort(listProdExpect);
        return productList.equals(listProdExpect);
    }
}
