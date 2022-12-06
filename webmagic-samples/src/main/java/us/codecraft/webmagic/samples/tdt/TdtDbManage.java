package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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

        parseClassArray();

        isSave = false;

        return true;
    }

    /**
     * parse class array
     */
    private void parseClassArray() {
        for (TdtPageModel pageModel : pageDataList) {
            JSONObject clazzObj = pageModel.getClazzObj();
            if (clazzObj != null) {
                classArray.add(parseClass(clazzObj));
            }
        }
    }

    /**
     * parse class
     *
     * @param clazzObj class obj
     * @return class
     */
    private Object parseClass(JSONObject clazzObj) {
        JSONObject obj = new JSONObject();

        obj.put("className", clazzObj.get("name"));
        obj.put("classDesc", clazzObj.getOrDefault("class_desc", "无"));
        obj.put("moduleName1", clazzObj.get("module_name1"));
        obj.put("moduleName2", clazzObj.getOrDefault("module_name2", "无"));
        obj.put("fromUrl", clazzObj.getOrDefault("url", "无"));
        obj.put("addTime", new Date());

        return obj;
    }

    /**
     * parse option array
     */
    private void parseOptionArray() {
        for (TdtPageModel pageModel : pageDataList) {
            JSONObject clazzObj = pageModel.getClazzObj();
            if (clazzObj != null) {
                classArray.add(parseOptionDetial(clazzObj));
            }
        }
    }

    /**
     * parse option detail
     *
     * @param detail detail obj
     * @return option detail
     */
    private Object parseOptionDetial(JSONObject detail) {
        JSONObject obj = new JSONObject();
        

        return obj;
    }
}
