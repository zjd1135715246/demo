package com.zzz.demo.design_patterns;

/**
 * 简单工厂
 *
 * @author zhangjiadong
 * @date 2020/6/18
 */
public class SimpleFactory {}

abstract class pizza{

    abstract void preparation();
    void bake(){}
    void cut(){}
    void box(){}
}

class CheesePizza extends pizza{

    @Override
    void preparation() {

    }
}
