package nopCommerce;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.*;
import pageObjects.nopCommerce.registerPO;

import java.util.ArrayList;
import java.util.Arrays;

public class User extends BaseTest {
    WebDriver driver;

    loginPO loginPage;
    registerPO registerPage;
    homePO homePage;
    customerInfoPO customerInfoPage;
    myProductReviewPO myProductReviewPage;
    changePasswordPO changePasswordPage;
    addressPO addressPage;
    prodDetailPO prodDetailPage;
    searchPO searchPage;
    shopCartPO shopCartPage;
    computerPO computerPage;
    wishlistPO wishlistPage;
    compareProdPO compareProdPage;
    recentlyViewProdPO recentlyViewProdPage;
    String emailAddress , password , newPass , prodName , prodName1 , skuProd, priceProd , prodSrc , compareSrc, compareSrc1;
    String FN , LN ,Company , Country , State , City , Add1 , Add2 , Zip , Phone , Fax , fullName;

    String prodView , prodView2 , prodView3 , prodView4 , prodView5;
    ArrayList<String> listProdView = new ArrayList<String>(Arrays.asList(prodView3 , prodView4 , prodView5));

    String processor , ram , HDD, OS , Software , compareProd;
    ArrayList<String> listProdLenovo = new ArrayList<String>(Arrays.asList("Lenovo IdeaCentre 600 All-in-One PC" , "Lenovo Thinkpad X1 Carbon Laptop"));

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
        prodName = "Build your own computer";
        prodName1 = "Apple MacBook Pro 13-inch";

        compareProd = "Digital Storm VANQUISH 3 Custom Performance PC";
        compareSrc = prodName.toLowerCase().replace(" " , "-");
        compareSrc1 = compareProd.toLowerCase().replace(" " , "-");


