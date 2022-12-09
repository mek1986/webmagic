package us.codecraft.webmagic.samples.tdt.proxy;

import org.apache.ibatis.session.SqlSession;
import us.codecraft.webmagic.samples.tdt.TdtConfig;
import us.codecraft.webmagic.samples.tdt.TdtGlobalService;
import us.codecraft.webmagic.samples.tdt.TdtUtils;
import us.codecraft.webmagic.samples.tdt.entity.TdtClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: mek
 * Date: 2022\12\9 0009
 * Time: 19:25
 * vx: 250023777
 * Description: interceptor
 * @version: 1.0
 */
public class MapperInterceptor<T> implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (TdtConfig.DEBUG) {
            TdtUtils.printDebug("代理开始");
            TdtUtils.printDebug("代理方法为:" + method);
        }

        Class<?> c = method.getDeclaringClass();
        T dao = (T) TdtGlobalService.dbManage.getSqlSession().getMapper(c);
        return method.invoke(dao, args);
    }
}
