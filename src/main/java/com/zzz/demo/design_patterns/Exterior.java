package com.zzz.demo.design_patterns;

/**
 * 外观模式
 * @author zhangjiadong
 * @date 2020/7/2
 */
public class Exterior {
}

class DVDPlayer{

    private DVDPlayer(){}

    private static DVDPlayer dvdPlayer = new DVDPlayer();

    public static DVDPlayer getInstance(){
        return dvdPlayer;
    }

    public void player(){
        System.out.println("dvd player");
    }
}

class TVPlayer{
    private TVPlayer(){}

    private static TVPlayer tvPlayer = new TVPlayer();

    public static TVPlayer getInstance(){
        return tvPlayer;
    }

    public void player(){
        System.out.println("tv player");
    }
}

class HomeClient{

    private DVDPlayer dvdPlayer;
    private TVPlayer tvPlayer;

    public HomeClient() {
        this.dvdPlayer = DVDPlayer.getInstance();
        this.tvPlayer = TVPlayer.getInstance();
    }

    public void player(){
        dvdPlayer.player();
        tvPlayer.player();
    }
}