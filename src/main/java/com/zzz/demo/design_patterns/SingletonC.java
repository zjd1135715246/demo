package com.zzz.demo.design_patterns;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 单例模式
 *
 * @author zhangjiadong
 * @date 2020/6/17
 */
public class SingletonC {}

//饿汉式（静态常量）
class Singleton1{

    private Singleton1(){}

    private final static Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
//饿汉式（静态代码块）
class Singleton2{

    private Singleton2(){}

    private final static Singleton2 INSTANCE ;
    static {
        INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return INSTANCE;
    }
}

//懒汉式(线程不安全)
class Singleton3{

    private Singleton3(){}

    private  static Singleton3 INSTANCE ;

    public static Singleton3 getInstance(){
        if(INSTANCE==null){
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}

//懒汉式(方法级同步，线程安全，不推荐使用)
class Singleton4{

    private Singleton4(){}

    private  static Singleton4 INSTANCE ;

    public static synchronized Singleton4 getInstance(){
        if(INSTANCE==null){
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}

//懒汉式(代码块级同步，线程不安全，不推荐使用)
class Singleton5{

    private Singleton5(){}

    private  static Singleton5 INSTANCE ;

    public static  Singleton5 getInstance(){
        if(INSTANCE==null){
            synchronized (Singleton5.class){
                INSTANCE = new Singleton5();
            }
        }
        return INSTANCE;
    }
}

//双重检查(线程安全，代码块级同步，推荐)
class Singleton6{

    private Singleton6(){}

    //volatile可以将更新同步到主存
    private  static volatile Singleton6 INSTANCE ;

    public static  Singleton6 getInstance(){

        if(INSTANCE==null){
            synchronized (Singleton6.class){
                if (INSTANCE==null){
                    INSTANCE = new Singleton6();
                }
            }
        }
        return INSTANCE;
    }
}

//静态内部类(线程安全，通过jvm的classLoad实现，推荐)
class Singleton7{

    private Singleton7(){}

    private static class Singleton7Instance{
        private  static  Singleton7 INSTANCE = new Singleton7() ;
    }

    public static  Singleton7 getInstance(){
        return Singleton7Instance.INSTANCE;
    }
}

//枚举
enum Singleton8{
    INSTANCE;
}

