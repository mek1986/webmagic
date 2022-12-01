package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: mek
 * Date: 2022\11\30 0030
 * Time: 16:49
 * vx: 250023777
 * Description: 爬虫处理器
 * @version: 1.0
 */
public class TdtProcessor implements PageProcessor {
    /**
     * 站点
     */
    private Site site;
    /**
     * 菜单模型
     */
    private volatile TdtMenuModel menuModel = null;

    /**
     * Processes the page, extract URLs to fetch, extract the data and store.
     *
     * @param page page
     */
    @Override
    public void process(Page page) {
        if (menuModel == null) {
            synchronized (TdtConfig.MENU_INIT_KEY.intern()) {
                if (menuModel == null) {
                    initMenuModel(page);
                    menuModel.addUrlToPage(page);
                }
            }

            return;
        }
    }

    /**
     * 初始化菜单模型
     *
     * @param page 页面对象
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

            System.out.println(matcherStr);
            System.out.println("******");
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
        //site定义抽取配置，以及开始url等
        if (site == null) {
            site = Site.me().setDomain(TdtConfig.DOMAIN).
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
        }

        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TdtProcessor())
                .thread(5)
                .addUrl(TdtConfig.MENU_PAGE_PREFIX + "initTree.js")
                .run();

        System.out.println("ok");
    }
}
