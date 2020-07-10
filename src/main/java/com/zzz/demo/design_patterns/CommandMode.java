package com.zzz.demo.design_patterns;

/**
 * 命令模式
 * @author zhangjiadong
 * @date 2020/7/7
 */
public class CommandMode {
}

interface Command{
    void execute();
    void undo();
}

class TvOnCommand implements Command{

    public TvOnCommand(TvReceive tvReceive) {
        this.tvReceive = tvReceive;
    }

    TvReceive tvReceive;
    @Override
    public void execute() {
        tvReceive.on();
    }

    @Override
    public void undo() {
        tvReceive.close();
    }
}

class TvCloseCommand implements Command{

    public TvCloseCommand(TvReceive tvReceive) {
        this.tvReceive = tvReceive;
    }

    TvReceive tvReceive;
    @Override
    public void execute() {
        tvReceive.close();
    }

    @Override
    public void undo() {
        tvReceive.on();
    }
}

class TvReceive{
    void on(){
        System.out.println("电视打开");
    }

    void close(){
        System.out.println("电视关闭");
    }
}