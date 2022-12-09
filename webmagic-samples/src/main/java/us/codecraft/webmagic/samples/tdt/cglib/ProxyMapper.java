package us.codecraft.webmagic.samples.tdt.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: mek
 * Date: 2022\12\9 0009
 * Time: 17:28
 * vx: 250023777
 * Description: proxy
 * @version: 1.0
 */
final public class ProxyMapper implements MethodInterceptor {
    public Object proxyObject(Class claxx){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(claxx);
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return method.invoke(o,objects);
    }
}
