package com.zzz.demo.design_patterns;

/**
 * @Description 适配器模式
 * @Date 2020/6/27 9:29
 * @Created by zhangjiadong
 */
public class Adapter {
}

//原有类
class Voltage220 {
    public int outPut220() {
        int src = 220;
        System.out.println("220伏特电压");
        return src;
    }
}

//最终需要的
interface Voltage5{
    int outPut5();
}

//适配器
class adapterVoltage implements Voltage5{

    Voltage220 voltage220;

    public adapterVoltage(Voltage220 voltage220) {
        this.voltage220 = voltage220;
    }

    @Override
    public int outPut5() {
        int dst = voltage220.outPut220()/44;
        return dst;
    }
}

//
class Phone{
    public void charge(Voltage5 voltage5){

        if(voltage5.outPut5()==5){
            System.out.println("可以充电");
        }

    }

}