package commons;

import java.io.File;

public class GlobalConstants {

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOAD_FILE_PATH =  PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
    public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGScreenshots" + File.separator;
    public static final String DOWNLOAD_FILE_PATH = PROJECT_PATH + File.separator + "downloadFiles";
    public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String BANK_GURU_URL = "https://demo.guru99.com/v4/";

    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
}
