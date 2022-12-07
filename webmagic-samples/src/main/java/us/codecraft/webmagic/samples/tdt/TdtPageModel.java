package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONArray;
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
    private JSONObject moduleNames = new JSONObject();
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
            throw new IllegalArgumentException("html is empty");
        }

        if (isClass(selectables, url)) {
            //parse clazz
            parseClazz(selectables, url);
            return this;
        }

        //parse option
        parseOption(selectables, url);

        return this;
    }

    /**
     * check page whether is a class
     *
     * @param selectables selectables
     * @param url         page url
     * @return if page is class,return true.else return false
     */
    private boolean isClass(List<List<Selectable>> selectables, String url) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return false;
        }

        String name = item.getString("name");
        if (Strings.isNullOrEmpty(name)) {
            //must have name
            if (TdtConfig.DEBUG) {
                System.out.println("does no have name");
            }

            System.exit(TdtConfig.EXIT_CODE);
            return false;
        }

        if (name.contains("Options")) {
            //options
            return false;
        }

        List<Selectable> tableNodes = selectables.get(3);
        boolean method = false;
        boolean type = false;

        //find option
        for (Selectable tableNode : tableNodes) {
            List<Selectable> tdNodes = tableNode.xpath("tr").nodes().get(0).xpath("td").nodes();
            method = false;
            type = false;

            for (Selectable node :
                    tdNodes) {
                String title = node.regex(TdtConfig.TITLE_REGEX_STRING).get().trim();
                if (Objects.equals("方法", title)) {
                    method = true;
                    continue;
                }

                if (Objects.equals("类型", title)) {
                    type = true;
                    continue;
                }

                if (method && type) {
                    break;
                }
            }

            if (method && type) {
                break;
            }
        }

        return !(method && type);
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


    /**
     * parse event
     *
     * @param selectables selectables
     * @param url         page url
     * @param name        name
     * @return event list
     */
    private List<JSONObject> parseEventList(List<List<Selectable>> selectables, String url, String name) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return null;
        }

        List<Integer> indexList = new ArrayList<>();
        List<Selectable> tableNodes = selectables.get(3);
        int tableSize = tableNodes.size();

        //find event
        for (int i = 0; i < tableSize; i++) {
            List<Selectable> tdNodes = tableNodes.get(i).xpath("tr").nodes().get(0).xpath("td").nodes();

            for (Selectable node :
                    tdNodes) {
                String title = node.regex(TdtConfig.TITLE_REGEX_STRING).get().trim();
                if (title.contains("事件")) {
                    indexList.add(i);
                }
            }
        }

        if (indexList.isEmpty()) {
            //no method
            return null;
        }

        //return value
        JSONArray ans = new JSONArray();
        //parse method list
        for (Integer index :
                indexList) {
            Selectable eventTable = tableNodes.get(index);
            List<Selectable> trNodes = eventTable.xpath("tr").nodes();
            if (trNodes == null || trNodes.size() <= 1) {
                TdtUtils.printDebug("event table is invalid");
                continue;
            }

            List<Selectable> titleNodes = trNodes.get(0).xpath("td").nodes();
            //td index to attr name
            Map<Integer, String> index2EventNameMap = parseEventTitles(titleNodes);

            //parse tr
            parseTableTr(name, ans, trNodes, index2EventNameMap);
        }

        return ans.toJavaList(JSONObject.class);
    }

    /**
     * parse method title
     * key is index,value is attr name
     *
     * @param titleNodes title nodes
     * @return map
     */
    private Map<Integer, String> parseEventTitles(List<Selectable> titleNodes) {
        if (titleNodes == null || titleNodes.size() < 2) {
            TdtUtils.printDebug("title td size is not valid in event: " + (titleNodes == null ? 0 : titleNodes.size()));
            System.exit(TdtConfig.EXIT_CODE);
        }

        Map<String, String> title2NameMap = new HashMap<>();
        title2NameMap.put("事件", "event_name");
        title2NameMap.put("参数", "params");
        title2NameMap.put("描述", "event_desc");
        title2NameMap.put("说明", "event_desc");
        Map<Integer, String> ans = new HashMap<>();
        int size = titleNodes.size();
        String content;
        for (int i = 0; i < size; i++) {
            content = titleNodes.get(i).regex(TdtConfig.TITLE_REGEX_STRING).toString().trim();

            if (Strings.isNullOrEmpty(content)) {
                TdtUtils.printDebug("title content is method in enum");
                System.exit(TdtConfig.EXIT_CODE);
            }

            if (!title2NameMap.containsKey(content)) {
                throw new IllegalArgumentException("title content is not exist in method：" + content);
            }

            ans.put(i, title2NameMap.get(content));
        }

        return ans;
    }

    /**
     * parse method list
     *
     * @param selectables selectables
     * @param url         page url
     * @param name        name
     * @return method list
     */
    private List<JSONObject> parseMethodList(List<List<Selectable>> selectables, String url, String name) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return null;
        }

        List<Integer> indexList = new ArrayList<>();
        List<Selectable> tableNodes = selectables.get(3);
        int tableSize = tableNodes.size();
        boolean method;
        boolean type;

        //find option
        for (int i = 0; i < tableSize; i++) {
            List<Selectable> tdNodes = tableNodes.get(i).xpath("tr").nodes().get(0).xpath("td").nodes();
            method = false;
            type = false;

            for (Selectable node :
                    tdNodes) {
                String title = node.regex(TdtConfig.TITLE_REGEX_STRING).get().trim();
                if (title.contains("方法") || title.contains("函数")) {
                    method = true;
                    continue;
                }

                if (Objects.equals("类型", title)) {
                    type = true;
                    continue;
                }

                if (method && type) {
                    break;
                }
            }

            if (method && !type) {
                indexList.add(i);
            }
        }

        if (indexList.isEmpty()) {
            //no method
            return null;
        }

        //return value
        List<JSONObject> ans = new ArrayList<>();
        //parse method list
        for (Integer index :
                indexList) {
            Selectable enumTable = tableNodes.get(index);
            List<Selectable> trNodes = enumTable.xpath("tr").nodes();
            if (trNodes == null || trNodes.size() <= 1) {
                TdtUtils.printDebug("method table is invalid");
                continue;
            }

            List<Selectable> titleNodes = trNodes.get(0).xpath("td").nodes();
            //td index to attr name
            Map<Integer, String> index2EnumNameMap = parseMethodTitles(titleNodes);
            JSONArray array = new JSONArray();

            //parse tr
            parseTableTr(name, array, trNodes, index2EnumNameMap);

            JSONObject obj = new JSONObject();
            List<Selectable> h3Nodes = selectables.get(2);

            if (h3Nodes == null || h3Nodes.isEmpty()) {
                obj.put("方法", array);
            } else {
                Selectable h3 = h3Nodes.get(index);

                if (h3 == null) {
                    obj.put("方法", array);
                } else {
                    obj.put(h3.regex(TdtConfig.TITLE_REGEX_STRING).get().trim(), array);
                }
            }

            ans.add(obj);
        }

        return ans;
    }

    /**
     * parse method title
     * key is index,value is attr name
     *
     * @param titleNodes title nodes
     * @return map
     */
    private Map<Integer, String> parseMethodTitles(List<Selectable> titleNodes) {
        if (titleNodes == null || titleNodes.size() < 2) {
            TdtUtils.printDebug("title td size is not valid in method: " + (titleNodes == null ? 0 : titleNodes.size()));
            System.exit(TdtConfig.EXIT_CODE);
        }

        Map<String, String> title2NameMap = new HashMap<>();
        title2NameMap.put("方法", "raw_method_sign");
        title2NameMap.put("构造函数", "raw_method_sign");
        title2NameMap.put("函数", "raw_method_sign");
        title2NameMap.put("返回值", "return_type");
        title2NameMap.put("返回类型", "return_type");
        title2NameMap.put("描述", "method_desc");
        title2NameMap.put("说明", "method_desc");
        Map<Integer, String> ans = new HashMap<>();
        int size = titleNodes.size();
        String content;
        for (int i = 0; i < size; i++) {
            content = titleNodes.get(i).regex(TdtConfig.TITLE_REGEX_STRING).toString().trim();

            if (Strings.isNullOrEmpty(content)) {
                TdtUtils.printDebug("title content is method in enum");
                System.exit(TdtConfig.EXIT_CODE);
            }

            if (!title2NameMap.containsKey(content)) {
                throw new IllegalArgumentException("title content is not exist in method：" + content);
            }

            ans.put(i, title2NameMap.get(content));
        }

        return ans;
    }


    /**
     * parse enum
     *
     * @param selectables selectables
     * @param url         url
     * @return enum list
     */
    private List<JSONObject> parseEnumList(List<List<Selectable>> selectables, String url) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            return null;
        }

        List<Selectable> tableNodes = selectables.get(3);

        int tableSize = tableNodes.size();
        List<Integer> indexList = new ArrayList<>();
        //find enum
        for (int i = 0; i < tableSize; i++) {
            String title = tableNodes.get(i).xpath("tr").nodes().get(0).xpath("td").nodes().get(0).regex(TdtConfig.TITLE_REGEX_STRING).get().trim();
            if (Objects.equals("常量", title)) {
                indexList.add(i);
                break;
            }
        }

        if (indexList.isEmpty()) {
            //no enum
            return null;
        }

        if (indexList.size() > 1) {
            throw new IllegalArgumentException("more than one enum");
        }

        //return value
        List<JSONObject> ans = new ArrayList<>();
        //parse enum list

        String name = selectables.get(1).get(0).regex(TdtConfig.CONTENT_REGEX_STRING).toString();
        Selectable enumTable = tableNodes.get(indexList.get(0));
        List<Selectable> trNodes = enumTable.xpath("tr").nodes();
        if (trNodes == null || trNodes.size() <= 1) {
            throw new IllegalArgumentException("enum table is invalid");
        }

        List<Selectable> titleNodes = trNodes.get(0).xpath("td").nodes();
        //td index to attr name
        Map<Integer, String> index2EnumNameMap = parseEnumTitles(titleNodes);
        JSONArray array = new JSONArray();

        //parse tr
        parseTableTr(name, array, trNodes, index2EnumNameMap);

        JSONObject obj = new JSONObject();
        obj.put(name, array);

        ans.add(obj);

        return ans;
    }

    /**
     * parse table tr
     *
     * @param name    name
     * @param array   array
     * @param trNodes tr nodes
     * @param map     map
     */
    private void parseTableTr(String name, JSONArray array, List<Selectable> trNodes, Map<Integer, String> map) {
        int trSize = trNodes.size();
        List<Selectable> tdNodes;
        JSONObject obj = new JSONObject();

        for (int i = 1; i < trSize; i++) {
            tdNodes = trNodes.get(i).xpath("td").nodes();
            int size = tdNodes.size();
            for (int j = 0; j < size; j++) {
                List<Selectable> nodes = tdNodes.get(j).regex(TdtConfig.CONTENT_REGEX_STRING).nodes();

                if (nodes == null || nodes.size() == 0) {
                    obj.put(map.get(j), "无");
                    continue;
                }

                for (Selectable node :
                        nodes) {
                    String s = node.get();

                    if (!Strings.isNullOrEmpty(s)) {
                        s = s.trim();
                    }

                    if (!Strings.isNullOrEmpty(s)) {
                        obj.put(map.get(j), s);
                        break;
                    }
                }

                if (!obj.containsKey(map.get(j))) {
                    obj.put(map.get(j), "无");
                }
            }

            obj.put("name", name);
            array.add(obj);
            obj = new JSONObject();
        }
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
        title2NameMap.put("描述", "const_value_desc");
        Map<Integer, String> ans = new HashMap<>();
        int size = titleNodes.size();
        String content;
        for (int i = 0; i < size; i++) {
            content = titleNodes.get(i).regex(TdtConfig.TITLE_REGEX_STRING).toString().trim();

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
     */
    private void parseOption(List<List<Selectable>> selectables, String url) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            throw new IllegalArgumentException("cant not find option");
        }

        String name = item.getString("name");

        //parse option
        this.optionObj = parseOptionInfo(name, selectables, url, item);
        //parse enum
        this.enumList = parseEnumList(selectables, url);
    }

    /**
     * parse option info
     *
     * @param name        option name
     * @param selectables selectables
     * @param url         url
     * @param item        module item info
     * @return clazz obj
     */
    private JSONObject parseOptionInfo(String name, List<List<Selectable>> selectables, String url, JSONObject item) {
        //option object
        JSONObject obj = new JSONObject();

        obj.put("name", name);
        obj.put("url", url);
        obj.put("obj_desc", selectables.get(0).get(0).regex("<p>([\\s\\S]+)</p>").get().trim());
        obj.put("detail", parseOptionDetail(selectables, name));

        return obj;
    }

    /**
     * parse option detail from selectables
     *
     * @param selectables Selectables list
     * @param name        optionName
     * @return option detail
     */
    private List<JSONObject> parseOptionDetail(List<List<Selectable>> selectables, String name) {
        List<Selectable> tableNodes = selectables.get(3);

        int tableSize = tableNodes.size();
        int index = -1;
        //find attr
        for (int i = 0; i < tableSize; i++) {
            String title = tableNodes.get(i).xpath("tr").nodes().get(0).xpath("td").nodes().get(0).regex(TdtConfig.TITLE_REGEX_STRING).get().trim();
            if (Objects.equals("属性", title) || Objects.equals("方法", title)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new IllegalArgumentException("can not find attr in option detail");
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
        JSONArray array = new JSONArray();

        //parse tr
        parseTableTr(name, array, trNodes, index2AttrNameMap);

        return array.toJavaList(JSONObject.class);
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
        title2NameMap.put("方法", "attr_name");
        title2NameMap.put("类型", "attr_type");
        title2NameMap.put("说明", "attr_desc");
        title2NameMap.put("描述", "attr_desc");
        title2NameMap.put("默认值", "attr_default_value");
        Map<Integer, String> ans = new HashMap<>();
        int size = titleNodes.size();
        String content;
        for (int i = 0; i < size; i++) {
            content = titleNodes.get(i).regex(TdtConfig.TITLE_REGEX_STRING).toString().trim();

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
     */
    private void parseClazz(List<List<Selectable>> selectables, String url) {
        JSONObject item = menuModel.getItem(url, TdtMenuModel.KEY_TYPE_ENUM.URL);
        if (item == null) {
            throw new IllegalArgumentException("cant not find class");
        }

        String name = item.getString("name");

        List<String> names = menuModel.getModuleNameByPid(item.getInteger("pId") + "");
        //set module name
        setModuleNames(this.moduleNames, names);

        //parse clazz
        this.clazzObj = parseClazzInfo(selectables, url, name, item);
        //parse enum
        this.enumList = parseEnumList(selectables, url);
        //parse method
        this.methodList = parseMethodList(selectables, url, name);
        //parse event
        this.eventList = parseEventList(selectables, url, name);
    }

    public JSONObject getModuleNames() {
        return moduleNames;
    }

    /**
     * parse clazz info
     *
     * @param selectables selectables
     * @param url         url
     * @param name        name
     * @param item        module item info
     * @return clazz obj
     */
    private JSONObject parseClazzInfo(List<List<Selectable>> selectables, String url, String name, JSONObject item) {
        //class object
        JSONObject obj = new JSONObject();

        obj.put("name", name);
        obj.put("url", url);

        List<Selectable> nodes = selectables.get(0).get(0).nodes();
        if (nodes == null || nodes.size() == 0) {
            TdtUtils.printDebug("can not find p in class");
            System.exit(TdtConfig.EXIT_CODE);
        }

        obj.put("class_desc", nodes.get(0).regex("<p>([\\s\\S]+)</p>").get());

        return obj;
    }

    /**
     * set object's module names
     *
     * @param obj   object
     * @param names name list
     */
    private void setModuleNames(JSONObject obj, List<String> names) {
        if (names == null || names.isEmpty()) {
            //must have module
            throw new IllegalArgumentException("can not find module");
        }

        int moduleSize = names.size();
        //first module
        obj.put("module1", names.get(0));

        if (moduleSize == 1) {
            //don't have second module
            obj.put("module2", "无");
        } else {
            //second module
            obj.put("module2", names.get(1));
        }
    }

    public JSONObject getClazzObj() {
        return clazzObj;
    }

    public JSONObject getOptionObj() {
        return optionObj;
    }

    public List<JSONObject> getEnumList() {
        return enumList;
    }

    public List<JSONObject> getMethodList() {
        return methodList;
    }

    public List<JSONObject> getEventList() {
        return eventList;
    }
}
