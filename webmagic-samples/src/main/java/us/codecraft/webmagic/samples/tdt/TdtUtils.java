package us.codecraft.webmagic.samples.tdt;

import java.util.List;

/**
 * @author: mek
 * Date: 2022\12\3 0003
 * Time: 10:23
 * vx: 250023777
 * Description: util tool
 * @version: 1.0
 */
final public class TdtUtils {
    /**
     * print TdtConfig.DEBUG info
     *
     * @param texts content texts
     */
    public static void printDebug(String... texts) {
        for (String text :
                texts) {
            System.out.println("[tdt DEBUG info]");
            System.out.println(TdtConfig.DEBUG_PREFIX_TEXT + text);
            System.out.println("[end tdt DEBUG]");
        }
    }

    /**
     * print TdtConfig.DEBUG info
     *
     * @param texts content texts
     */
    public static void printDebug(List<String> texts) {
        for (String text :
                texts) {
            System.out.println("[tdt DEBUG info]");
            System.out.println(TdtConfig.DEBUG_PREFIX_TEXT + text);
            System.out.println("[end tdt DEBUG]");
        }
    }
}
