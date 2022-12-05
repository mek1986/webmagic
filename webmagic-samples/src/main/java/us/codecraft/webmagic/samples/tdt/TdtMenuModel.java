package us.codecraft.webmagic.samples.tdt;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import us.codecraft.webmagic.Page;

import java.util.*;

/**
 * @author: mek
 * Date: 2022\12\1 0001
 * Time: 15:37
 * vx: 250023777
 * Description: save menu info
 * @version: 1.0
 */
public class TdtMenuModel {
    /**
     * id creator
     */
    private Integer externIdCounter = 50000;

    /**
     * menu item list
     */
    private final List<JSONObject> MENU_ITEMS;

    /**
     * id TO MENU_ITEMS map
     */
    private final Map<String, Integer> ID2_MENU_ITEMS = new HashMap<>();

    /**
     * name to MENU_ITEMS map
     */
    private final Map<String, Integer> NAME2_MENU_ITEMS = new HashMap<>();

    /**
     * url to MENU_ITEMS map
     */
    private final Map<String, Integer> URL2_MENU_ITEMS = new HashMap<>();

    /**
     * page url set
     */
    private final Set<String> PAGE_URLS = new HashSet<>();

    public TdtMenuModel(List<JSONObject> items) {
        MENU_ITEMS = items;
        if (MENU_ITEMS != null && MENU_ITEMS.size() > 0) {
            int size = MENU_ITEMS.size();
            for (int i = 0; i < size; i++) {
                String idKey = String.valueOf(MENU_ITEMS.get(i).getInteger("id"));
                if (MENU_ITEMS.get(i).containsKey("file")) {
                    String url = TdtConfig.CONTENT_PAGE_PREFIX + MENU_ITEMS.get(i).getString("file");
                    //向集合中添加待抓取页面url
                    PAGE_URLS.add(url);
                    URL2_MENU_ITEMS.put(url, i);
                }

                //id重复
                if (ID2_MENU_ITEMS.containsKey(idKey)) {
                    //设置新的id
                    MENU_ITEMS.get(i).put("id", externIdCounter);
                    ID2_MENU_ITEMS.put(externIdCounter + "", i);
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
     * add url to Page object,so crawler can continue download page.
     *
     * @param page page对象
     */
    public void addUrlToPage(Page page) {
        for (String url :
                PAGE_URLS) {
            page.addTargetRequest(url);
        }
    }

    /**
     * get menu item by key
     *
     * @param key  key
     * @param type key type[id|url|name]
     * @return item
     */
    public JSONObject getItem(String key, KEY_TYPE_ENUM type) {
        if (type.value == 1) {
            //id

            return getItem(ID2_MENU_ITEMS.get(key));
        } else if (type.value == 2) {
            //url

            return getItem(URL2_MENU_ITEMS.get(key));
        } else if (type.value == 3) {
            //name

            return getItem(NAME2_MENU_ITEMS.get(key));
        }

        return null;
    }

    /**
     * get item by index
     *
     * @param i index
     * @return menu item
     */
    private JSONObject getItem(Integer i) {
        if (i == null) {
            return null;
        }

        //deep clone
        return JSONObject.parseObject(MENU_ITEMS.get(i).toString());
    }

    /**
     * get class module name by pid
     *
     * @param pid pid
     * @return module list:(1[,2]),1 is first module name,2 is second module name
     */
    public List<String> getModuleNameByPid(String pid) {
        if (Strings.isNullOrEmpty(pid)) {
            return null;
        }

        LinkedList<String> ret = new LinkedList<>();

        JSONObject item = getItem(ID2_MENU_ITEMS.get(pid));
        if (item == null) {
            return null;
        }

        ret.add(item.getString("name"));

        if (item.getInteger("pId") == 0) {
            return ret;
        }

        item = getItem(ID2_MENU_ITEMS.get(item.getInteger("pId") + ""));

        if (item == null) {
            return ret;
        }

        ret.addFirst(item.getString("name"));

        return ret;
    }

    /**
     * MENU_ITEM key enum
     */
    public static enum KEY_TYPE_ENUM {
        /**
         * id type
         */
        ID(1),
        /**
         * url type
         */
        URL(2),
        /**
         * name type
         */
        NAME(3);

        /**
         * enum value
         */
        private final int value;

        private KEY_TYPE_ENUM(int value) {
            this.value = value;
        }
    }

    /**
     * get page model size
     *
     * @return size
     */
    public int getPageModelSize() {
        return URL2_MENU_ITEMS.size();
    }
}
