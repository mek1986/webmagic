package us.codecraft.webmagic.samples.tdt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Set;

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
        System.out.println("[tdt DEBUG info]");
        for (String text :
                texts) {
            System.out.println(TdtConfig.DEBUG_PREFIX_TEXT + text);
        }
        System.out.println("[end tdt DEBUG]");
    }

    /**
     * print TdtConfig.DEBUG info
     *
     * @param texts content texts
     */
    public static <T> void printDebug(List<T> texts) {
        System.out.println("[tdt DEBUG info]");
        for (T text :
                texts) {
            System.out.println(TdtConfig.DEBUG_PREFIX_TEXT + text.toString());
        }
        System.out.println("[end tdt DEBUG]");
    }

    public static void saveFile(String filePath, String content) {
        File file = new File(filePath);

        try (FileOutputStream fos = new FileOutputStream(file); FileChannel channel = fos.getChannel();) {
            // 将字符串放入缓存区
            ByteBuffer byteBuffer = ByteBuffer.allocate(content.getBytes().length + 1);
            //将字符串以字节形式放入buffer中
            byteBuffer.put(content.getBytes());
            //开始读取
            byteBuffer.flip();
            //从buffer中读取到文件
            channel.write(byteBuffer);
        } catch (Exception e) {
            throw new RuntimeException("file save err");
        }
    }

    public static String readFile(String filePath) {
        File file = new File(filePath);

        try (FileInputStream fis = new FileInputStream(file); FileChannel channel = fis.getChannel();) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 10);

            channel.read(byteBuffer);

            return new String(byteBuffer.array());
        } catch (Exception e) {
            throw new RuntimeException("file read err");
        }
    }
}
