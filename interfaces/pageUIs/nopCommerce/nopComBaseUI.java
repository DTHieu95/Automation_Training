package pageUIs.nopCommerce;

public class nopComBaseUI {
    public static final String DYNAMIC_MENU_HEADER = "//div[@class = 'header-links']//a[text() = '%s']";

    public static final String DYNAMIC_TEXTBOX_BY_LABEL = "//label[contains(string() , '%s')]/following-sibling::input";

    public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(string() , '%s')]";

    public static final String PAGE_TITLE = "//div[@class = 'page-title']/h1";

    public static final String DYNAMIC_ERROR_MESSAGE_TEXTBOX = "//span[@id = '%s-error']";

    public static final String SUMMARY_ERROR_MSG = "//div[@class = 'message-error validation-summary-errors']";

    public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name = '%s']";

    public static final String DYNAMIC_ACCOUNT_MENU_SIDE = "//div[@class = 'listbox']//a[contains(string() , '%s')]";

    public static final String DYNAMIC_RADIO_BOX_LABEL = "//label[text() = '%s']/preceding-sibling::input";

    public static final String DYNAMIC_DROPDOWN_BY_LABEL = "//label[contains(string() , '%s')]/following-sibling::select";

    public static final String SUCCESS_MSG_BAR = "//div[@class = 'bar-notification success']";

    public static final String ICON_CLOSE_BAR_HEADER = "//div//span[@class = 'close']";

    public static final String DYNAMIC_FOOTER_LINK = "//div[@class = 'footer']//a[text() = '%s']";

    public static final String DYNAMIC_CHECK_BOX = "//label[text() = '%s']/preceding-sibling::input";

    public static final String DYNAMIC_LINK_TEXT = "//a[text() = '%s']";

    public static final String DYNAMIC_HEADER_MENU = "//ul[@class = 'top-menu notmobile']//a[contains(string() , '%s')]";

    public static final String DYNAMIC_SUBLIST_MENU = "//ul[@class = 'top-menu notmobile']//ul[@class = 'sublist first-level']//a[contains(string() , '%s')]";

    public static final String PRODUCT_NAME_TEXT = "//h2[@class = 'product-title']//a";

    public static final String PRODUCT_PRICE = "//div[@class = 'prices']//span";

    public static final String CURRENT_PAGE = "//li[@class = 'current-page']/span";

    public static final String DYNAMIC_ICON_PAGE = "//li[@class = '%s-page']/a";
}
