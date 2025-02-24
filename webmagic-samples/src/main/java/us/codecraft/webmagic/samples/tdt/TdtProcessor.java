package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.samples.tdt.script.FileCrater;
import us.codecraft.webmagic.selector.Json;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: mek
 * Date: 2022\11\30 0030
 * Time: 16:49
 * vx: 250023777
 * Description: download page, init menu model and parse page
 * @version: 1.0
 */
public class TdtProcessor implements PageProcessor {
    /**
     * web site
     */
    private Site site;

    /**
     * menu model
     */
    private TdtMenuModel menuModel = null;

    /**
     * Processes the page, extract URLs to fetch, extract the data and store.
     *
     * @param page page
     */
    @Override
    public void process(Page page) {
        if (menuModel == null) {
            parseInitTreeFile(page);
            return;
        }


        TdtGlobalService.dbManage.addPageData(new TdtPageModel(menuModel).parsePage(page));
        if (TdtGlobalService.dbManage.getPageDataList().size() >= menuModel.getPageModelSize()) {
            finishDownload(page);
        }
    }

    private void finishDownload(Page page) {
        System.out.println("download finish");

        if (TdtGlobalService.dbManage.save()) {
            FileCrater fileCrater = null;
            try {
                fileCrater = new FileCrater();
                fileCrater.createFile();
                parseClassInfoPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        System.out.println("some error happen when save data to db");
    }

    private void parseInitTreeFile(Page page) {
        initMenuModel(page);
        menuModel.addUrlToPage(page);
    }

    private void parseClassInfoPage() throws IOException {
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/driver/chromedriver.exe").getPath());

        String path = TdtConfig.SITE_URL + "getClassInfo.html";
        ChromeDriver driver = new ChromeDriver();
        driver.get(path);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String classInfo = driver.findElementById("content").getText();
        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("classInfo" + classInfo);
        }

        TdtGlobalService.helpInfo = JSONObject.parseObject(classInfo);
        TdtGlobalService.generate();

        driver.quit();
    }

    private void saveToFile(Page page) throws Exception {
        String fileName = page.getUrl().regex("/([^/]+)\\.[a-zA-Z]+$").toString();

        save(fileName, page.getHtml().get());
    }

    private void save(String fileName, String content) throws Exception {
        TdtUtils.saveFile(TdtConfig.HTML_FILE_PATH + fileName + ".html", content);
        System.out.println(fileName + " save success");
    }

    /**
     * init menu model with Page object
     *
     * @param page Page object
     */
    private void initMenuModel(Page page) {
        String sourceText = page.getRawText();
        String pattern = "(\\[\\{id:1,pId:0,name:\"地图主类\"[^\\[\\]]+?file:\"pages-class/militarySymbols/Control.militarySymbols\\.html\"\\}\\])";

        Pattern compileRes = Pattern.compile(pattern);

        Matcher matcher = compileRes.matcher(sourceText);
        if (matcher.find()) {
            String matcherStr = matcher.group(0);
            matcherStr = matcherStr.replaceAll("!0", "false")
                    .replaceAll("!1", "false");

            TdtUtils.printDebug(matcherStr);
            List<JSONObject> list = new Json(matcherStr).toList(JSONObject.class);

            //设置菜单模型
            menuModel = new TdtMenuModel(list);
            return;
        }

        System.out.println("menu match nothing");
    }

    /**
     * Returns the site settings.
     *
     * @return site
     * @see Site
     */
    @Override
    public Site getSite() {
        //init web site
        if (site == null) {
            site = Site.me().setDomain(TdtConfig.DOMAIN).
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        }

        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new TdtProcessor());
        TdtGlobalService.SPIDER = spider;
        spider.setSpiderListeners(Lists.newArrayList(new TdtSpiderErrorListener()))
                .thread(5)
                .addUrl(TdtConfig.MENU_PAGE_PREFIX + "initTree.js")
                .run();

        System.out.println("ok");
    }
}
