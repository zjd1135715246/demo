package com.zzz.demo.design_patterns;

import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式
 * @author zhangjiadong
 * @date 2020/7/10
 */
public class IteratorPattern {
}
//系
class DepartmentI{

}

class ComputerCollege implements Iterator{
    //表示是怎么存放的
    DepartmentI[] departmentIS;
    private Integer position;


    public ComputerCollege(DepartmentI[] departmentIS) {
        this.departmentIS = departmentIS;
    }

    @Override
    public boolean hasNext() {

        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}

class InfoCollege implements Iterator{
    //表示是怎么存放的
    List<DepartmentI> departmentIS;
    private Integer position=-1;


    public InfoCollege(List<DepartmentI> departmentIS) {
        this.departmentIS = departmentIS;
    }

    @Override
    public boolean hasNext() {

        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}

interface CollegeI{
    String getName();

    void addDepartment(String name,String desc);

    Iterator createIterator();
}