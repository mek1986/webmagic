package us.codecraft.webmagic.samples.tdt;

/**
 * @author: mek
 * Date: 2022\12\1 0001
 * Time: 15:36
 * vx: 250023777
 * Description: 配置
 * @version: 1.0
 */
final public class TdtConfig {
    /**
     * 域名
     */
    public final static String DOMAIN = "lbs.tianditu.gov.cn";


    /**
     * 网站地址
     */
    public final static String SITE_URL = "http://" + DOMAIN + "/";

    /**
     * 菜单页面地址前缀
     */
    public final static String MENU_PAGE_PREFIX = SITE_URL + "js/";

    /**
     * 内容页面地址前缀
     */
    public final static String CONTENT_PAGE_PREFIX = SITE_URL + "api/js4.0/";

    /**
     * 菜单初始化同步锁
     */
    public final static String MENU_INIT_KEY = "menu_init_key";
}
