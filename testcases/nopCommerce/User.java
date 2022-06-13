package nopCommerce;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.registerPO;

public class User extends BaseTest {
    WebDriver driver;

    loginPO loginPage;
    registerPO registerPage;
    homePO homePage;
    String emailAddress , password;

    @Parameters({"browser", "url"})

    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        emailAddress = "hieu" + getRandomNumber() + "@gmail.com";
        password = "123456";
        homePage = pageGenerator.getHomePage(driver);
        verifyTrue(homePage.isWelcomeMsgDisplayed());
        homePage.clickToDynamicMenuHeader(driver , "Register");
        registerPage = pageGenerator.getRegisterPage(driver);
    }

    @Test
    public void TC_01(){
        registerPage.clickToDynamicButton(driver , "Register");

        verifyEquals(registerPage.getErrorMessageByID(driver ,"FirstName") , "First name is required.");
        verifyEquals(registerPage.getErrorMessageByID(driver ,"LastName") , "Last name is required.");
        verifyEquals(registerPage.getErrorMessageByID(driver ,"Email") , "Email is required.");
        verifyEquals(registerPage.getErrorMessageByID(driver ,"Password") , "Password is required.");
        verifyEquals(registerPage.getErrorMessageByID(driver ,"ConfirmPassword") , "Password is required.");
    }

    @Test
    public void TC_02(){
        registerPage.sendKeyToDynamicTextbox(driver , "Email" , "hieu");
        registerPage.clickToDynamicButton(driver , "Register");
        verifyEquals(registerPage.getErrorMessageByID(driver ,"Email") , "Wrong email");
    }

    @Test
    public void TC_03(){
        registerPage.sendKeyToDynamicTextbox(driver , "First name" , "Hieu");
        registerPage.sendKeyToDynamicTextbox(driver , "Last name" , "DT");
        registerPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        registerPage.sendKeyToDynamicTextbox(driver , "Password" , password);
        registerPage.sendKeyToDynamicTextbox(driver , "Confirm password" , password);
        registerPage.clickToDynamicButton(driver , "Register");

        verifyTrue(registerPage.isRegisterSuccessMsgDisplayed());
        registerPage.clickToDynamicButton(driver , "Continue");
        homePage = pageGenerator.getHomePage(driver);
        verifyTrue(homePage.isWelcomeMsgDisplayed());
        homePage.clickToDynamicMenuHeader(driver , "Log out");
        verifyTrue(homePage.isWelcomeMsgDisplayed());
    }

    @Test
    public void TC_04(){
        homePage.clickToDynamicMenuHeader(driver , "Register");
        registerPage = pageGenerator.getRegisterPage(driver);

        registerPage.sendKeyToDynamicTextbox(driver , "First name" , "Hieu");
        registerPage.sendKeyToDynamicTextbox(driver , "Last name" , "DT");
        registerPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        registerPage.sendKeyToDynamicTextbox(driver , "Password" , password);
        registerPage.sendKeyToDynamicTextbox(driver , "Confirm password" , password);
        registerPage.clickToDynamicButton(driver , "Register");

        verifyEquals(registerPage.getSummaryErrorMsg(driver) , "The specified email already exists");
    }

    @Test
    public void TC_05(){
        registerPage.sendKeyToDynamicTextbox(driver , "Password" , password);
        registerPage.clickToDynamicButton(driver , "Register");

        verifyEquals(registerPage.getErrorMessageByID(driver ,"Password") , "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void TC_06(){
        registerPage.sendKeyToDynamicTextbox(driver , "Password" , password);
        registerPage.sendKeyToDynamicTextbox(driver , "Confirm password" , "123456789");
        registerPage.clickToDynamicButton(driver , "Register");

        verifyEquals(registerPage.getErrorMessageByID(driver ,"ConfirmPassword") , "The password and confirmation password do not match.");
    }

    @Test
    public void TC_07(){
        registerPage.clickToDynamicMenuHeader(driver , "Log in");
        loginPage = pageGenerator.getLoginPage(driver);

        verifyEquals(loginPage.getPageTitle(driver) , "Welcome, Please Sign In!");
        loginPage.clickToDynamicButton(driver , "Log in");

        verifyEquals(loginPage.getErrorMessageByID(driver  , "Email") , "Please enter your email");
    }

    @Test
    public void TC_08(){
        loginPage.sendKeyToDynamicTextbox(driver , "Email" , "Hieu");
        loginPage.clickToDynamicButton(driver , "Log in");
        verifyEquals(loginPage.getErrorMessageByID(driver  , "Email") , "Wrong email");
    }

    @Test
    public void TC_09(){
        loginPage.sendKeyToDynamicTextbox(driver , "Email" , "Hieu@gmail.com");
        loginPage.clickToDynamicButton(driver , "Log in");
        verifyEquals(loginPage.getSummaryErrorMsg(driver) , "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void TC_10(){
        loginPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        loginPage.clickToDynamicButton(driver , "Log in");
        verifyEquals(loginPage.getSummaryErrorMsg(driver) , "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void TC_11(){
        loginPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        loginPage.sendKeyToDynamicTextbox(driver , "Password" , "123456");
        loginPage.clickToDynamicButton(driver , "Log in");
        verifyEquals(loginPage.getSummaryErrorMsg(driver) , "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
    }

    @Test
    public void TC_12(){
        loginPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        loginPage.sendKeyToDynamicTextbox(driver , "Password" , password);
        loginPage.clickToDynamicButton(driver , "Log in");

        homePage = pageGenerator.getHomePage(driver);
        verifyTrue(homePage.isWelcomeMsgDisplayed());
    }

    @Test
    public void TC_13(){
        homePage.clickToDynamicMenuHeader(driver , "My account");
    }
}
