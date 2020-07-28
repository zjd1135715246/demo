package com.zzz.demo.design_patterns;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * 策略模式
 * @author zhangjiadong
 * @date 2020/7/27
 */
public class StrategyMode {
}

interface FlyBehavior{
    void fly();
}

class NoFlyBehavior implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("cant fly");
    }
}

class GoodFlyBehavior implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("good fly");
    }
}

abstract class Duck{
    //策略接口
    protected FlyBehavior flyBehavior;

    public abstract void display();
}

class WildDuck extends Duck{

    public WildDuck() {
        flyBehavior = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("玩具鸭");
        Collections.emptyList();
    }
}