package com.zzz.demo.design_patterns;

/**
 * 桥接模式
 * @author zhangjiadong
 * @date 2020/6/28
 */
public class Bridging {
}

//
interface Brand{
    void open();
    void close();
}

abstract class BridgingPhone implements Brand{

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }
}

class XM extends BridgingPhone{

}