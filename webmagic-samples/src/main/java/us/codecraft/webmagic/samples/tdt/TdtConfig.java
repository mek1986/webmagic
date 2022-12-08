package us.codecraft.webmagic.samples.tdt;

/**
 * @author: mek
 * Date: 2022\12\1 0001
 * Time: 15:36
 * vx: 250023777
 * Description: app base config
 * @version: 1.0
 */
final public class TdtConfig {
    /**
     * debug mode
     */
    public static boolean DEBUG = true;

    /**
     * domain
     * lbs.tianditu.gov.cn
     */
    public final static String DOMAIN = "localhost:6001";


    /**
     * site url
     */
    public final static String SITE_URL = "http://" + DOMAIN + "/";

    /**
     * menu page url
     */
    public final static String MENU_PAGE_PREFIX = SITE_URL + "js/";

    /**
     * content page url
     * api/js4.0/
     */
    public final static String CONTENT_PAGE_PREFIX = SITE_URL + "";

    /**
     * debug print prefix text
     */
    public final static String DEBUG_PREFIX_TEXT = "DEBUG: ";

    /**
     * system exit code
     */
    public final static Integer EXIT_CODE = 1000;

    /**
     * title regex string
     */
    public final static String TITLE_REGEX_STRING = ">([^<>]+)<";

    /**
     * content regex string
     */
    public final static String CONTENT_REGEX_STRING = ">([\\s\\S]+)<";

    /**
     * html file path
     */
    public final static String HTML_FILE_PATH = "D:\\temp\\tdt_html\\";
}
