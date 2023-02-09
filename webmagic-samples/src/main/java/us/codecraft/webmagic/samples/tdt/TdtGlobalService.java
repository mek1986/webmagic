package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import org.apache.http.HttpRequestFactory;
import sun.net.www.http.HttpClient;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientGenerator;
import us.codecraft.webmagic.samples.tdt.script.FileCrater;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public static Map<String, JSONObject> methodHelp = new HashMap<>();
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

    public static boolean generate() throws IOException {
        buildModule();

        FileCrater fileCrater = new FileCrater();
        JSONObject[] objects = moduleMap.values().toArray(new JSONObject[0]);
        if (!fileCrater.createFile(objects[0].getString("fileName"), "module.js.tpl", objects[0], methodHelp)) {
            System.out.println(objects[0].getString("fileName") + "生成失败");
            return false;
        }

        System.out.println(objects[0].getString("fileName") + "生成成功");
        encodeCoreJsFile();

        return true;
    }

    private static void encodeCoreJsFile() throws IOException {
        //code is encode type,can be in these values [None,Numeric,Normal]
        URL localhost = new URL("http://localhost:6001/example-file.php?code=Normal");
        URLConnection con = localhost.openConnection();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));) {
            String content;

            while ((content = bufferedReader.readLine()) != null) {
                System.out.println(content);
            }
        }
    }

    private static void buildModule() {
        initModuleMap();
        setClassList();
        setMethodList();
        setEventList();
        setOption();
        setEnumList();
    }

    private static void initModuleMap() {
        JSONObject[] jsonObjects = dbManage.modules.values().toArray(new JSONObject[0]);
//        for (JSONObject jsonObject : jsonObjects) {
//            JSONObject obj = new JSONObject();
//            obj.put("fileName", jsonObject.getString("fileName"));
//            obj.put("version", jsonObject.getString("version"));
//            obj.put("pModuleName", "0");
//            obj.put("addTime", jsonObject.get("addTime"));
//            if (Objects.equal(jsonObject.getString("pModuleName"), "0")) {
//                obj.put("moduleName", jsonObject.getString("moduleName"));
//                moduleMap.put(jsonObject.getString("moduleName"), obj);
//                continue;
//            }
//
//            obj.put("moduleName", jsonObject.getString("pModuleName"));
//            moduleMap.put(jsonObject.getString("pModuleName"), obj);
//        }
        JSONObject obj = new JSONObject();
        obj.put("fileName", "core_dev.js");
        obj.put("version", jsonObjects[0].getString("version"));
        obj.put("pModuleName", "0");
        obj.put("addTime", jsonObjects[0].get("addTime"));
        obj.put("moduleName", "all");
        moduleMap.put("all", obj);
    }

    private static void setEnumList() {
        JSONObject enums = new JSONObject();
        for (JSONObject obj : dbManage.enumArray.toJavaList(JSONObject.class)) {
//            JSONObject module = moduleMap.get(obj.getString("moduleName1"));
            JSONObject module = moduleMap.get("all");
            JSONObject e = (JSONObject) module.getOrDefault("enums", new JSONObject());
            List<JSONObject> list = (ArrayList) e.getOrDefault(obj.getString("constName"), new ArrayList<>());
            list.add(obj);
            e.put(obj.getString("constName"), list);
            module.put("enums", e);
        }
    }

    private static void setOption() {
        for (JSONObject obj : dbManage.optionDetailArray.toJavaList(JSONObject.class)) {
            JSONObject clazz = classHelp.get(obj.getString("objName").replace("Options", ""));
            if (clazz == null) {
                continue;
            }
            List<JSONObject> optionDetailList = (ArrayList) clazz.getOrDefault("optionDetailList", new ArrayList<>());
            optionDetailList.add(obj);
            clazz.put("optionDetailList", optionDetailList);
        }
    }

    private static void setEventList() {
        for (JSONObject obj : dbManage.eventArray.toJavaList(JSONObject.class)) {
            JSONObject clazz = classHelp.get(obj.getString("belongClassName"));
            if (clazz == null) {
                continue;
            }
            List<JSONObject> methodList = (ArrayList) clazz.getOrDefault("eventList", new ArrayList<>());
            methodList.add(obj);
            clazz.put("eventList", methodList);
        }
    }

    private static void setMethodList() {
        for (JSONObject obj : dbManage.methodArray.toJavaList(JSONObject.class)) {
            JSONObject clazz = classHelp.get(obj.getString("belongClassName"));
            if (clazz == null) {
                continue;
            }
            List<JSONObject> methodList = (ArrayList) clazz.getOrDefault("methodList", new ArrayList<>());
            methodList.add(obj);
            String methodCall = obj.getString("methodCall");
            String pattern = "\\(([^()]+)\\)";
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(methodCall);
            String p = "";
            if (matcher.find()) {
                String group = matcher.group(1);
                p = group;
                String[] params = group.split(",");
                obj.put("paramsName", params);
            }

            obj.put("paramsBody", "(" + p + ")");
            clazz.put("methodList", methodList);
            methodHelp.put(clazz.getString("className") + "-" + obj.getString("methodName"), obj);
        }

        for (JSONObject obj : dbManage.methodArray.toJavaList(JSONObject.class)) {
            JSONObject clazz = classHelp.get(obj.getString("belongClassName"));
            if (clazz == null) {
                continue;
            }

            String p = "";
            if (clazz.getString("parent") != null) {
                JSONObject parentMethod = methodHelp.get(clazz.getString("parent") + "-" + clazz.getString("parent"));
                if (parentMethod != null) {
                    String methodCall = parentMethod.getString("methodCall");
                    String pattern = "\\(([^()]+)\\)";
                    Pattern compile = Pattern.compile(pattern);
                    Matcher matcher = compile.matcher(methodCall);
                    if (matcher.find()) {
                        String group = matcher.group(1);
                        String[] params = group.split(",");
                        if (obj.containsKey("paramsName")) {
                            String[] paramsNames = obj.getObject("paramsName", String[].class);
                            Set<String> paramsNamesSet = Arrays.stream(paramsNames).collect(Collectors.toSet());
                            if (params.length >= paramsNames.length) {
                                for (String s :
                                        params) {
                                    if (!paramsNamesSet.contains(s)) {
                                        obj.getJSONObject("params").put(s, parentMethod.getJSONObject("params").getJSONObject(s));
                                    }
                                }
                                p = group;
                                obj.put("paramsName", params);
                                obj.put("paramsBody", "(" + p + ")");
                            }
                        }
                    }
                }
            }
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
            } else {
                obj.put("parent", null);
                //            JSONObject module = moduleMap.get(obj.getString("moduleName1"));
                JSONObject module = moduleMap.get("all");
                List<JSONObject> classList = (ArrayList) module.getOrDefault("classList", new ArrayList<>());
                classList.add(obj);
                module.put("classList", classList);
            }
            classHelp.put(className, obj);
        }

        if (removeList.size() > 0) {
            for (JSONObject obj :
                    removeList) {
                System.out.println("删除不存在的类:" + obj.getString("className"));
                dbManage.classArray.remove(obj);
            }
        }

        TreeMap<Integer, List<JSONObject>> treeMap = new TreeMap<>();
        for (JSONObject obj :
                dbManage.classArray.toJavaList(JSONObject.class)) {
            String className = obj.getString("className");
            if (helpInfo.getJSONObject("notExist").containsKey(className)) {
                continue;
            }

            if (helpInfo.getJSONObject("child").containsKey(className)) {
                int key = findKey(className, 0);
                List<JSONObject> list = treeMap.getOrDefault(key, new ArrayList<>());
                list.add(obj);
                treeMap.put(key, list);
            }
        }

        JSONObject module = moduleMap.get("all");
        List<JSONObject> classList = (ArrayList) module.getOrDefault("classList", new ArrayList<>());
        treeMap.forEach((k, v) -> {
            for (JSONObject jsonObject : v) {
                classList.add(jsonObject);
            }
        });

        module.put("classList", classList);
    }

    private static int findKey(String className, int i) {
        String parent = helpInfo.getJSONObject("child").getString(className);
        if (helpInfo.getJSONObject("child").containsKey(parent)) {
            return findKey(parent, i + 1);
        }

        return i + 1;
    }

    public static void main(String[] args) {

    }
}
