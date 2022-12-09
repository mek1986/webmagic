package us.codecraft.webmagic.samples.tdt;

import com.google.common.base.Strings;
import us.codecraft.webmagic.Spider;

import java.io.File;

/**
 * @author: ifelse
 * Date: 2022/12/6
 * VX: 250023777
 * Description: global service
 * @version: 1.0
 */
public class TdtGlobalService {
    public static Spider SPIDER;
    public static TdtDbManage dbManage=new TdtDbManage();

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

    public static void main(String[] args) {

    }
}
