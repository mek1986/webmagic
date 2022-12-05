package us.codecraft.webmagic.samples.tdt;

import java.util.ArrayList;
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
        System.out.println("save to db");

        return true;
    }
}
