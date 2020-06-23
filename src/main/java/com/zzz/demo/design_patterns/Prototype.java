package com.zzz.demo.design_patterns;

import java.io.*;

/**
 * 原型模式，主要通过object的clone方法实现
 *
 * @author zhangjiadong
 * @date 2020/6/23
 */
public class Prototype {
}

class Prototype1 implements Cloneable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}



//深拷贝

class A implements Cloneable, Serializable{
    private String name;
    private Integer age;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class B implements Cloneable, Serializable{

    private String color;
    private A a;

    //重写clone方式
    @Override
    protected Object clone() throws CloneNotSupportedException {
        B b = null;
        b = (B) super.clone();
        b.a = (A) a.clone();
        return b;
    }

    //序列化方式
    public Object BClone(){

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos =null;
        ByteArrayInputStream bis =null;
        ObjectInputStream ois =null;

        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            ois.readObject();
            B copyB = (B)ois.readObject();
            return copyB;
        }catch (Exception e){
            return null;
        }finally {
            try {
                ois.close();
                bis.close();
                oos.close();
                bos.close();
            }catch (Exception e){
            }
        }

    }

}