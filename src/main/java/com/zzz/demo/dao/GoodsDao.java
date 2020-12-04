package com.zzz.demo.dao;

import com.zzz.demo.entity.Goods;
import com.zzz.demo.entity.MyCart;
import com.zzz.demo.back.GoodsReBack;
import com.zzz.demo.back.OrderGoodsReBack;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select a.id,a.name,a.price,b.imgFir,b.imgSec,b.imgTir,c.ensembleId ensembleTypeId from goods a left join goodsimage  b on a.id=b.goodsId left join goodstype c on a.goodsTypeId=c.typeId  where a.id=#{id}")
    public List<GoodsReBack> goodsDetailed(Goods goods);

    List<GoodsReBack> goodsList(Integer typeId, Integer nowCount, Integer total, String searchName);

    List<OrderGoodsReBack> myCart(Integer userId, Integer nowCount, Integer total);

    @Delete("delete from myCart where id=#{id} ")
    int delCartOneGoods(Integer id);

    int executeSql(MyCart cart);

    void saveGoods(GoodsReBack goods);

    @Insert("insert into goodsimage(goodsId,imgFir,imgSec,imgTir) values(#{id},#{imgFir},#{imgSec},#{imgTir})")
    void saveGoodsImg(GoodsReBack goods);
}
