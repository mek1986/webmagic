package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Sets;
import us.codecraft.webmagic.Spider;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author: ifelse
 * Date: 2022/12/6
 * VX: 250023777
 * Description: global service
 * @version: 1.0
 */
public class TdtGlobalService {
    public static Spider SPIDER;
    public static JSONObject helpInfo;
    public static TdtDbManage dbManage = new TdtDbManage();
    public static Set<String> titleSet = Sets.newConcurrentHashSet();
    public static Map<String, JSONObject> classHelp = new HashMap<>();
    public static Map<String, JSONObject> moduleMap = new HashMap<>();

    public static void saveUrl2File() {
        File path = new File(TdtConfig.HTML_FILE_PATH);

        File[] files = path.listFiles();

        if (files != null && files.length > 0) {
            StringBuilder sb = new StringBuilder();

            for (File file : files) {
                String fileName = file.getName();
                if (fileName.contains(".html")) {
                    sb.append(fileName).append(",");
                }
            }

            if (sb.length() > 0) {
                //delete last ","
                sb.deleteCharAt(sb.length() - 1);
                String content = sb.toString();

                TdtUtils.saveFile(TdtConfig.HTML_FILE_PATH + "url.txt", content);
            }
        }
    }

    public static String[] readUrlListFromFile() {
        String content = TdtUtils.readFile(TdtConfig.HTML_FILE_PATH + "url.txt");
        if (Strings.isNullOrEmpty(content)) {
            return null;
        }

        return content.split(",");
    }

    public static boolean generate() {
        buildModule();

        return true;
    }

    private static void buildModule() {
        initModuleMap();
        setClassList();
        setMethodList();
        setEventList();
        setOption();
        setEnumList();

        if (TdtConfig.DEBUG) {
            JSONObject[] objects = moduleMap.values().toArray(new JSONObject[0]);
            for (JSONObject obj :
                    objects) {
                TdtUtils.printDebug("模块信息:", obj.toString());
            }
        }
    }

    private static void initModuleMap() {
        JSONObject[] jsonObjects = dbManage.modules.values().toArray(new JSONObject[0]);
        for (JSONObject jsonObject : jsonObjects) {
            if (Objects.equal(jsonObject.getString("pModuleName"), "0")) {
                JSONObject obj = new JSONObject();
                obj.put("fileName", jsonObject.getString("fileName"));
                obj.put("version", jsonObject.getString("version"));
                obj.put("moduleName", jsonObject.getString("moduleName"));
                moduleMap.put(jsonObject.getString("moduleName"), obj);
            }
        }
    }

    private static void setEnumList() {
        for (JSONObject obj : dbManage.enumArray.toJavaList(JSONObject.class)) {
            JSONObject module = moduleMap.get(obj.getString("moduleName1"));
            List<JSONObject> enumList = (ArrayList) module.getOrDefault("enumList", new ArrayList<>());
            enumList.add(obj);
            module.put("enumList", enumList);
        }
    }

    private static void setOption() {
        for (JSONObject obj : dbManage.optionDetailArray.toJavaList(JSONObject.class)) {
            JSONObject clazz = classHelp.get(obj.getString("objName").replace("Options", ""));
            List<JSONObject> optionDetailList = (ArrayList) clazz.getOrDefault("optionDetailList", new ArrayList<>());
            optionDetailList.add(obj);
            clazz.put("optionDetailList", optionDetailList);
        }
    }

    private static void setEventList() {
        for (JSONObject obj : dbManage.eventArray.toJavaList(JSONObject.class)) {
            JSONObject clazz = classHelp.get(obj.getString("belongClassName"));
            List<JSONObject> methodList = (ArrayList) clazz.getOrDefault("eventList", new ArrayList<>());
            methodList.add(obj);
            clazz.put("eventList", methodList);
        }
    }

    private static void setMethodList() {
        for (JSONObject obj : dbManage.methodArray.toJavaList(JSONObject.class)) {
            JSONObject clazz = classHelp.get(obj.getString("belongClassName"));
            List<JSONObject> methodList = (ArrayList) clazz.getOrDefault("methodList", new ArrayList<>());
            methodList.add(obj);
            clazz.put("methodList", methodList);
        }
    }

    private static void setClassList() {
        List<JSONObject> removeList = new ArrayList<>();
        for (JSONObject obj :
                dbManage.classArray.toJavaList(JSONObject.class)) {
            String className = obj.getString("className");
            if (helpInfo.getJSONObject("notExist").containsKey(className)) {
                removeList.add(obj);
                continue;
            }

            if (helpInfo.getJSONObject("child").containsKey(className)) {
                obj.put("parent", helpInfo.getJSONObject("child").getString(className));
            }

            JSONObject module = moduleMap.get(obj.getString("moduleName1"));
            List<JSONObject> classList = (ArrayList) module.getOrDefault("classList", new ArrayList<>());
            classList.add(obj);
            module.put("classList", classList);
            classHelp.put(className, obj);
        }

        if (removeList.size() > 0) {
            for (JSONObject obj :
                    removeList) {
                System.out.println("删除不存在的类:" + obj.getString("className"));
                dbManage.classArray.remove(obj);
            }
        }
    }

    public static void main(String[] args) {

    }
}
