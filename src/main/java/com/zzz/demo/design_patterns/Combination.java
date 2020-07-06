package com.zzz.demo.design_patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * @author zhangjiadong
 * @date 2020/7/1
 */
public class Combination {
}

abstract class OrganizationComponent{
    private String name;

    public void add(OrganizationComponent component){}
    public void remove(){}

    public abstract void print();
}


class University extends OrganizationComponent{

    //这里面是College
    List<OrganizationComponent> list = new ArrayList<>();

    @Override
    public void print() {
        System.out.println("大学");
    }

    @Override
    public void add(OrganizationComponent component) {
        list.add(component);
    }
}

class College extends OrganizationComponent{

    //这里面是department
    List<OrganizationComponent> list = new ArrayList<>();

    @Override
    public void print() {
        System.out.println("学院");
    }

    @Override
    public void add(OrganizationComponent component) {
        list.add(component);
    }
}

class Department extends OrganizationComponent{

    @Override
    public void print() {
        System.out.println("系");
    }

}