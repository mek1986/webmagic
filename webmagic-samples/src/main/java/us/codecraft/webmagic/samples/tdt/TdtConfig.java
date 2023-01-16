package us.codecraft.webmagic.samples.tdt;

import java.util.HashMap;
import java.util.Map;

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
    public final static String DOMAIN = "192.168.1.203:6001";


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
    public final static String HTML_FILE_PATH = "D:/temp/tdt_html/";

    /**
     * js file path
     */
    public final static String JS_FILE_PATH = "D:/temp/js/";

    /**
     * module name 2 file name map
     */
    public final static Map<String, String> MODULE_NAME_2_FILE_NAME = new HashMap<>();
    static {
        initModuleName2FileName();
    }

    private static void initModuleName2FileName() {
        MODULE_NAME_2_FILE_NAME.put("地图主类", "map.js");
        MODULE_NAME_2_FILE_NAME.put("图层类", "tile_layer.js");
        MODULE_NAME_2_FILE_NAME.put("控件类", "control.js");
        MODULE_NAME_2_FILE_NAME.put("覆盖物类", "over_lay.js");
        MODULE_NAME_2_FILE_NAME.put("工具类", "tool.js");
        MODULE_NAME_2_FILE_NAME.put("组件类", "component.js");
        MODULE_NAME_2_FILE_NAME.put("右键菜单类", "menu.js");
        MODULE_NAME_2_FILE_NAME.put("实体类", "entity.js");
        MODULE_NAME_2_FILE_NAME.put("服务类", "service.js");
        MODULE_NAME_2_FILE_NAME.put("符号标绘", "military_symbols.js");
    }
}
