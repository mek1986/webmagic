package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: mek
 * Date: 2022\12\3 0003
 * Time: 10:03
 * vx: 250023777
 * Description: manage db
 * @version: 1.0
 */
public class TdtDbManage {
    private List<TdtPageModel> pageDataList = new ArrayList<>();

    private boolean isSave = false;

    private JSONArray classArray = new JSONArray();
    private JSONArray optionArray = new JSONArray();
    private JSONArray optionDetailArray = new JSONArray();
    private JSONArray enumArray = new JSONArray();
    private JSONArray methodArray = new JSONArray();
    private JSONArray eventArray = new JSONArray();
    private JSONArray moduleArray = new JSONArray();

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
        setUrlAddTimeVersion(clazzObj, version);
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
        JSONObject[] jsonObjects = (JSONObject[]) array.toArray();
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
        setUrlAddTimeVersion(detailObj, version);

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
        setUrlAddTimeVersion(optionObj, version);
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
            enumArray.add(parseEnum(enumObj, moduleNames, version));
        }
    }

    /**
     * parse enum
     *
     * @param enumObj     enum object
     * @param moduleNames module names
     * @param version     version
     * @return enum
     */
    private JSONObject parseEnum(JSONObject enumObj, JSONObject moduleNames, String version) {
        JSONObject obj = new JSONObject();

        obj.put("constName", enumObj.get("name"));
        obj.put("constValue", enumObj.get("const_value"));
        obj.put("constDesc", enumObj.getOrDefault("const_desc", "无"));
        setUrlAddTimeVersion(enumObj, version);
        setModuleNames(enumObj, moduleNames);

        return obj;
    }

    private void setUrlAddTimeVersion(JSONObject obj, String version) {
        obj.put("fromUrl", obj.getOrDefault("url", "无"));
        obj.put("addTime", new Date());
        obj.put("version", version);
    }

    private void setModuleNames(JSONObject obj, JSONObject moduleNames) {
        obj.put("moduleName1", moduleNames.getString("module1"));
        obj.put("moduleName2", moduleNames.getString("module2"));
    }
}
