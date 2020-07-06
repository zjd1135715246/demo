package com.zzz.demo.design_patterns;

import java.util.HashMap;

/**
 * 享元模式
 * @author zhangjiadong
 * @date 2020/7/3
 */
public class FlyWeight {
}
//外部状态
class User{
    private String name;
}

abstract class WebSite{

    public abstract void use(User user);
}

class ContractWebSite extends WebSite{

    //内部状态
    private String type;

    @Override
    public void use(User user) {
        System.out.println();
    }

    public ContractWebSite(String type) {
        this.type = type;
    }
}


class WebSiteFactory{
    private HashMap<String,ContractWebSite> map = new HashMap<>();

    public WebSite getWebSite(String type){
        if(!map.containsKey(type)){
            map.put(type,new ContractWebSite(type));
        }

        return map.get(type);
    }
}
