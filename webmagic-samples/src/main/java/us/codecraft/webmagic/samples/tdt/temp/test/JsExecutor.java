package us.codecraft.webmagic.samples.tdt.temp.test;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author: ifelse
 * Date: 2022/12/13
 * VX: 250023777
 * Description: js script runner
 * @version: 1.0
 */
public class JsExecutor {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // JavaScript code in a String
        String jsScript = "var obj = {name:\"xiaoming\"};";
        engine.eval(jsScript);
        ScriptObjectMirror obj = (ScriptObjectMirror)engine.get("obj");

        System.out.println(obj.get("name"));
    }
}