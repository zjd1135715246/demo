package com.zzz.demo.design_patterns;

/**
 * 装饰者模式
 * @author zhangjiadong
 * @date 2020/6/30
 */
public class Decoration {
}

abstract class Drink{
    private String des;
    private float price;

    public void setDes(String des) {
        this.des = des;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public float getPrice() {
        return price;
    }

    public abstract float  cost();
}

class Coffee extends Drink{
    @Override
    public float cost() {
        return getPrice();
    }
}

class BlackCoffee extends Coffee{
    public BlackCoffee() {
        setPrice(3.5f);
    }
}

class Decorator extends Drink{

    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        return super.getPrice()+obj.cost();
    }
}

class milk extends Decorator{

    public milk(Drink obj) {
        super(obj);
        setPrice(3.6f);
    }
}