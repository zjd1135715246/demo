package com.zzz.demo.design_patterns;

/**
 * @author zhangjiadong
 * @date 2020/7/24
 */
public class StateMode {
}

interface State{
    abstract void deductMoney();
    abstract void raffle();
    abstract void dis();
}

class NoRadffleState implements State{

    
    @Override
    public void deductMoney() {

    }

    @Override
    public void raffle() {

    }

    @Override
    public void dis() {

    }
}