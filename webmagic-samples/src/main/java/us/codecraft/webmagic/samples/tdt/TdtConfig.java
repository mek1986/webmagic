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
     * domain
     */
    public final static String DOMAIN = "lbs.tianditu.gov.cn";


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
     */
    public final static String CONTENT_PAGE_PREFIX = SITE_URL + "api/js4.0/";

    /**
     * menu init synchronized key
     */
    public final static String MENU_INIT_KEY = "menu_init_key";
}
