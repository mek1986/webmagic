package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: mek
 * Date: 2022\12\1 0001
 * Time: 16:57
 * vx: 250023777
 * Description: save content page info
 * @version: 1.0
 */
public class TdtPageModel {
    private TdtMenuModel menuModel;
    private JSONObject clazzObj;
    private JSONObject optionObj;
    private List<JSONObject> enumList;
    private List<JSONObject> methodList;
    private List<JSONObject> eventList;

    TdtPageModel(TdtMenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public TdtPageModel parsePage(Page page) {
        Html html = page.getHtml();
        String url = page.getUrl().toString();

        List<Selectable> selectables = parseHtml(html);

        //class obj init
        this.clazzObj = parseClazz(selectables, url);
        //option obj init
        this.optionObj = parseOption(selectables, url);
        //enumList init
        this.enumList = parseEnumList(selectables, url);
        //methodList init
        this.methodList = parseMethodList(selectables, url);
        //eventList init
        this.eventList = parseEventList(selectables, url);

        return this;
    }

    /**
     * parse Selectable list from Html object
     *
     * @param html Html object
     * @return Selectable list:4 items,(1,2,3,4),first is p,second is h3,third is h4,four is table
     */
    private List<Selectable> parseHtml(Html html) {
        //desc
        Selectable p = html.xpath("//p");
        Selectable h3 = html.xpath("//h3");
        Selectable h4 = html.xpath("//h4");
        Selectable table = html.xpath("//table");

        return Lists.newArrayList(p, h3, table, h4);
    }

    private List<JSONObject> parseEventList(List<Selectable> selectables, String url) {
        return null;
    }

    private List<JSONObject> parseMethodList(List<Selectable> selectables, String url) {
        return null;
    }

    private List<JSONObject> parseEnumList(List<Selectable> selectables, String url) {
        return null;
    }

    /**
     * parse option from html
     *
     * @param selectables Selectable list
     * @param url         page url
     * @return clazz object:if can't find clazz,null object is return.
     */
    private JSONObject parseOption(List<Selectable> selectables, String url) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return null;
        }

        String name = item.getString("name");
        if (Strings.isNullOrEmpty(name)) {
            //must have name
            return null;
        }

        if (!name.contains("Options")) {
            //options
            return null;
        }

        //option object
        JSONObject obj = new JSONObject();

        obj.put("name", name);
        obj.put("url", url);
        obj.put("desc", selectables.get(0).regex("<p>([\\s\\S]+)</p>").get());
        obj.put("detail", parseOptionDetail(selectables, name));

        return obj;
    }

    /**
     * parse option detail from selectables
     *
     * @param selectables Selectables list
     * @param optionName  optionName
     * @return option detail
     */
    private JSONObject parseOptionDetail(List<Selectable> selectables, String optionName) {

        

        return null;
    }

    /**
     * parse clazz from html
     *
     * @param selectables Selectable list
     * @param url         page url
     * @return clazz object:if can't find clazz,null object is return.
     */
    private JSONObject parseClazz(List<Selectable> selectables, String url) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return null;
        }

        String name = item.getString("name");
        if (Strings.isNullOrEmpty(name)) {
            //must have name
            return null;
        }

        if (name.contains("Options")) {
            //options
            return null;
        }

        //class object
        JSONObject obj = new JSONObject();

        obj.put("name", name);
        obj.put("url", url);
        obj.put("desc", selectables.get(0).regex("<p>([\\s\\S]+)</p>").get());

        List<String> moduleNames = menuModel.getModuleNameByPid(item.getInteger("pId") + "");
        if (moduleNames == null || moduleNames.isEmpty()) {
            //must have module
            return null;
        }

        int moduleSize = moduleNames.size();
        //first module
        obj.put("module1", moduleNames.get(0));

        if (moduleSize == 1) {
            //don't have second module
            obj.put("module2", "无");
        } else {
            //second module
            obj.put("module2", moduleNames.get(1));
        }

        return obj;
    }
}
