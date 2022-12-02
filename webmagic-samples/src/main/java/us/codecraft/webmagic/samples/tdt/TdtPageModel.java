package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author: mek
 * Date: 2022\12\1 0001
 * Time: 16:57
 * vx: 250023777
 * Description: save content page info
 * @version: 1.0
 */
public class TdtPageModel {
    private boolean debug = true;
    private final static String DEBUG_PREFIX_TEXT = "TdtPageModel debug:";
    private TdtMenuModel menuModel;
    private JSONObject clazzObj;
    private JSONObject optionObj;
    private List<JSONObject> enumList;
    private List<JSONObject> methodList;
    private List<JSONObject> eventList;

    TdtPageModel(TdtMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void parsePage(Page page) {
        Html html = page.getHtml();
        String url = page.getUrl().toString();

        if (debug) {
            printDebug(url, html.get());
        }

        //class obj init
        this.clazzObj = parseClazz(html, url);
        //option obj init
        this.optionObj = parseOption(html, url);
        //enumList init
        this.enumList = parseEnumList(html, url);
        //methodList init
        this.methodList = parseMethodList(html, url);
        //eventList init
        this.eventList = parseEventList(html, url);
    }

    private List<JSONObject> parseEventList(Html html, String url) {
        return null;
    }

    private List<JSONObject> parseMethodList(Html html, String url) {
        return null;
    }

    private List<JSONObject> parseEnumList(Html html, String url) {
        return null;
    }

    private JSONObject parseOption(Html html, String url) {
        return null;
    }

    private JSONObject parseClazz(Html html, String url) {
        String htmlString = html.get();

        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return null;
        }

        String name = item.getString("name");
        if (Strings.isNullOrEmpty(name)) {
            return null;
        }

        if (name.contains("Options")) {
            return null;
        }

        JSONObject obj = new JSONObject();
        obj.put("name", name);


        return obj;
    }

    /**
     * print debug info
     *
     * @param texts content texts
     */
    private void printDebug(String... texts) {
        for (String text :
                texts) {
            System.out.println("[debug info]");
            System.out.println(DEBUG_PREFIX_TEXT + text);
            System.out.println("[end debug]");
        }
    }
}
