package com.zzz.demo.service.serviceimpl;

import com.zzz.demo.dao.GoodsDao;
import com.zzz.demo.dao.UserDao;
import com.zzz.demo.entity.Goods;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.back.GoodsReBack;
import com.zzz.demo.back.OrderGoodsReBack;
import com.zzz.demo.back.ReBackMessage;
import com.zzz.demo.request.OrderGoodsBean;
import com.zzz.demo.service.GoodsService;
import com.zzz.demo.util.OtherUtil;
import com.zzz.demo.util.POJOSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void saveData(String data) {
        //String sql=POJOSave.savePOJOBatch(new Goods(),data);
        List list =POJOSave.savePOJOBatchSec(data);
        list.stream().forEach(goods->{
            goodsDao.saveGoods((GoodsReBack) goods);
            goodsDao.saveGoodsImg((GoodsReBack) goods);
        });
        //String sql = POJOSave.savePOJOBatchTir(list);
        //userDao.savePOJOBySql(sql);
    }

    @Override
    public ReBackMessage goodsDetailed(Goods goods) {
        List<GoodsReBack> list = goodsDao.goodsDetailed(goods);
        return new ReBackMessage(200,"操作成功",list);
    }

    @Override
    public ReBackMessage goodsList(Integer typeId, Integer pageSize, Integer currentPage, String searchName) {
        List<Integer> page = OtherUtil.pageManager(currentPage,pageSize);
        String key = "list:goods"+currentPage;
        List<GoodsReBack> list = new ArrayList<>();
        /*List re = (List) redisTemplate.opsForList().leftPop(key);
        System.out.println(re);
        if(re!=null){
            return new RebackMessage(200,"查询成功", re);
        }*/
        list=goodsDao.goodsList(typeId,page.get(0),page.get(1),searchName);
        list.stream().forEach(p->{
            p.setSaleTotal((int) (Math.random() * 100000));
        });
        //redisTemplate.opsForList().leftPush(key,list);
        return new ReBackMessage(200,"查询成功",list);
    }

    @Override
    public ReBackMessage myCart(OrderGoodsBean goodsBean) {
        List<Integer> pageList = OtherUtil.pageManager(goodsBean.getCurrentPage(),goodsBean.getPageSize());
        List<OrderGoodsReBack> list = goodsDao.myCart(goodsBean.getUserId(),pageList.get(0),pageList.get(1));
        return new ReBackMessage(200,"查询成功",list);
    }

    @Override
    public ReBackMessage delCartOneGoods(Integer id) {
        int count = goodsDao.delCartOneGoods(id);
        int code = count>0?200:500;
        String message =code==200?"操作成功":"网络异常";
        return new ReBackMessage(code,message);
    }

    @Override
    public ReBackMessage addMyCart(MyCart cart) {
        int count = goodsDao.executeSql(cart);
        int code =count>0?200:500;
        String msg = code==200?"操作成功":"网络异常";
        List<Integer> list = new ArrayList<Integer>();
        list.add(cart.getId());
        return new ReBackMessage(code,msg,list);
    }

    @Override
    public ReBackMessage addGoods(GoodsReBack goods) {
        goods.setEnsembleTypeId(1);
        goodsDao.saveGoods(goods);
        goodsDao.saveGoodsImg(goods);
        return null;
    }
}
