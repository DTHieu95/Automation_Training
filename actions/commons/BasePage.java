package commons;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.nopCommerce.nopComBaseUI;
import pageUIs.nopCommerce.registerPageUI;


public class BasePage {

    public static BasePage getBasePage() {
        return new BasePage();
    }

    public void openPageURL(WebDriver driver, String pageURL) {
        driver.get(pageURL);
    }



    public String getPageURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
        for (Cookie cookie : allCookies) {
            driver.manage().addCookie(cookie);
        }
    }

    public Alert waitAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        alert = waitAlertPresence(driver);
        alert.accept();
    }

    public void cancelAlert(WebDriver driver) {
        alert = waitAlertPresence(driver);
        alert.dismiss();
    }

    public void sendKeyToAlert(WebDriver driver, String value) {
        alert = waitAlertPresence(driver);
        alert.sendKeys(value);

    }

    public String getAlertText(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText();

    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowsID = driver.getWindowHandles();

        for (String id : allWindowsID) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String tabtitle) {
        Set<String> AllWindowID = driver.getWindowHandles();

        for (String id : AllWindowID) {
            driver.switchTo().window(id);
            String ActualTitle = driver.getTitle();
            if (ActualTitle.equals(tabtitle)) {
                break;
            }
        }
    }

    public void CloseAllWindowExceptParent(WebDriver driver, String parentID) {
        Set<String> AllWindowID = driver.getWindowHandles();

        for (String id : AllWindowID) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
    }

    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public WebElement getElement(WebDriver driver, String locator, String... params) {
        return driver.findElement(getByXpath(getDynamicLocator(locator, params)));
    }


    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public String getDynamicLocator(String locator, String... params) {
        return String.format(locator, (Object[]) params);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... params) {
        getElement(driver, getDynamicLocator(locator, params)).click();

    }

    public void sendKeyToElement(WebDriver driver, String locator, String value) {
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
        ;
    }

    public void sendKeyToElement(WebDriver driver, String locator, String value, String... params) {
        getElement(driver, getDynamicLocator(locator, params)).clear();
        getElement(driver, getDynamicLocator(locator, params)).sendKeys(value);
        ;
    }

    public int getElementsize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    public int getElementsize(WebDriver driver, String locator, String... params) {
        return getElements(driver, getDynamicLocator(locator, params)).size();
    }

    public void selectDropDownByText(WebDriver driver, String locator, String Text) {
        select = new Select(getElement(driver, locator));
        select.selectByVisibleText(Text);
    }

    public void selectDropDownByText(WebDriver driver, String locator, String Text, String... params) {
        select = new Select(getElement(driver, getDynamicLocator(locator, params)));
        select.selectByVisibleText(Text);
    }

    public String getSelectedItemFromDropDown(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption().getText();

    }

    public String getSelectedItemFromDropDown(WebDriver driver, String locator, String... params) {
        select = new Select(getElement(driver, getDynamicLocator(locator, params)));
        return select.getFirstSelectedOption().getText();

    }

    public boolean isDropDownMultiPle(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);
        explicitWait = new WebDriverWait(driver, timeout);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childLocator);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
            }
        }
    }

    public void selectMultiItemInDropdown(WebDriver driver, String parentLocator, String childLocator, String[] expectedValueItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);

        explicitWait = new WebDriverWait(driver, timeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

        for (WebElement childElement : allItems) {
            for (String item : expectedValueItem) {
                if (childElement.getText().trim().equals(item)) {
                    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
                    sleepInSecond(1);

                    childElement.click();
                    sleepInSecond(1);
                }
            }
        }
    }


    public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }


    public String getAttributeValue(WebDriver driver, String locator, String attributeName, String... params) {
        return getElement(driver, getDynamicLocator(locator, params)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator) {

        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... params) {

        return getElement(driver, locator).getText();
    }

    public void checktoCheckBoxOrRadio(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    public void checktoCheckBoxOrRadio(WebDriver driver, String locator, String... params) {
        if (!isElementSelected(driver, getDynamicLocator(locator, params))) {
            getElement(driver, getDynamicLocator(locator, params)).click();
        }
    }

    public void unCheckCheckBox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    public void unCheckCheckBox(WebDriver driver, String locator, String... params) {
        locator = getDynamicLocator(locator, params);
        if (isElementSelected(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
        return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getElements(driver, locator);
        overrideGlobalTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM But not displayed");
            return true;

        } else {
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator , String... params) {
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getElements(driver, getDynamicLocator(locator , params));
        overrideGlobalTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM But not displayed");
            return true;

        } else {
            return false;
        }
    }

    public void overrideGlobalTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public boolean isElementEnable(WebDriver driver, String locator) {

        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementEnable(WebDriver driver, String locator, String... params) {

        return getElement(driver, getDynamicLocator(locator, params)).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... params) {
        return getElement(driver, getDynamicLocator(locator, params)).isSelected();
    }

    public boolean isResultContainKeyword(WebDriver driver, String locator, String keyword) {
        boolean status;
        if (!getElementText(driver, locator).contains(keyword)) {
            status = false;
            return status;
        } else {
            status = true;
        }
        return status;
    }


    public WebDriver switchToFrameByElement(WebDriver driver, String locator) {
        return driver.switchTo().frame(getElement(driver, locator));
    }

    public WebDriver switchToDefaultContent(WebDriver driver) {

        return driver.switchTo().defaultContent();
    }

    public void hoverElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }

    public void hoverElement(WebDriver driver, String locator, String... params) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, getDynamicLocator(locator, params))).perform();
    }


    public void doubleClick(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(getElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }

    public void DragAndDrop(WebDriver driver, String sourcelocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(getElement(driver, sourcelocator), getElement(driver, targetLocator)).perform();

    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... params) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, getDynamicLocator(locator, params)), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;

        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;

        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;

        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;

        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;

        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public boolean areJqueryandJSLoadSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryload = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> JSLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryload) && explicitWait.until(JSLoad);

    }

    public boolean isJQueryLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeout);
        jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
        return explicitWait.until(jQueryLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;

        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;

        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isImageLoaded(WebDriver driver, String locator, String... params) {
        jsExecutor = (JavascriptExecutor) driver;

        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, getDynamicLocator(locator, params)));
        return status;
    }

    public void getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("$(document.evaluate(\"" + xpathLocator + "\" , document , null , XPathResult.FIRST_ORDERED_NODE_TYPE , null).singleNodeValue).val()");

    }

    public void waitElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));

    }

    public void waitElementVisible(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));

    }

    public void waitAllElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));

    }

    public void waitElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public void waitElementClickable(WebDriver driver, String locator, String... params) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
    }

    public void waitElementInvisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));

    }

    public int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    //NopCommerce
    public void clickToDynamicMenuHeader(WebDriver driver , String menuName){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_MENU_HEADER , menuName);
        clickToElement(driver , nopComBaseUI.DYNAMIC_MENU_HEADER , menuName);
    }

    public void sendKeyToDynamicTextbox(WebDriver driver ,String labelField , String value){
        waitElementVisible(driver , nopComBaseUI.DYNAMIC_TEXTBOX_BY_LABEL , labelField);
        sendKeyToElement(driver , nopComBaseUI.DYNAMIC_TEXTBOX_BY_LABEL , value , labelField);
    }

    public void clickToDynamicButton(WebDriver driver ,String buttonName){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_BUTTON_BY_TEXT , buttonName);
        clickToElement(driver , nopComBaseUI.DYNAMIC_BUTTON_BY_TEXT ,buttonName);
    }

    public String getPageTitle(WebDriver driver){
        waitElementVisible(driver , nopComBaseUI.PAGE_TITLE);
        return getElementText(driver , nopComBaseUI.PAGE_TITLE);
    }

    public String getErrorMessageByID(WebDriver driver , String errorID){
        waitElementVisible(driver , nopComBaseUI.DYNAMIC_ERROR_MESSAGE_TEXTBOX , errorID);
        return getElementText(driver , nopComBaseUI.DYNAMIC_ERROR_MESSAGE_TEXTBOX , errorID).trim();
    }

    public String getSummaryErrorMsg(WebDriver driver){
        waitElementVisible(driver , nopComBaseUI.SUMMARY_ERROR_MSG);
        return getElementText(driver , nopComBaseUI.SUMMARY_ERROR_MSG);
    }

    public String getAttributeInDynamicField(WebDriver driver , String labelName){
        waitElementVisible(driver , nopComBaseUI.DYNAMIC_TEXTBOX_BY_LABEL , labelName);
        return getAttributeValue(driver , nopComBaseUI.DYNAMIC_TEXTBOX_BY_LABEL , "value" , labelName);
    }

    public void selectValueInDynamicDropdown(WebDriver driver , String dropdownName , String value){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_DROPDOWN_BY_NAME , dropdownName);
        selectDropDownByText(driver , nopComBaseUI.DYNAMIC_DROPDOWN_BY_NAME , value , dropdownName);
    }

    public void selectValueInDynamicDropdownByLabel(WebDriver driver , String dropdownLabel , String value){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_DROPDOWN_BY_LABEL , dropdownLabel);
        selectDropDownByText(driver , nopComBaseUI.DYNAMIC_DROPDOWN_BY_LABEL , value , dropdownLabel);
    }

    public void clickToAccountMenuSide(WebDriver driver , String menuName){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_ACCOUNT_MENU_SIDE , menuName);
        clickToElement(driver , nopComBaseUI.DYNAMIC_ACCOUNT_MENU_SIDE , menuName);
    }

    public void checkDynamicRadioBox(WebDriver driver , String labelName){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_RADIO_BOX_LABEL , labelName);
        checktoCheckBoxOrRadio(driver , nopComBaseUI.DYNAMIC_RADIO_BOX_LABEL , labelName);
    }

    public String getSelectedValueInDynamicDropdown(WebDriver driver , String dropdownName){
        waitElementVisible(driver , nopComBaseUI.DYNAMIC_DROPDOWN_BY_NAME , dropdownName);
        return getSelectedItemFromDropDown(driver , nopComBaseUI.DYNAMIC_DROPDOWN_BY_NAME , dropdownName);
    }

    public boolean isDynamicRadioBoxSelected(WebDriver driver , String labelName){
        waitElementVisible(driver , nopComBaseUI.DYNAMIC_RADIO_BOX_LABEL , labelName);
        return isElementSelected(driver , nopComBaseUI.DYNAMIC_RADIO_BOX_LABEL , labelName);
    }

    public String getSuccessMsgBar(WebDriver driver){
        waitElementVisible(driver , nopComBaseUI.SUCCESS_MSG_BAR);
        return getElementText(driver , nopComBaseUI.SUCCESS_MSG_BAR);
    }

    public void clickIconCloseBarHeader(WebDriver driver){
        waitElementClickable(driver , nopComBaseUI.ICON_CLOSE_BAR_HEADER);
        clickToElement(driver , nopComBaseUI.ICON_CLOSE_BAR_HEADER);
    }

    public void clickToDynamicFooterLink(WebDriver driver , String menuName) {
        waitElementClickable(driver, nopComBaseUI.DYNAMIC_FOOTER_LINK, menuName);
        clickToElement(driver, nopComBaseUI.DYNAMIC_FOOTER_LINK, menuName);
    }

    public void clickToDynamicLinkText(WebDriver driver , String linkText){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_LINK_TEXT , linkText);
        clickToElement(driver , nopComBaseUI.DYNAMIC_LINK_TEXT , linkText);
    }

    public void checkToDynamicCheckbox(WebDriver driver , String label){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_CHECK_BOX , label);
        checktoCheckBoxOrRadio(driver , nopComBaseUI.DYNAMIC_CHECK_BOX , label);
    }

    public void unCheckDynamicCheckbox(WebDriver driver , String label){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_CHECK_BOX , label);
        unCheckCheckBox(driver , nopComBaseUI.DYNAMIC_CHECK_BOX , label);
    }

    public void hoverMenuAndClickToSubListMenu(WebDriver driver , String menuName , String subListMenu){
        waitElementClickable(driver , nopComBaseUI.DYNAMIC_HEADER_MENU , menuName);
        hoverElement(driver , nopComBaseUI.DYNAMIC_HEADER_MENU , menuName);

        waitElementClickable(driver , nopComBaseUI.DYNAMIC_SUBLIST_MENU , subListMenu);
        clickToElement(driver , nopComBaseUI.DYNAMIC_SUBLIST_MENU , subListMenu);
    }

    public boolean isProdNameSortAscending(WebDriver driver){
        ArrayList<String> productUIList = new ArrayList<String>();

        List<WebElement> prodNameList = getElements(driver, nopComBaseUI.PRODUCT_NAME_TEXT);
        for (WebElement prodName : prodNameList){
            productUIList.add(prodName.getText());
        }

        ArrayList<String> prodSortList = new ArrayList<String>();
        for (String product : productUIList){
            prodSortList.add(product);
        }
        Collections.sort(prodSortList);

        return productUIList.equals(prodSortList);
    }

    public boolean isProdNameSortDescending(WebDriver driver){
        ArrayList<String> productUIList = new ArrayList<String>();

        List<WebElement> prodNameList = getElements(driver, nopComBaseUI.PRODUCT_NAME_TEXT);
        for (WebElement prodName : prodNameList){
            productUIList.add(prodName.getText());
        }

        ArrayList<String> prodNameSortList = new ArrayList<String>();
        for (String product : productUIList){
            prodNameSortList.add(product);
        }
        Collections.sort(prodNameSortList);
        Collections.reverse(prodNameSortList);

        return productUIList.equals(prodNameSortList);
    }

    public boolean isProdPriceSortAscending(WebDriver driver){
        ArrayList<Float> priceUIList = new ArrayList<Float>();

        List<WebElement> prodPriceList = getElements(driver , nopComBaseUI.PRODUCT_PRICE);
        for (WebElement prodPrice : prodPriceList){
            priceUIList.add(Float.parseFloat(prodPrice.getText().replace("$" , "")));
        }

        ArrayList<Float> prodPriceSortList = new ArrayList<Float>();
        for (Float price : priceUIList){
            prodPriceSortList.add(price);
        }

        Collections.sort(prodPriceSortList);

        return priceUIList.equals(prodPriceSortList);
    }

    public boolean isProdPriceSortDescending(WebDriver driver){
        ArrayList<Float> priceUIList = new ArrayList<Float>();

        List<WebElement> prodPriceList = getElements(driver , nopComBaseUI.PRODUCT_PRICE);
        for (WebElement prodPrice : prodPriceList){
            priceUIList.add(Float.parseFloat(prodPrice.getText().replace("$" , "")));
        }

        ArrayList<Float> prodPriceSortList = new ArrayList<Float>();
        for (Float price : priceUIList){
            prodPriceSortList.add(price);
        }

        Collections.sort(prodPriceSortList);
        Collections.reverse(prodPriceSortList);

        return priceUIList.equals(prodPriceSortList);
    }

    public boolean isNumberProdDisplayedCorrect(WebDriver driver , int expectNumber){
        waitElementVisible(driver , nopComBaseUI.PRODUCT_NAME_TEXT);
        boolean status = false;
        if (getElementsize(driver , nopComBaseUI.PRODUCT_NAME_TEXT) > expectNumber){
            status = false;
            return status;
        }else {
            status = true;
        }
        return status;
    }

    public String getCurrentPage(WebDriver driver){
        waitElementVisible(driver , nopComBaseUI.CURRENT_PAGE);
        return getElementText(driver , nopComBaseUI.CURRENT_PAGE);
    }

    public boolean isDynamicIconPageDisplayed(WebDriver driver , String icon){
        waitElementVisible(driver , nopComBaseUI.DYNAMIC_ICON_PAGE , icon);
        return isElementDisplayed(driver , nopComBaseUI.DYNAMIC_ICON_PAGE , icon);
    }

    public boolean isDynamicIconPageUnDisplayed(WebDriver driver , String icon){
        return isElementUndisplayed(driver , nopComBaseUI.DYNAMIC_ICON_PAGE , icon);
    }




    private Alert alert;
    private WebDriverWait explicitWait;
    private long timeout = 15;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private Select select;
    private JavascriptExecutor jsExecutor;
    private Actions action;
}