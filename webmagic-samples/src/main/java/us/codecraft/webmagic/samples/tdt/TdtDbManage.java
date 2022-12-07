package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

    private boolean isSave = false;

    private final JSONArray classArray = new JSONArray();
    private final JSONArray optionArray = new JSONArray();
    private final JSONArray optionDetailArray = new JSONArray();
    private final JSONArray enumArray = new JSONArray();
    private final JSONArray methodArray = new JSONArray();
    private final JSONArray eventArray = new JSONArray();
    private final JSONArray moduleArray = new JSONArray();

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

        if (isSave) {
            System.out.println("正在保存中，请稍后再试");
            return false;
        }

        isSave = true;

        System.out.println("begin save data to db");

        String version = String.valueOf(System.currentTimeMillis());

        parseClassArray(version);
        parseOptionArray(version);
        parseEnumArray(version);
        parseMethodArray(version);

        isSave = false;

        return true;
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
                classArray.add(parseClass(clazzObj, pageModel.getModuleNames(), version));
            }
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
    private Object parseClass(JSONObject clazzObj, JSONObject moduleNames, String version) {
        JSONObject obj = new JSONObject();

        obj.put("className", clazzObj.get("name"));
        obj.put("classDesc", clazzObj.getOrDefault("class_desc", "无"));
        setIdUrlAddTimeVersion(clazzObj, version);
        setModuleNames(clazzObj, moduleNames);

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
                optionArray.add(parseOption(optionObj, pageModel.getModuleNames(), version));
                parseOptionDetailArray(optionObj.getJSONArray("detail"), version);
            }
        }
    }

    /**
     * parse option detail array
     *
     * @param array   option detail array
     * @param version version
     */
    private void parseOptionDetailArray(JSONArray array, String version) {
        JSONObject[] jsonObjects = (JSONObject[]) array.toArray(new JSONObject[0]);
        for (JSONObject detailObj : jsonObjects) {
            optionDetailArray.add(parseOptionDetail(detailObj, version));
        }
    }

    /**
     * parse option detail
     *
     * @param detailObj option detail
     * @param version   version
     * @return option detail object
     */
    private JSONObject parseOptionDetail(JSONObject detailObj, String version) {
        JSONObject obj = new JSONObject();

        obj.put("objName", detailObj.get("name"));
        obj.put("attrName", detailObj.get("attr_name"));
        obj.put("attrType", detailObj.get("attr_type"));
        obj.put("attrDesc", detailObj.getOrDefault("attr_desc", "无"));
        obj.put("attrDefaultValue", detailObj.getOrDefault("attr_default_value", "无"));
        setIdUrlAddTimeVersion(detailObj, version);

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
    private JSONObject parseOption(JSONObject optionObj, JSONObject moduleNames, String version) {
        JSONObject obj = new JSONObject();

        obj.put("objName", optionObj.get("name"));
        obj.put("objDesc", optionObj.getOrDefault("obj_desc", "无"));
        setIdUrlAddTimeVersion(optionObj, version);
        setModuleNames(optionObj, moduleNames);

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
                parseEnumList(enumList, pageModel.getModuleNames(), version);
            }
        }
    }

    /**
     * parse enum list
     *
     * @param enumList    enum list
     * @param moduleNames module names
     * @param version     version
     */
    private void parseEnumList(List<JSONObject> enumList, JSONObject moduleNames, String version) {
        for (JSONObject enumObj : enumList) {
            parseEnum(enumObj, moduleNames, version);
        }
    }

    /**
     * parse enum
     *
     * @param enumObj     enum object
     * @param moduleNames module names
     * @param version     version
     */
    private void parseEnum(JSONObject enumObj, JSONObject moduleNames, String version) {
        String key = enumObj.keySet().toArray(new String[0])[0];
        JSONObject[] jsonArray = (JSONObject[]) enumObj.getJSONArray(key).toArray();

        for (JSONObject jsonObject : jsonArray) {
            JSONObject obj = new JSONObject();

            obj.put("constName", key);
            obj.put("constValue", jsonObject.get("const_value"));
            obj.put("constDesc", jsonObject.getOrDefault("const_desc", "无"));
            setIdUrlAddTimeVersion(jsonObject, version);
            setModuleNames(jsonObject, moduleNames);

            enumArray.add(obj);
        }
    }

    private void setIdUrlAddTimeVersion(JSONObject obj, String version) {
        obj.put("fromUrl", obj.getOrDefault("url", "无"));
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
                parseMethodList(methodList, version);
            }
        }
    }

    /**
     * parse method list
     *
     * @param methodList method list
     * @param version    version
     */
    private void parseMethodList(List<JSONObject> methodList, String version) {
        for (JSONObject methodObj : methodList) {
            parseMethod(methodObj, version);
        }
    }

    /**
     * parse method object
     *
     * @param methodObj method
     * @param version   version
     */
    private void parseMethod(JSONObject methodObj, String version) {
        Set<String> keys = methodObj.keySet();

        for (String key :
                keys) {
            parseMethod(methodObj.getJSONArray(key), version, key);
        }
    }

    private void parseMethod(JSONArray methodArray, String version, String cate) {
        JSONObject[] jsonObjects = (JSONObject[]) methodArray.toArray(new JSONObject[0]);
        JSONObject obj;
        for (JSONObject jsonObject : jsonObjects) {
            obj = new JSONObject();

            obj.put("belongClassName", jsonObject.get("name"));
            obj.put("returnType", jsonObject.get("return_type"));
            obj.put("rawMethodSign", jsonObject.get("raw_method_sign"));
            obj.put("methodDesc", jsonObject.getOrDefault("method_desc", "无"));
            obj.put("methodCate", cate);

            parseMethodNameAndParams(obj);

            setIdUrlAddTimeVersion(jsonObject, version);

            methodArray.add(obj);
        }
    }

    /**
     * parse method name  and method params
     *
     * @param methodObj method object
     */
    public void parseMethodNameAndParams(JSONObject methodObj) {
        String methodSign = methodObj.getString("rawMethodSign");

        String pattern = "([a-zA-Z0-9]+)\\(([^\\(\\))]*)\\)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(methodSign);


        if (matcher.find()) {
            //parse method name
            methodObj.put("method_name", matcher.group(1).toString());

            String params = matcher.group(2);

            if (Strings.isNullOrEmpty(params)) {
                methodObj.put("params", new JSONObject());
                return;
            }

            pattern = "([^\\[\\]]+)(\\[[^\\[\\]]+\\])?";
            compile = Pattern.compile(pattern);
            matcher = compile.matcher(params);

            //parse param info
            if (matcher.find()) {
                //parse param type name
                String necessaryParams = matcher.group(1);
                String noNecessaryParams = matcher.group(2);
                String methodDesc = methodObj.getString("methodDesc");


                String[] split = necessaryParams.split(",");
                JSONObject paramObj = new JSONObject();
                //parse necessary params
                for (String s : split) {
                    String[] param = s.split(":");
                    JSONObject tempObj = new JSONObject();

                    tempObj.put("type", param[1]);
                    tempObj.put("need", true);
                    paramObj.put(param[0], tempObj);

                    if (Objects.equal(methodDesc, "无")) {
                        tempObj.put("desc", "无");
                    }
                }

                if (!Strings.isNullOrEmpty(noNecessaryParams)) {
                    //parse no necessary params
                    noNecessaryParams = noNecessaryParams.replace("[,", "").replace("]", "");
                    split = noNecessaryParams.split(",");

                    for (String s : split) {
                        String[] param = s.split(":");
                        JSONObject tempObj = new JSONObject();

                        tempObj.put("type", param[1]);
                        tempObj.put("need", false);
                        paramObj.put(param[0], tempObj);

                        if (Objects.equal(methodDesc, "无")) {
                            tempObj.put("desc", "无");
                        }
                    }
                }

                //parse param desc
                if (!Objects.equal(methodDesc, "无")) {
                    Set<String> keys = paramObj.keySet();

                    for (String key : keys) {
                        pattern = key + "(:|：)([^：：</]+)<?";
                        compile = Pattern.compile(pattern);
                        matcher = compile.matcher(methodDesc);

                        if (matcher.find()) {
                            paramObj.getJSONObject(key).put("desc", matcher.group(2));
                            continue;
                        }

                        paramObj.getJSONObject(key).put("desc", "无");
                    }
                }

                methodObj.put("params", paramObj);

                //return
                return;
            }
        }

        throw new IllegalArgumentException("invalid method sign");
    }

    public static void main(String[] args) {
        TdtDbManage tdtDbManage = new TdtDbManage();
        JSONObject methodObj = new JSONObject();
        methodObj.put("rawMethodSign", "Map(container:String|HTMLElement[,opts:MapOptions])");
        methodObj.put("methodDesc", "在指定的容器内创建地图实例，之后需要调用Map.centerAndZoom()方法对地图进行初始化。未进行初始化的地图将不能进行任何操作。<br />参数说明：<br />container：用于显示地图的DIV对象。<br />opts：地图属性对象，请参考MapOptions。");

        tdtDbManage.parseMethodNameAndParams(methodObj);

        System.out.println(methodObj.toString());

        methodObj = new JSONObject();
        methodObj.put("rawMethodSign", "getDistance(start:LngLat,end:LngLat)");
        methodObj.put("methodDesc", "返回两点之间的距离，单位是米。<br />参数说明：<br />start：起点地理坐标。<br />end：终点地理坐标。");

        tdtDbManage.parseMethodNameAndParams(methodObj);

        System.out.println(methodObj.toString());
    }
}
