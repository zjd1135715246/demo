package com.zzz.demo.design_patterns;

/**
 * 访问者模式
 * @author zhangjiadong
 * @date 2020/7/9
 */
public class VisitorMode {
}

abstract class Action{
    abstract void getMan(Man man);
    abstract void getWoman(Woman woman);
}

class Success extends Action{

    @Override
    void getMan(Man man) {
    }

    @Override
    void getWoman(Woman woman) {
    }
}

class Fail extends Action{
    @Override
    void getMan(Man man) {

    }

    @Override
    void getWoman(Woman woman) {

    }
}

abstract class Person{
    abstract void accept(Action action);
}

class Man extends Person{
    @Override
    void accept(Action action) {

    }
}

class Woman extends Person{
    @Override
    void accept(Action action) {

    }
}
