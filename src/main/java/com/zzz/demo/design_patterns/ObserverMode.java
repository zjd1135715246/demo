package com.zzz.demo.design_patterns;

import java.util.List;

/**
 *
 * @Description 观察者模式
 * @Date 2020/7/13 8:10
 * @Created by zhangjiadong
 */
public class ObserverMode {
}

interface Subject{
    void register(Observe o);
    void remove(Observe o);
    void notifyObserve();
}

interface Observe{
    void update(float temperature,float pressure,float humidity);
}



class WeatherData implements Subject{
    private float temperature;
    private float pressure;
    private float humidity;

    private List<Observe> list;

    void setData(float temperature,float pressure,float humidity){
        this.pressure=pressure;
        this.temperature=temperature;
        this.humidity=humidity;
        dataChange();
    }
    void dataChange(){
        notifyObserve();
    }

    @Override
    public void register(Observe o) {
        list.add(o);
    }

    @Override
    public void remove(Observe o) {
        list.remove(o);
    }

    @Override
    public void notifyObserve() {
        for (Observe ob:list) {
            ob.update(this.temperature,this.pressure,this.humidity);
        }
    }


}

class Sina implements Observe{
    private float temperature;
    private float pressure;
    private float humidity;

    void display(){
        System.out.println(this.temperature);
        System.out.println(this.pressure);
        System.out.println(this.humidity);
    }

    @Override
    public void update(float temperature, float pressure, float humidity) {
        this.pressure=pressure;
        this.temperature=temperature;
        this.humidity=humidity;
        display();
    }
}