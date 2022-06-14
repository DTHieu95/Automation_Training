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
    customerInfoPO customerInfoPage;
    myProductReviewPO myProductReviewPage;
    changePasswordPO changePasswordPage;
    addressPO addressPage;
    String emailAddress , password , newPass;
    String FN , LN ,Company , Country , State , City , Add1 , Add2 , Zip , Phone , Fax , fullName;

    @Parameters({"browser", "url"})

    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        emailAddress = "hieu" + getRandomNumber() + "@gmail.com";
        password = "12345678";
        newPass = "123456789";
        FN = "Hieu";
        LN = "DT";
        Company = "Jmango";
        Country = "United States";
        State = "California";
        City = "HN";
        Add1 = "NTT";
        Add2 = "HD";
        Zip = "10000";
        Phone = "098654321";
        Fax = "012445";
        fullName = FN + " " + LN;
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

        verifyEquals(homePage.getPageTitle(driver) , "My account - Customer info");

        verifyEquals(customerInfoPage.getAttributeInDynamicField(driver , "First name") , "Hieu");
        verifyEquals(customerInfoPage.getAttributeInDynamicField(driver , "Last name") , "DT");
        verifyEquals(customerInfoPage.getAttributeInDynamicField(driver , "Email") , emailAddress);

        customerInfoPage.checkDynamicRadioBox(driver , "Female");
        customerInfoPage.sendKeyToDynamicTextbox(driver , "First name" , "Hoa");
        customerInfoPage.sendKeyToDynamicTextbox(driver , "Last name" , "NP");
        customerInfoPage.sendKeyToDynamicTextbox(driver , "Email" , "hoanp@gmail.com");
        customerInfoPage.sendKeyToDynamicTextbox(driver , "Company name" , "Jmango");
        customerInfoPage.clickToDynamicButton(driver , "Save");

        verifyEquals(customerInfoPage.getAttributeInDynamicField(driver , "First name") , "Hoa");
        verifyEquals(customerInfoPage.getAttributeInDynamicField(driver , "Last name") , "Np");
        verifyEquals(customerInfoPage.getAttributeInDynamicField(driver , "Email") , "hoanp@gmail.com");
        verifyEquals(customerInfoPage.getAttributeInDynamicField(driver , "Company name") , "Jmango");
        verifyTrue(customerInfoPage.isDynamicRadioBoxSelected(driver , "Female"));
    }

    @Test
    public void TC_14(){
        customerInfoPage.clickToAccountMenuSide(driver , "Addresses");
        addressPage = pageGenerator.getAddressPage(driver);

        verifyEquals(addressPage.getPageTitle(driver) , "My account - Addresses");
        addressPage.clickToDynamicButton(driver , "Add new");
        verifyEquals(addressPage.getPageTitle(driver) , "My account - Add new address");
        addressPage.sendKeyToDynamicTextbox(driver , "First name" , FN);
        addressPage.sendKeyToDynamicTextbox(driver , "Last name" , LN);
        addressPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        addressPage.sendKeyToDynamicTextbox(driver , "Company" , Company);
        addressPage.selectValueInDynamicDropdownByLabel(driver , "Country" , Country);
        addressPage.selectValueInDynamicDropdownByLabel(driver , "State / province" , State);
        addressPage.sendKeyToDynamicTextbox(driver , "City" , City);
        addressPage.sendKeyToDynamicTextbox(driver , "Address 1" , Add1);
        addressPage.sendKeyToDynamicTextbox(driver , "Address 2" , Add2);
        addressPage.sendKeyToDynamicTextbox(driver , "Zip / postal code" , Zip);
        addressPage.sendKeyToDynamicTextbox(driver , "Phone number" , Phone);
        addressPage.sendKeyToDynamicTextbox(driver , "Fax number" , Fax);

        addressPage.clickToDynamicButton(driver , "Save");

        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "name") , fullName);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "email") , "Email:\n" + emailAddress);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "phone") , Phone);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "fax") , Fax);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "company") , Company);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "address1") , Add1);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "address2") , Add2);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "city-state-zip") , City + ", " + State + ", " + Zip);
        verifyEquals(addressPage.getInfoAddressByName(driver , fullName , "country") , Country);
    }

    @Test
    public void TC_15(){
        addressPage.clickToAccountMenuSide(driver , "Change password");
        changePasswordPage = pageGenerator.getChangePasswordPage(driver);
        verifyEquals(changePasswordPage.getPageTitle(driver) , "My account - Change password");
        changePasswordPage.sendKeyToDynamicTextbox(driver , "Old password" , password);
        changePasswordPage.sendKeyToDynamicTextbox(driver , "New password" , newPass);
        changePasswordPage.sendKeyToDynamicTextbox(driver , "Confirm password" , newPass);
        changePasswordPage.clickToDynamicButton(driver , "Change password");

        verifyEquals(changePasswordPage.getSuccessMsgBar(driver) , "Password was changed");
        changePasswordPage.clickIconCloseBarHeader(driver);
        changePasswordPage.clickToDynamicMenuHeader(driver , "Log out");
        homePage = pageGenerator.getHomePage(driver);
        verifyTrue(homePage.isWelcomeMsgDisplayed());

        homePage.clickToDynamicMenuHeader(driver , "Log in");
        loginPage = pageGenerator.getLoginPage(driver);

        loginPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        loginPage.sendKeyToDynamicTextbox(driver , "Password" , password);
        loginPage.clickToDynamicButton(driver , "Log in");
        verifyEquals(loginPage.getSummaryErrorMsg(driver) , "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

        loginPage.sendKeyToDynamicTextbox(driver , "Email" , emailAddress);
        loginPage.sendKeyToDynamicTextbox(driver , "Password" , newPass);
        loginPage.clickToDynamicButton(driver , "Log in");

        homePage = pageGenerator.getHomePage(driver);
        verifyTrue(homePage.isWelcomeMsgDisplayed());
    }

    @Test
    public void TC_16(){

    }
}
