package com.loadclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
interface IMessage { 											// 传统代理设计必须有接口
    public void send(); 										// 业务方法
}
class MessageReal implements IMessage {						// 真实实现类
    @Override
    public void send() {
        System.out.println("【发送消息】www.mldn.cn");
    }
}
class MLDNProxy implements InvocationHandler {					// 代理类
    private Object target; 									// 真实业务对象
    /**
     *  进行真实业务对象与代理业务对象之间的绑定处理
     * @param target 真实业务对象
     * @return Proxy生成的代理业务对象
     */
    public Object bind(Object target) {
        this.target = target;									// 保存真实对象
        // 依据真实对象的类加载器、实现接口以及代理调用类（InvocationHandler子类）动态创建代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }
    public boolean connect() {								// 代理方法
        System.out.println("【消息代理】进行消息发送通道的连接。");
        return true;
    }
    public void close() {										// 代理方法
        System.out.println("【消息代理】关闭消息通道。");
    }
    @Override
    public Object invoke(Object pro, Method method, Object[] args) throws Throwable {
        Object returnData = null;								// 真实业务处理结果
        if (this.connect()) {									// 通道是否连接
            returnData = method.invoke(this.target, args);		// 调用真实业务
            this.close();									// 通道关闭
        }
        return returnData;									// 返回执行结果
    }
}
public class DynamicDaili {
    public static void main(String[] args) throws Exception {
        IMessage msg = (IMessage) new MLDNProxy().bind(new MessageReal()) ;
        msg.send();
    }
}
