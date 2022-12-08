package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: mek
 * Date: 2022\12\3 0003
 * Time: 10:03
 * vx: 250023777
 * Description: db manager
 * @version: 1.0
 */
public class TdtDbManage {
    private List<TdtPageModel> pageDataList = new ArrayList<>();

    private boolean isSaveing = false;

    private final JSONArray classArray = new JSONArray();
    private final JSONArray optionArray = new JSONArray();
    private final JSONArray optionDetailArray = new JSONArray();
    private final JSONArray enumArray = new JSONArray();
    private final JSONArray methodArray = new JSONArray();
    private final JSONArray eventArray = new JSONArray();
    private final JSONObject modules = new JSONObject();

    public List<TdtPageModel> getPageDataList() {
        return pageDataList;
    }

    public void setPageDataList(List<TdtPageModel> pageDataList) {
        this.pageDataList = pageDataList;
    }

    public void addPageData(TdtPageModel data) {
        if (pageDataList == null) {
            pageDataList = new ArrayList<>();
        }

        pageDataList.add(data);
    }

    public boolean save() {
        if (pageDataList == null || pageDataList.size() == 0) {
            System.out.println("no data to save");
            return true;
        }

        if (isSaveing) {
            System.out.println("正在保存中，请稍后再试");
            return false;
        }

        isSaveing = true;

        System.out.println("begin save data to db");

        String version = String.valueOf(System.currentTimeMillis());

        try {
            parseClassArray(version);
            parseOptionArray(version);
            parseEnumArray(version);
            parseMethodArray(version);
            parseEventArray(version);
        } catch (Exception e) {
            System.out.println("save error:" + e.getMessage());
            if (TdtConfig.DEBUG) {
                e.printStackTrace();
            }

            return false;
        } finally {
            isSaveing = false;
        }

        System.out.println("end save! success");

        return true;
    }

