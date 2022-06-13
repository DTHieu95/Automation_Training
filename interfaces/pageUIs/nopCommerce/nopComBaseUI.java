package pageUIs.nopCommerce;

public class nopComBaseUI {
    public static final String DYNAMIC_MENU_HEADER = "//div[@class = 'header-links']//a[text() = '%s']";

    public static final String DYNAMIC_TEXTBOX_BY_LABEL = "//label[contains(string() , '%s')]/following-sibling::input";

    public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[contains(string() , '%s')]";

    public static final String PAGE_TITLE = "//div[@class = 'page-title']/h1";

    public static final String DYNAMIC_ERROR_MESSAGE_TEXTBOX = "//span[@id = '%s-error']";

    public static final String SUMMARY_ERROR_MSG = "//div[@class = 'message-error validation-summary-errors']";

    public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name = '%s']";
}
