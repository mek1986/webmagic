package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

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

        List<List<Selectable>> selectables = parseHtml(html);
        if (selectables == null) {
            TdtUtils.printDebug(page.getUrl().get());
            System.exit(TdtConfig.EXIT_CODE);
        }

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
    private List<List<Selectable>> parseHtml(Html html) {
        //desc
        List<Selectable> p = html.xpath("//p").nodes();
        List<Selectable> h3 = html.xpath("//h3").nodes();
        List<Selectable> h4 = html.xpath("//h4").nodes();
        List<Selectable> table = html.xpath("//table").nodes();

        if (!checkTableSize(table)) {
            return null;
        }

        return Lists.newArrayList(p, h3, h4, table);
    }

    private List<JSONObject> parseEventList(List<List<Selectable>> selectables, String url) {
        return null;
    }

    private List<JSONObject> parseMethodList(List<List<Selectable>> selectables, String url) {
        return null;
    }

    private List<JSONObject> parseEnumList(List<List<Selectable>> selectables, String url) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return null;
        }

        String name = item.getString("name");
        if (Strings.isNullOrEmpty(name)) {
            //must have name
            return null;
        }

        List<Selectable> tableNodes = selectables.get(3);

        int tableSize = tableNodes.size();
        List<Integer> indexList = new ArrayList<>();
        //find enum
        for (int i = 0; i < tableSize; i++) {
            String title = tableNodes.get(i).xpath("tr").nodes().get(0).xpath("td").nodes().get(0).regex(">([^<>]+)<").get().trim();
            if (Objects.equals("常量", title)) {
                indexList.add(i);
                break;
            }
        }

        if (indexList.isEmpty()) {
            TdtUtils.printDebug("can not find attr in option detail");
            return null;
        }

        //return value
        List<JSONObject> ans = new ArrayList<>();
        //parse enum list
        for (Integer index :
                indexList) {
            Selectable enumTable = tableNodes.get(index);
            List<Selectable> trNodes = enumTable.xpath("tr").nodes();
            if (trNodes == null || trNodes.size() <= 1) {
                TdtUtils.printDebug("enum table is invalid");
                continue;
            }

            List<Selectable> titleNodes = trNodes.get(0).xpath("td").nodes();
            //td index to attr name
            Map<Integer, String> index2EnumNameMap = parseEnumTitles(titleNodes);
            int contentSize = trNodes.size();
            JSONObject obj = new JSONObject();
            List<Selectable> tdNodes;
            for (int i = 1; i < contentSize; i++) {
                tdNodes = trNodes.get(i).xpath("td").nodes();
                int size = tdNodes.size();
                for (int j = 0; j < size; j++) {
                    obj.put(index2EnumNameMap.get(j), tdNodes.get(j).regex(">([^<>]+)<").get().trim());
                }

                obj.put("const_name", name);
                ans.add(obj);
                obj = new JSONObject();
            }
        }

        return ans;
    }

    /**
     * parse enum title
     * key is index,value is attr name
     *
     * @param titleNodes title nodes
     * @return map
     */
    private Map<Integer, String> parseEnumTitles(List<Selectable> titleNodes) {
        if (titleNodes == null || titleNodes.size() != 2) {
            TdtUtils.printDebug("title td size is not valid in enum: " + (titleNodes == null ? 0 : titleNodes.size()));
            System.exit(TdtConfig.EXIT_CODE);
        }

        Map<String, String> title2NameMap = new HashMap<>();
        title2NameMap.put("常量", "const_value");
        title2NameMap.put("说明", "const_value_desc");
        Map<Integer, String> ans = new HashMap<>();
        int size = titleNodes.size();
        String content;
        for (int i = 0; i < size; i++) {
            content = titleNodes.get(i).regex(">([^<>]+)<").toString().trim();

            if (Strings.isNullOrEmpty(content)) {
                TdtUtils.printDebug("title content is empty in enum");
                System.exit(TdtConfig.EXIT_CODE);
            }

            if (!title2NameMap.containsKey(content)) {
                TdtUtils.printDebug("title content is not exist in enum");
                System.exit(TdtConfig.EXIT_CODE);
            }

            ans.put(i, title2NameMap.get(content));
        }

        return ans;
    }

    /**
     * parse option from html
     *
     * @param selectables Selectable list
     * @param url         page url
     * @return clazz object:if can't find clazz,null object is return.
     */
    private JSONObject parseOption(List<List<Selectable>> selectables, String url) {
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
        obj.put("desc", selectables.get(0).get(0).regex("<p>([\\s\\S]+)</p>").get().trim());
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
    private List<JSONObject> parseOptionDetail(List<List<Selectable>> selectables, String optionName) {
        List<Selectable> tableNodes = selectables.get(3);

        int tableSize = tableNodes.size();
        int index = -1;
        //find attr
        for (int i = 0; i < tableSize; i++) {
            String title = tableNodes.get(i).xpath("tr").nodes().get(0).xpath("td").nodes().get(0).regex(">([^<>]+)<").get().trim();
            if (Objects.equals("属性", title)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            TdtUtils.printDebug("can not find attr in option detail");
            System.exit(TdtConfig.EXIT_CODE);
        }

        Selectable attrTable = tableNodes.get(index);
        List<Selectable> trNodes = attrTable.xpath("tr").nodes();
        if (trNodes == null || trNodes.size() <= 1) {
            TdtUtils.printDebug("tr nodes does not have content");
            System.exit(TdtConfig.EXIT_CODE);
        }

        List<Selectable> titleNodes = trNodes.get(0).xpath("td").nodes();
        //td index to attr name
        Map<Integer, String> index2AttrNameMap = parseOptionDetailTitles(titleNodes);

        int contentSize = trNodes.size();
        //return value
        List<JSONObject> ans = new ArrayList<>();
        JSONObject obj = new JSONObject();
        List<Selectable> tdNodes;
        for (int i = 1; i < contentSize; i++) {
            tdNodes = trNodes.get(i).xpath("td").nodes();
            int size = tdNodes.size();
            for (int j = 0; j < size; j++) {
                obj.put(index2AttrNameMap.get(j), tdNodes.get(j).regex(">([^<>]+)<").get().trim());
            }

            obj.put("obj_name", optionName);
            ans.add(obj);
            obj = new JSONObject();
        }

        return ans;
    }

    /**
     * parse option detail title
     * key is index,value is attr name
     *
     * @param titleNodes title td nodes
     * @return title map
     */
    private Map<Integer, String> parseOptionDetailTitles(List<Selectable> titleNodes) {
        if (titleNodes == null || titleNodes.size() < 3 || titleNodes.size() > 4) {
            TdtUtils.printDebug("title td size is not valid in option detail: " + (titleNodes == null ? 0 : titleNodes.size()));
            System.exit(TdtConfig.EXIT_CODE);
        }

        Map<String, String> title2NameMap = new HashMap<>();
        title2NameMap.put("属性", "attr_name");
        title2NameMap.put("类型", "attr_type");
        title2NameMap.put("说明", "attr_desc");
        title2NameMap.put("默认值", "attr_default_value");
        Map<Integer, String> ans = new HashMap<>();
        int size = titleNodes.size();
        String content;
        for (int i = 0; i < size; i++) {
            content = titleNodes.get(i).regex(">([^<>]+)<").toString().trim();

            if (Strings.isNullOrEmpty(content)) {
                TdtUtils.printDebug("title content is empty in option detail");
                System.exit(TdtConfig.EXIT_CODE);
            }

            if (!title2NameMap.containsKey(content)) {
                TdtUtils.printDebug("title content is not exist in option detail");
                System.exit(TdtConfig.EXIT_CODE);
            }

            ans.put(i, title2NameMap.get(content));
        }

        return ans;
    }

    /**
     * check table size
     *
     * @param tableNodes table nodes
     * @return 数量是否一致
     */
    private boolean checkTableSize(List<Selectable> tableNodes) {
        if (tableNodes == null || tableNodes.size() == 0) {
            if (TdtConfig.DEBUG) {
                TdtUtils.printDebug("can not find table in option detail");
            }

            return false;
        }

        return true;
    }

    /**
     * parse clazz from html
     *
     * @param selectables Selectable list
     * @param url         page url
     * @return clazz object:if can't find clazz,null object is return.
     */
    private JSONObject parseClazz(List<List<Selectable>> selectables, String url) {
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

        List<Selectable> nodes = selectables.get(0).get(0).nodes();
        if (nodes == null || nodes.size() == 0) {
            TdtUtils.printDebug("can not find p in class");
            System.exit(TdtConfig.EXIT_CODE);
        }

        obj.put("desc", nodes.get(0).regex("<p>([\\s\\S]+)</p>").get());

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