    /**
     * parse event array
     *
     * @param version version
     */
    private void parseEventArray(String version) {
        for (TdtPageModel pageModel : pageDataList) {
            List<JSONObject> eventList = pageModel.getEventList();
            if (eventList != null && eventList.size() > 0) {
                parseEventList(eventList, version, pageModel.getPageUrl());
            }
        }

        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("parse event array success");
        }
    }

    /**
     * parse event list
     *
     * @param eventList event list
     * @param version   version
     */
    private void parseEventList(List<JSONObject> eventList, String version, String url) {
        for (JSONObject eventObj : eventList) {
            parseEvent(eventObj, version, url);
        }
    }

    /**
     * parse evetn
     *
     * @param eventObj event object
     * @param version  version
     */
    private void parseEvent(JSONObject eventObj, String version, String url) {
        JSONObject obj = new JSONObject();

        obj.put("eventName", eventObj.getString("event_name"));
        obj.put("belongClassName", eventObj.getString("name"));
        obj.put("params", eventObj.getString("params"));
        obj.put("eventDesc", eventObj.getOrDefault("event_desc", "无"));

        setIdUrlAddTimeVersion(obj, version, url);

        eventArray.add(obj);
    }

    /**
     * parse class array
     *
     * @param version version
     */
    private void parseClassArray(String version) {
        for (TdtPageModel pageModel : pageDataList) {
            JSONObject clazzObj = pageModel.getClazzObj();
            if (clazzObj != null) {
                classArray.add(parseClass(clazzObj, pageModel.getModuleNames(), version, pageModel.getPageUrl()));
            }

            parseModule(pageModel.getModuleNames(), version);
        }

        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("parse class array success");
        }
    }

    private void parseModule(JSONObject moduleNames, String version) {
        JSONObject obj = new JSONObject();

        obj.put("id", UUID.randomUUID());
        obj.put("addTime", new Date());
        obj.put("version", version);

        String[] keys = moduleNames.keySet().toArray(new String[0]);
        if (keys.length == 1) {
            //super module
            String module1 = moduleNames.getString(keys[0]);
            if (!TdtConfig.MODULE_NAME_2_FILE_NAME.containsKey(module1)) {
                throw new IllegalArgumentException("can not find module1" + module1);
            }

            obj.put("moduleName", module1);
            obj.put("fileMame", TdtConfig.MODULE_NAME_2_FILE_NAME.get(module1));
            obj.put("pModuleName", "0");

            if (!this.modules.containsKey(module1)) {
                //do not repeat
                this.modules.put(module1, obj);
            }

            return;
        }

        //super module and sub module
        String module1 = moduleNames.getString(keys[0]);
        String module2 = moduleNames.getString(keys[1]);
        if (!TdtConfig.MODULE_NAME_2_FILE_NAME.containsKey(module1)) {
            throw new IllegalArgumentException("can not find module1" + module1);
        }

        obj.put("moduleName", module1);
        obj.put("fileMame", TdtConfig.MODULE_NAME_2_FILE_NAME.get(module1));
        obj.put("pModuleName", module2);

        String key = module1 + "-" + module2;

        if (!this.modules.containsKey(key)) {
            //do not repeat
            this.modules.put(key, obj);
        }
    }

    /**
     * parse class
     *
     * @param clazzObj    class obj
     * @param moduleNames module names
     * @param version     version
     * @return class
     */
    private Object parseClass(JSONObject clazzObj, JSONObject moduleNames, String version, String url) {
        JSONObject obj = new JSONObject();

        obj.put("className", clazzObj.getString("name"));
        obj.put("classDesc", clazzObj.getOrDefault("class_desc", "无"));
        setIdUrlAddTimeVersion(obj, version, url);
        setModuleNames(obj, moduleNames);

        return obj;
    }

    /**
     * parse option array
     *
     * @param version version
     */
    private void parseOptionArray(String version) {
        for (TdtPageModel pageModel : pageDataList) {
            JSONObject optionObj = pageModel.getOptionObj();
            if (optionObj != null) {
                optionArray.add(parseOption(optionObj, pageModel.getModuleNames(), version, pageModel.getPageUrl()));
                parseOptionDetailArray(optionObj.getJSONArray("detail"), version, pageModel.getPageUrl());
            }
        }

        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("parse option array success");
        }
    }

    /**
     * parse option detail array
     *
     * @param array   option detail array
     * @param version version
     * @param url     url
     */
    private void parseOptionDetailArray(JSONArray array, String version, String url) {
        JSONObject[] jsonObjects = (JSONObject[]) array.toArray(new JSONObject[0]);
        for (JSONObject detailObj : jsonObjects) {
            optionDetailArray.add(parseOptionDetail(detailObj, version, url));
        }

        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("parse option detail array success");
        }
    }

    /**
     * parse option detail
     *
     * @param detailObj option detail
     * @param version   version
     * @return option detail object
     */
    private JSONObject parseOptionDetail(JSONObject detailObj, String version, String url) {
        JSONObject obj = new JSONObject();

        obj.put("objName", detailObj.getString("name"));
        obj.put("attrName", detailObj.getString("attr_name"));
        obj.put("attrType", detailObj.getString("attr_type"));
        obj.put("attrDesc", detailObj.getOrDefault("attr_desc", "无"));
        obj.put("attrDefaultValue", detailObj.getOrDefault("attr_default_value", "无"));
        setIdUrlAddTimeVersion(obj, version, url);

        return obj;
    }

    /**
     * parse option option
     *
     * @param optionObj   option
     * @param moduleNames module names
     * @param version     version
     * @return option object
     */
    private JSONObject parseOption(JSONObject optionObj, JSONObject moduleNames, String version, String url) {
        JSONObject obj = new JSONObject();

        obj.put("objName", optionObj.getString("name"));
        obj.put("objDesc", optionObj.getOrDefault("obj_desc", "无"));
        setIdUrlAddTimeVersion(obj, version, url);
        setModuleNames(obj, moduleNames);

        return obj;
    }

    /**
     * parse enum array
     *
     * @param version version
     */
    private void parseEnumArray(String version) {
        for (TdtPageModel pageModel : pageDataList) {
            List<JSONObject> enumList = pageModel.getEnumList();
            if (enumList != null && enumList.size() > 0) {
                parseEnumList(enumList, pageModel.getModuleNames(), version, pageModel.getPageUrl());
            }
        }

        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("parse enum array success");
        }
    }

    /**
     * parse enum list
     *
     * @param enumList    enum list
     * @param moduleNames module names
     * @param version     version
     */
    private void parseEnumList(List<JSONObject> enumList, JSONObject moduleNames, String version, String url) {
        for (JSONObject enumObj : enumList) {
            parseEnum(enumObj, moduleNames, version, url);
        }
    }

    /**
     * parse enum
     *
     * @param enumObj     enum object
     * @param moduleNames module names
     * @param version     version
     */
    private void parseEnum(JSONObject enumObj, JSONObject moduleNames, String version, String url) {
        String key = enumObj.keySet().toArray(new String[0])[0];
        JSONObject[] jsonArray = (JSONObject[]) enumObj.getJSONArray(key).toArray(new JSONObject[0]);

        for (JSONObject jsonObject : jsonArray) {
            JSONObject obj = new JSONObject();

            obj.put("constName", key);
            obj.put("constValue", jsonObject.getString("const_value"));
            obj.put("constDesc", jsonObject.getOrDefault("const_desc", "无"));
            setIdUrlAddTimeVersion(obj, version, url);
            setModuleNames(obj, moduleNames);

            enumArray.add(obj);
        }
    }

    private void setIdUrlAddTimeVersion(JSONObject obj, String version, String url) {
        obj.put("id", UUID.randomUUID().toString());
        obj.put("fromUrl", url);
        obj.put("addTime", new Date());
        obj.put("version", version);
    }

    private void setModuleNames(JSONObject obj, JSONObject moduleNames) {
        obj.put("moduleName1", moduleNames.getString("module1"));
        obj.put("moduleName2", moduleNames.getString("module2"));
    }

    /**
     * parse method array
     *
     * @param version version
     */
    private void parseMethodArray(String version) {

        for (TdtPageModel pageModel : pageDataList) {
            List<JSONObject> methodList = pageModel.getMethodList();
            if (methodList != null && methodList.size() > 0) {
                parseMethodList(methodList, version, pageModel.getPageUrl(), pageModel);
            }
        }

        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("parse method array success");
        }
    }

    /**
     * parse method list
     *
     * @param methodList method list
     * @param version    version
     * @param pageModel  pageModel
     */
    private void parseMethodList(List<JSONObject> methodList, String version, String url, TdtPageModel pageModel) {
        try {
            for (JSONObject methodObj : methodList) {
                parseMethod(methodObj, version, url, pageModel);
            }
        } catch (Exception e) {
            System.out.println("method parse error");
            System.exit(TdtConfig.EXIT_CODE);
        }
    }

    /**
     * parse method object
     *
     * @param methodObj method
     * @param version   version
     * @param pageModel page model
     */
    private void parseMethod(JSONObject methodObj, String version, String url, TdtPageModel pageModel) {
        Set<String> keys = methodObj.keySet();

        for (String key :
                keys) {
            parseMethod(methodObj.getJSONArray(key), version, url, key, pageModel);
        }
    }

    private void parseMethod(JSONArray array, String version, String url, String cate, TdtPageModel pageModel) {
        JSONObject[] jsonObjects = (JSONObject[]) array.toArray(new JSONObject[0]);
        JSONObject obj;
        for (JSONObject jsonObject : jsonObjects) {
            obj = new JSONObject();

            obj.put("belongClassName", jsonObject.getString("name"));
            obj.put("returnType", jsonObject.getString("return_type"));
            obj.put("rawMethodSign", jsonObject.getString("raw_method_sign"));
            obj.put("methodDesc", jsonObject.getOrDefault("method_desc", "无"));
            obj.put("methodCate", cate);

            parseMethodNameAndParams(obj, pageModel);

            setIdUrlAddTimeVersion(obj, version, url);

            methodArray.add(obj);
        }
    }

    /**
     * parse method name  and method params
     *
     * @param methodObj method object
     * @param pageModel page model
     */
    public void parseMethodNameAndParams(JSONObject methodObj, TdtPageModel pageModel) {
        String methodSign = methodObj.getString("rawMethodSign");

        String pattern = "([a-zA-Z0-9]+)\\s*(\\([^\\(\\))]*\\))?";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(methodSign);

        if (matcher.find()) {
            //parse method name
            methodObj.put("method_name", matcher.group(1).trim());

            String params = matcher.group(2);

            if (Strings.isNullOrEmpty(params)) {
                if (Objects.equal(methodObj.getString("methodCate"), "构造函数")) {
                    parseConstructMethod(methodObj, pageModel);
                    return;
                }

                methodObj.put("params", new JSONObject());
                return;
            }

            params = params.replace("(", "").replace(")", "");

            if (Strings.isNullOrEmpty(params)) {
                //no params
                methodObj.put("params", new JSONObject());
                return;
            }

            pattern = "([^\\[\\]]+)\\s*(\\[[^\\[\\]]+\\])?";
            compile = Pattern.compile(pattern);
            matcher = compile.matcher(params);

            //parse param info
            if (matcher.find()) {
                //parse param type name
                String necessaryParams = matcher.group(1).trim();
                String noNecessaryParams = matcher.group(2);
                String methodDesc = methodObj.getString("methodDesc");


                String[] split = necessaryParams.split(",");
                JSONObject paramObj = new JSONObject();
                //if param don't have name,i is used to generate name
                int i = 1;

                //parse necessary params
                for (String s : split) {
                    String[] param = s.trim().split(":");
                    JSONObject tempObj = new JSONObject();

                    tempObj.put("need", true);
                    if (param.length == 1) {
                        //only have param type
                        tempObj.put("type", param[0].trim());
                        //param name
                        paramObj.put("var" + (++i), tempObj);
                    } else {
                        tempObj.put("type", param[1].trim());
                        paramObj.put(param[0].trim(), tempObj);
                    }
                }

                if (!Strings.isNullOrEmpty(noNecessaryParams)) {
                    //parse no necessary params
                    noNecessaryParams = noNecessaryParams.replace("[,", "").replace("]", "");
                    split = noNecessaryParams.split(",");

                    for (String s : split) {
                        String[] param = s.trim().split(":");
                        JSONObject tempObj = new JSONObject();

                        tempObj.put("need", false);
                        if (param.length == 1) {
                            //only have param type
                            tempObj.put("type", param[0].trim());
                            //param name
                            paramObj.put("var" + (++i), tempObj);
                        } else {
                            tempObj.put("type", param[1].trim());
                            paramObj.put(param[0].trim(), tempObj);
                        }
                    }
                }

                Set<String> keys = paramObj.keySet();
                for (String key : keys) {
                    pattern = key + "(:|：)([^：：</]+)<?";
                    compile = Pattern.compile(pattern);
                    matcher = compile.matcher(methodDesc);

                    if (matcher.find()) {
                        paramObj.getJSONObject(key).put("desc", matcher.group(2).trim());
                        continue;
                    }

                    paramObj.getJSONObject(key).put("desc", "无");
                }

                methodObj.put("params", paramObj);

                //return
                return;
            }
        }

        if (Objects.equal(methodObj.getString("methodCate"), "构造函数")) {
            parseConstructMethod(methodObj, pageModel);
            return;
        }

        throw new IllegalArgumentException("invalid method sign");
    }

    private void parseConstructMethod(JSONObject methodObj, TdtPageModel pageModel) {
        //option object
        JSONObject menuItem = pageModel.getMenuModel().getItem(methodObj.getString("method_name") + "Options", TdtMenuModel.KEY_TYPE_ENUM.NAME);

        JSONObject paramObj = new JSONObject();
        if (menuItem == null) {
            methodObj.put("params", paramObj);
            return;
        }

        JSONObject tempObj = new JSONObject();

        tempObj.put("type", menuItem.get("name"));
        tempObj.put("need", false);
        tempObj.put("desc", "属性对象");
        paramObj.put("opts", tempObj);

        methodObj.put("params", paramObj);
    }

    public static void main(String[] args) {
        TdtDbManage tdtDbManage = new TdtDbManage();
        JSONObject methodObj = new JSONObject();
        methodObj.put("methodCate", "构造函数");
        methodObj.put("rawMethodSign", "在地图上加载一个标绘控件，该控件包含所有标绘图形的绘制类库。");
        methodObj.put("methodDesc", "创建一个地图标绘控件。<br>参数说明：<br>opts：比例尺属性对象，请参考Control.militarySymbolsOptions。");

        tdtDbManage.parseMethodNameAndParams(methodObj, null);

        System.out.println(methodObj.toString());
    }
}
