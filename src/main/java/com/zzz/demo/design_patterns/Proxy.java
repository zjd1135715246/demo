package com.zzz.demo.design_patterns;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description 代理模式
 * @Date 2020/7/4 12:52
 * @Created by zhangjiadong
 */
class ProxyMode {
}

//静态代理
interface TeacherDao{
    void teach();
}

class TeacherDaoImpl implements TeacherDao{

    @Override
    public void teach() {
        System.out.println("teach");
    }
}

class TeacherProxy implements TeacherDao{

    private TeacherDao teacherDao;

    public TeacherProxy(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        teacherDao.teach();
    }
}


//动态代理

interface TeacherDao2{
    void teach();
}

class TeacherDaoImpl2 implements TeacherDao2{

    @Override
    public void teach() {
        System.out.println("teach");
    }
}

class ProxyFactory{

    //维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance(){
        //loader:指定当前目标对象的类加载器
        //第二个参数：目标对象实现的接口类型
        //第三个参数：目标对象处理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (o, method, objects) -> {
            Object invoke = method.invoke(target, objects);
            return invoke;
        });
    }
}


//cglib代理

class TeacherDao3{
    public void teach(){
        System.out.println("teach");
    }
}

