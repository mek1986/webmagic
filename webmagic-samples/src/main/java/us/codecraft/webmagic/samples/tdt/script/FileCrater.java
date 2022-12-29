package us.codecraft.webmagic.samples.tdt.script;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import freemarker.cache.FileTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.*;
import us.codecraft.webmagic.samples.tdt.TdtConfig;
import us.codecraft.webmagic.samples.tdt.entity.TdtClass;
import us.codecraft.webmagic.samples.tdt.mapper.TdtClassDAO;
import us.codecraft.webmagic.samples.tdt.proxy.DaoMapperProxy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: ifelse
 * Date: 2022/12/11
 * VX: 250023777
 * Description: 描述
 * @version: 1.0
 */
public class FileCrater {
    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

    private void setConfig() throws IOException {
        cfg.setTemplateLoader(new FileTemplateLoader(new File(this.getClass().getResource("").getPath() + "template\\")));

//        cfg.setDirectoryForTemplateLoading(file);
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }

    public FileCrater() throws IOException {
        setConfig();
    }

    public boolean createFile() throws IOException {
        TdtClassDAO mapper = DaoMapperProxy.getProxyInstance(TdtClassDAO.class);

        List<TdtClass> classes = mapper.selectByExample(null);

        JSONArray jsonArray = new JSONArray();
        JSONObject obj;

        for (TdtClass aClass : classes) {
            obj = new JSONObject();
            obj.put("name", aClass.getClassName());
            obj.put("obj", "T." + aClass.getClassName());

            jsonArray.add(obj);
        }

        obj = new JSONObject();
        obj.put("content", jsonArray.toString());


        Template temp = cfg.getTemplate("test.js.tpl");
        String path = TdtConfig.HTML_FILE_PATH;
        File dir = new File(path);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("create output dir error");
                return false;
            }
        }

        try (OutputStream os = new FileOutputStream(path + "test.js"); Writer out = new OutputStreamWriter(os);) {
            temp.process(obj, out);
        } catch (FileNotFoundException | ParseException | MalformedTemplateNameException | TemplateException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException, TemplateException {
        FileCrater fileCrater = new FileCrater();
        fileCrater.createFile();
    }
}
