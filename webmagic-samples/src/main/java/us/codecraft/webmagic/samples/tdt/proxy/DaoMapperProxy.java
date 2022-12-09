package us.codecraft.webmagic.samples.tdt.proxy;

import java.lang.reflect.Proxy;

/**
 * @author: mek
 * Date: 2022\12\9 0009
 * Time: 19:24
 * vx: 250023777
 * Description: 描述
 * @version: 1.0
 */
final public class DaoMapperProxy{
    public static <T> T getProxyInstance(Class clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz}, new MapperInterceptor<T>());
    }
}
