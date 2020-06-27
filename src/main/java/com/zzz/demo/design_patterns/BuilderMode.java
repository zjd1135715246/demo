package com.zzz.demo.design_patterns;

/**
 * 建造者模式
 * @author zhangjiadong
 * @date 2020/6/27
 */
public class BuilderMode {
}

//房子类
class House{
    private String foundation;
    private String fence;
    private String roof;
}
//房子建造者
abstract class HouseBuild{
    private House house = new House();

    public abstract void buildFoundation();
    public abstract void buildFence();
    public abstract void buildRoof();

    public House buildHouse(){
        return house;
    }
}
//具体某种房子
class bungalowHouse extends HouseBuild{

    @Override
    public void buildFoundation() {

    }

    @Override
    public void buildFence() {

    }

    @Override
    public void buildRoof() {

    }
}

//指挥者
class HouseDirector{
    HouseBuild houseBuild =null;

    public void setHouseBuild(HouseBuild houseBuild) {
        this.houseBuild = houseBuild;
    }

    public House constructorHouse(){
        houseBuild.buildFoundation();
        houseBuild.buildFence();
        houseBuild.buildRoof();
        return houseBuild.buildHouse();
    }
}