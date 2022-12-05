package us.codecraft.webmagic.samples.tdt;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

/**
 * @author: ifelse
 * Date: 2022/12/6
 * VX: 250023777
 * Description: spider error lister
 * @version: 1.0
 */
public class TdtSpiderErrorListener implements SpiderListener {
    @Override
    public void onSuccess(Request request) {
        System.out.println(request.getUrl() + " parse success");
    }

    @Override
    public void onError(Request request, Exception e) {
        System.out.println();
        System.out.println(request.getUrl() + " parse error");
        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug(e.toString());
        }
        TdtGlobalService.SPIDER.close();
    }
}
