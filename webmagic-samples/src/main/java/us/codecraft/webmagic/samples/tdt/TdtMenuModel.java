package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;

import java.util.*;

/**
 * @author: mek
 * Date: 2022\12\1 0001
 * Time: 15:37
 * vx: 250023777
 * Description: 菜单模型
 * @version: 1.0
 */
public class TdtMenuModel {
    /**
     * id冲突解决器
     */
    private Integer externIdCounter = 50000;

    /**
     * 菜单项列表
     */
    private final List<JSONObject> MENU_ITEMS;

    /**
     * id TO MENU_ITEMS map
     */
    private final Map<Integer, Integer> ID2_MENU_ITEMS = new HashMap<>();

    /**
     * name to MENU_ITEMS map
     */
    private final Map<String, Integer> NAME2_MENU_ITEMS = new HashMap<>();
    /**
     * page url set
     */
    private final Set<String> PAGE_URLS = new HashSet<>();

    public TdtMenuModel(List<JSONObject> items) {
        MENU_ITEMS = items;
        if (MENU_ITEMS != null && MENU_ITEMS.size() > 0) {
            int size = MENU_ITEMS.size();
            for (int i = 0; i < size; i++) {
                int idKey = MENU_ITEMS.get(i).getInteger("id");
                if (MENU_ITEMS.get(i).containsKey("file")) {
                    //向集合中添加待抓取页面url
                    PAGE_URLS.add(MENU_ITEMS.get(i).getString("file"));
                }

                //id重复
                if (ID2_MENU_ITEMS.containsKey(idKey)) {
                    //设置新的id
                    MENU_ITEMS.get(i).put("id", externIdCounter);
                    ID2_MENU_ITEMS.put(externIdCounter, i);
                    NAME2_MENU_ITEMS.put(MENU_ITEMS.get(i).getString("name"), i);
                    externIdCounter++;

                    continue;
                }

                ID2_MENU_ITEMS.put(idKey, i);
                NAME2_MENU_ITEMS.put(MENU_ITEMS.get(i).getString("name"), i);
            }
        }
    }

    /**
     * 向page对象中添加url
     *
     * @param page page对象
     */
    public void addUrlToPage(Page page) {
        for (String url :
                PAGE_URLS) {
            page.addTargetRequest(TdtConfig.CONTENT_PAGE_PREFIX + url);
        }
    }
}