        processor = "2.2 GHz Intel Pentium Dual-Core E2200";
        ram = "2GB";
        HDD = "320GB";
        OS = "Vista Home [+$50.00]";
        Software = "Microsoft Office [+$50.00]";
        prodSrc = prodName1.toLowerCase().replace(" " , "-");
        compareSrc = compareProd.toLowerCase().replace(" " , "-");
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
        homePage.clickToDynamicLinkText(driver , prodName);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);
    }

    @Test
    public void TC_17(){
        homePage.clickToDynamicFooterLink(driver , "Search");
        searchPage = pageGenerator.getSearchPage(driver);
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isMsgWarningDisplayed());
    }

    @Test
    public void TC_18(){
        searchPage.sendKeyToDynamicTextbox(driver , "Search keyword" , "Macbook Pro 2050");
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isMsgNoResultDisplayed());
    }

    @Test
    public void TC_19(){
        searchPage.sendKeyToDynamicTextbox(driver , "Search keyword" , "Lenovo");
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isProductSearchResultsCorrect(listProdLenovo));
    }

    @Test
    public void TC_20(){
        searchPage.sendKeyToDynamicTextbox(driver , "Search keyword" , "Lenovo Thinkpad X1 Carbon Laptop");
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isProductSearchResultCorrect("Lenovo Thinkpad X1 Carbon Laptop"));
    }

    @Test
    public void TC_21(){
        searchPage.sendKeyToDynamicTextbox(driver , "Search keyword" , "Apple Macbook Pro");
        searchPage.checkToDynamicCheckbox(driver , "Advanced search");
        searchPage.selectValueInDynamicDropdownByLabel(driver , "Category" , "Computers");
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isMsgNoResultDisplayed());
    }

    @Test
    public void TC_22(){
        searchPage.checkToDynamicCheckbox(driver , "Automatically search sub categories");
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isProductSearchResultCorrect(prodName1));
    }

    @Test
    public void TC_23(){
        searchPage.selectValueInDynamicDropdownByLabel(driver , "Manufacturer" , "HP");
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isMsgNoResultDisplayed());
    }

    @Test
    public void TC_24(){
        searchPage.selectValueInDynamicDropdownByLabel(driver , "Manufacturer" , "Apple");
        searchPage.clickToDynamicButton(driver , "Search");
        verifyTrue(searchPage.isProductSearchResultCorrect(prodName1));
    }

    @Test
    public void TC_25(){
        searchPage.hoverMenuAndClickToSubListMenu(driver , "Computer" , "Notebooks");
        computerPage = pageGenerator.getComputerPage(driver);

        computerPage.selectValueInDynamicDropdown(driver , "products-orderby" , "Name: A to Z");
        verifyTrue(computerPage.isProdNameSortAscending(driver));

        computerPage.selectValueInDynamicDropdown(driver , "products-orderby" , "Name: Z to A");
        verifyTrue(computerPage.isProdNameSortDescending(driver));

        computerPage.selectValueInDynamicDropdown(driver , "products-orderby" , "Price: Low to High");
        verifyTrue(computerPage.isProdPriceSortAscending(driver));

        computerPage.selectValueInDynamicDropdown(driver , "products-orderby" , "Price: High to Low");
        verifyTrue(computerPage.isProdPriceSortDescending(driver));
    }

    @Test
    public void TC_26(){
        computerPage.selectValueInDynamicDropdown(driver , "products-pagesize" , "3");
        verifyTrue(computerPage.isNumberProdDisplayedCorrect(driver , 3));
        verifyEquals(computerPage.getCurrentPage(driver) , "1");
        verifyTrue(computerPage.isDynamicIconPageDisplayed(driver , "next"));

        computerPage.clickToDynamicLinkText(driver , "2");
        computerPage.selectValueInDynamicDropdown(driver , "products-pagesize" , "3");
        verifyEquals(computerPage.getCurrentPage(driver) , "2");
        verifyTrue(computerPage.isNumberProdDisplayedCorrect(driver , 3));
        verifyTrue(computerPage.isDynamicIconPageDisplayed(driver , "previous"));
    }

    @Test
    public void TC_27(){
        computerPage.selectValueInDynamicDropdown(driver , "products-pagesize" , "6");
        verifyTrue(computerPage.isNumberProdDisplayedCorrect(driver , 6));
        verifyTrue(computerPage.isDynamicIconPageUnDisplayed(driver , "next"));
        verifyTrue(computerPage.isDynamicIconPageUnDisplayed(driver , "previous"));

        computerPage.selectValueInDynamicDropdown(driver , "products-pagesize" , "9");
        verifyTrue(computerPage.isNumberProdDisplayedCorrect(driver , 9));
        verifyTrue(computerPage.isDynamicIconPageUnDisplayed(driver , "next"));
        verifyTrue(computerPage.isDynamicIconPageUnDisplayed(driver , "previous"));
    }

    @Test
    public void TC_28(){
        computerPage.clickToDynamicLinkText(driver , prodName1);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);

        skuProd = prodDetailPage.getDynamicInfoProduct("sku-4");
        priceProd = prodDetailPage.getDynamicInfoProduct("price-value-4");
        prodDetailPage.clickToDynamicButton(driver , "Add to wishlist");

        Assert.assertEquals(prodDetailPage.getSuccessMsgBar(driver) , "The product has been added to your\nwishlist");
        prodDetailPage.clickToDynamicLinkText(driver , "wishlist");

        wishlistPage = pageGenerator.getWishlistPage(driver);
        Assert.assertEquals(wishlistPage.getPageTitle(driver) , "Wishlist");
        Assert.assertEquals(wishlistPage.getQtyCartOrWishlist(driver , "wishlist") , "(2)");

        Assert.assertEquals(wishlistPage.getTextInDynamicTable(driver , "Wishlist" , "SKU" , "1") ,skuProd);
        Assert.assertTrue(wishlistPage.getAttributeValueInInDynamicTable(driver , "src" , "Wishlist" , "Image" , "1").contains(prodSrc));
        Assert.assertEquals(wishlistPage.getTextInDynamicTable(driver , "Wishlist" , "Product(s)" , "1") , prodName1);
        Assert.assertEquals(wishlistPage.getTextInDynamicTable(driver , "Wishlist" , "Price" , "1") , priceProd);
        Assert.assertEquals(wishlistPage.getAttributeValueInInDynamicTable(driver , "value" , "Wishlist" , "Qty" , "1") , "2");
        Assert.assertEquals(wishlistPage.getTextInDynamicTable(driver , "Wishlist" , "Total" , "1") , "$" + String.valueOf(Float.valueOf(priceProd.replace("$" , "")) * 2));

        wishlistPage.checkChexkboxDynamicProduct(prodName1);
        wishlistPage.clickToDynamicButton(driver , "Add to cart");

        shopCartPage = pageGenerator.getShopCartPage(driver);
        Assert.assertEquals(shopCartPage.getPageTitle(driver) , "Shopping cart");

        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "SKU" , "1") ,skuProd);
        Assert.assertTrue(shopCartPage.getAttributeValueInInDynamicTable(driver , "src" , "Shopping cart" , "Image" , "1").contains(prodSrc));
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Product(s)" , "1") , prodName1);
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Price" , "1") , priceProd);
        Assert.assertEquals(shopCartPage.getAttributeValueInInDynamicTable(driver , "value" , "Shopping cart" , "Qty" , "1") , "2");
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Total" , "1") , "$" + String.valueOf(Float.valueOf(priceProd.replace("$" , "")) * 2));

        Assert.assertEquals(shopCartPage.getQtyCartOrWishlist(driver , "wishlist") , "(0)");
        Assert.assertEquals(shopCartPage.getQtyCartOrWishlist(driver , "cart") , "(2)");
    }

    @Test
    public void TC_29(){
        shopCartPage.clickToDynamicButton(driver , "Continue shopping");
        homePage = pageGenerator.getHomePage(driver);
        Assert.assertTrue(homePage.isWelcomeMsgDisplayed());

        homePage.clickToDynamicLinkText(driver , prodName);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);

        prodDetailPage.selectValueInDynamicDropdown(driver , "product_attribute_1" , processor);
        prodDetailPage.selectValueInDynamicDropdown(driver , "product_attribute_2" , ram);
        prodDetailPage.checkDynamicRadioBox(driver , HDD);
        prodDetailPage.checkDynamicRadioBox(driver , OS);
        prodDetailPage.checkDynamicRadioBox(driver , Software);

        prodDetailPage.clickToDynamicButton(driver , "Add to wishlist");

        Assert.assertEquals(prodDetailPage.getSuccessMsgBar(driver) , "The product has been added to your\nwishlist");
        prodDetailPage.clickToDynamicLinkText(driver , "wishlist");
        wishlistPage = pageGenerator.getWishlistPage(driver);
        Assert.assertEquals(wishlistPage.getQtyCartOrWishlist(driver , "wishlist") , "(2)");

        wishlistPage.clickDynamicIconRemove(prodName);
        Assert.assertTrue(wishlistPage.isMsgNoDataDisplayed(driver , "Wishlist" , "The wishlist is empty!"));
        Assert.assertEquals(wishlistPage.getQtyCartOrWishlist(driver , "wishlist") , "(0)");

    }

    @Test
    public void TC_30(){
        wishlistPage.hoverMenuAndClickToSubListMenu(driver , "Computers" , "Desktops");
        computerPage = pageGenerator.getComputerPage(driver);

        computerPage.clickToDynamicProductButton(driver , prodName , "Add to cart");
        Assert.assertEquals(computerPage.getSuccessMsgBar(driver) , "The product has been added to your\nproduct comparison");

        computerPage.clickToDynamicProductButton(driver , prodName1 , "Add to cart");
        Assert.assertEquals(computerPage.getSuccessMsgBar(driver) , "The product has been added to your\nproduct comparison");

        computerPage.clickToDynamicLinkText(driver , "product comparison");
        compareProdPage = pageGenerator.getCompareProductPage(driver);
        Assert.assertEquals(compareProdPage.getPageTitle(driver) , "Compare products");

        Assert.assertTrue(compareProdPage.getAttributeDynamicTable("src" , "getAttributeDynamicTable" , "2").contains(compareSrc1));
        Assert.assertTrue(compareProdPage.getAttributeDynamicTable("src" , "getAttributeDynamicTable" , "3").contains(compareSrc1));
        Assert.assertEquals(compareProdPage.getTextInDynamicTable("product-name" , "2") , compareProd);
        Assert.assertEquals(compareProdPage.getTextInDynamicTable("product-name" , "3") , prodName);
        Assert.assertEquals(compareProdPage.getTextInDynamicTable("product-price" , "2") , "$1,259.00");
        Assert.assertEquals(compareProdPage.getTextInDynamicTable("product-price" , "3") , "$1,200.00");

        compareProdPage.clickToDynamicButton(driver , "Clear list");
        Assert.assertTrue(compareProdPage.isMsgNoDataDisplayed(driver , "Compare products" , "You have no items to compare."));

    }

    @Test
    public void TC_31(){
        compareProdPage.hoverMenuAndClickToSubListMenu(driver , "Computers" , "Notebooks");
        computerPage = pageGenerator.getComputerPage(driver);

        computerPage.clickToDynamicLinkText(driver , prodView);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);
        prodDetailPage.backToPage(driver);

        computerPage.clickToDynamicLinkText(driver , prodView2);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);
        prodDetailPage.backToPage(driver);

        computerPage.clickToDynamicLinkText(driver , prodView3);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);
        prodDetailPage.backToPage(driver);

        computerPage.clickToDynamicLinkText(driver , prodView4);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);
        prodDetailPage.backToPage(driver);

        computerPage.clickToDynamicLinkText(driver , prodView5);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);

        prodDetailPage.clickToDynamicFooterLink(driver , "Recently viewed products");
        recentlyViewProdPage = pageGenerator.getRecentlyViewProdPage(driver);

        Assert.assertTrue(recentlyViewProdPage.isListProdViewCorrect(listProdView));

    }

    @Test
    public void TC_32(){
        recentlyViewProdPage.clickToIconPage(driver);
        homePage = pageGenerator.getHomePage(driver);
        Assert.assertTrue(homePage.isWelcomeMsgDisplayed());

        homePage.clickToDynamicLinkText(driver , prodName);
        prodDetailPage = pageGenerator.getProdDetailPage(driver);

        prodDetailPage.selectValueInDynamicDropdown(driver , "product_attribute_1" , "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]");
        prodDetailPage.selectValueInDynamicDropdown(driver , "product_attribute_2" , "8GB [+$60.00]");
        prodDetailPage.checkDynamicRadioBox(driver , "400 GB [+$100.00]");
        prodDetailPage.checkDynamicRadioBox(driver , "Vista Premium [+$60.00]");
        prodDetailPage.checkDynamicRadioBox(driver , "Microsoft Office [+$50.00]");
        prodDetailPage.checkDynamicRadioBox(driver , "Acrobat Reader [+$10.00]");
        prodDetailPage.checkDynamicRadioBox(driver , "Total Commander [+$5.00]");

        prodDetailPage.clickToDynamicButton(driver , "Add to cart");
        Assert.assertEquals(prodDetailPage.getSuccessMsgBar(driver) , "The product has been added to your\nshopping cart");
        prodDetailPage.clickToDynamicLinkText(driver , "shopping cart");

        shopCartPage = pageGenerator.getShopCartPage(driver);
        shopCartPage.hoverDynamicIconInHeader(driver , "cart");

        Assert.assertEquals(shopCartPage.getInfoMiniCartHeader(driver , "count") , "There are\n1 item(s)\nin your cart.");
        Assert.assertTrue(shopCartPage.getValueProdFirstMiniShopCart(driver , "href" , "picture").contains(prodName.toLowerCase().replace(" " , "-")));
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "name") , prodName);
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "attributes") , "Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\nRAM: 8GB [+$60.00]\nHDD: 400 GB [+$100.00]\nOS: Vista Premium [+$60.00]\nSoftware: Microsoft Office [+$50.00]\nSoftware: Acrobat Reader [+$10.00]\nSoftware: Total Commander [+$5.00]");
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "price") , "Unit price:\n$1,500.00");
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "quantity") , "Quantity:\n1");
        Assert.assertEquals(shopCartPage.getInfoMiniCartHeader(driver , "totals") , "Sub-Total:\n$1,500.00");

        shopCartPage.clickToDynamicLinkText(driver , "Edit");
        prodDetailPage = pageGenerator.getProdDetailPage(driver);

        prodDetailPage.selectValueInDynamicDropdown(driver , "product_attribute_1" , "2.2 GHz Intel Pentium Dual-Core E2200");
        prodDetailPage.selectValueInDynamicDropdown(driver , "product_attribute_2" , "4GB [+$20.00]");
        prodDetailPage.checkDynamicRadioBox(driver , "320 GB");
        prodDetailPage.checkDynamicRadioBox(driver , "Vista Home [+$50.00]");
        prodDetailPage.checkDynamicRadioBox(driver , "Microsoft Office [+$50.00]");
        prodDetailPage.unCheckCheckBox(driver , "Acrobat Reader [+$10.00]");
        prodDetailPage.unCheckCheckBox(driver , "Total Commander [+$5.00]");
        prodDetailPage.clickToDynamicButton(driver , "Update");

        Assert.assertEquals(prodDetailPage.getSuccessMsgBar(driver) , "The product has been added to your\nshopping cart");
        prodDetailPage.clickToDynamicLinkText(driver , "shopping cart");

        shopCartPage = pageGenerator.getShopCartPage(driver);

        Assert.assertEquals(shopCartPage.getInfoMiniCartHeader(driver , "count") , "There are\n2 item(s)\nin your cart.");
        Assert.assertTrue(shopCartPage.getValueProdFirstMiniShopCart(driver , "href" , "picture").contains(prodName.toLowerCase().replace(" " , "-")));
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "name") , prodName);
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "attributes") , "Processor: 2.2 GHz Intel Pentium Dual-Core E2200\nRAM: 4GB [+$20.00]\nHDD: 320 GB\nOS: Vista Home [+$50.00]\nSoftware: Microsoft Office [+$50.00]");
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "price") , "Unit price:\n$1,320.00");
        Assert.assertEquals(shopCartPage.getInfoProdFirstMiniShopCart(driver , "quantity") , "Quantity:\n2");
        Assert.assertEquals(shopCartPage.getInfoMiniCartHeader(driver , "totals") , "Sub-Total:\n$2,640.00");

        shopCartPage.clickToDynamicIconRemoveInCart(prodName);
        Assert.assertTrue(shopCartPage.isMsgNoDataDisplayed(driver , "Shopping cart" , "Your Shopping Cart is empty!"));
    }

    @Test
    public void TC_33(){
        String prodCheck = "Lenovo IdeaCentre 600 All-in-One PC";
        shopCartPage.hoverMenuAndClickToSubListMenu(driver , "Computers" , "Desktops");
        computerPage = pageGenerator.getComputerPage(driver);
        computerPage.clickToDynamicLinkText(driver , "Lenovo IdeaCentre 600 All-in-One PC");
        prodDetailPage.clickToDynamicButton(driver , "Add to cart");
        Assert.assertEquals(prodDetailPage.getSuccessMsgBar(driver) , "The product has been added to your\nshopping cart");
        prodDetailPage.clickToDynamicLinkText(driver , "shopping cart");
        shopCartPage = pageGenerator.getShopCartPage(driver);

        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "SKU" , "1") , "LE_IC_600");
        Assert.assertTrue(shopCartPage.getAttributeValueInInDynamicTable(driver , "src" , "Shopping cart" , "Image" , "1").contains(prodCheck.toLowerCase().replace(" " , "-")));
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Product (s)" , "1") , prodCheck);
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Price" , "1") , "Price:\n$500.00");
        Assert.assertEquals(shopCartPage.getAttributeValueInInDynamicTable(driver , "value" ,"Shopping cart" , "Qty." , "1") , "1");
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Total" , "1") , "Total:\n$500.00");

        shopCartPage.updateQuantityProduct(driver ,"Lenovo IdeaCentre 600 All-in-One PC" , "5");
        shopCartPage.clickToDynamicButton(driver , "Update shopping cart");

        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "SKU" , "1") , "LE_IC_600");
        Assert.assertTrue(shopCartPage.getAttributeValueInInDynamicTable(driver , "src" , "Shopping cart" , "Image" , "1").contains(prodCheck.toLowerCase().replace(" " , "-")));
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Product (s)" , "1") , prodCheck);
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Price" , "1") , "Price:\n$500.00");
        Assert.assertEquals(shopCartPage.getAttributeValueInInDynamicTable(driver , "value" ,"Shopping cart" , "Qty." , "1") , "5");
        Assert.assertEquals(shopCartPage.getTextInDynamicTable(driver , "Shopping cart" , "Total" , "1") , "Total:\n$2500.00");


    }

}
