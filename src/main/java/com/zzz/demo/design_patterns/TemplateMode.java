package com.zzz.demo.design_patterns;

/**
 * @author zhangjiadong
 * @date 2020/7/6
 */
public class TemplateMode {
}

//抽象类，表示模板
abstract class SoyMilk{
    final void make(){
        select();
        addCondiments();
        soak();
    }

    void select(){
        System.out.println("选主材料");
    }
    //选配料
    abstract void addCondiments();

    void soak(){
        System.out.println("制作");
    }
}

class RedSoyMilk extends SoyMilk{

    @Override
    void addCondiments() {
        System.out.println("红豆");
    }
}