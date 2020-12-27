package com.zzz.demo.util;

import com.zzz.demo.back.GoodsReBack;
import net.sf.json.JSONArray;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class POJOSave {

    public static String savePOJOBatch(Object obj,String data) {
        JSONArray array = JSONArray.fromObject(data);
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<Object> list = new LinkedList<>();
        StringBuffer sb1 = new StringBuffer();
        String sql ="";
        sb1.append("insert into "+clazz.getSimpleName()+" (");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("values");
        for (int i = 0; i <array.size() ; i++) {
            sb2.append("(");
            try {
                Object o = clazz.getConstructor().newInstance();
                Map map = (Map) array.get(i);
                for (Field f : fields) {
                    f.setAccessible(true);
                    //f.set(o, map.get(f.getName()));
                    if(!f.getName().equals("id")){
                        if(map.get(f.getName())!=null){
                            if(i==0){
                                sb1.append(f.getName()+",");
                            }
                            sb2.append("'"+map.get(f.getName())+"',");
                        }
                    }
                }
                list.add(o);
                sb2.setLength(sb2.length()-1);
                sb2.append("),");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        sb1.setLength(sb1.length()-1);
        sb2.setLength(sb2.length()-1);
        sb1.append(")"+sb2.toString());
        sql += sb1.toString();
        return sql;
    }

    public static String savePOJO(Object o,Integer type) {
        //type 1:修改 2：添加
        Class clazz = o.getClass();
        String sql =type==2?"insert into "+clazz.getSimpleName()+"(": "update "+clazz.getSimpleName()+" set ";
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer("values(");
        Field[] fields = clazz.getDeclaredFields();
        Integer id = 0;
        try {
            for (Field f:fields) {
                f.setAccessible(true);
                if(!f.getName().equals("id")){
                    if(type == 2 && f.get(o)!=null){
                        sb1.append(f.getName()+",");
                        sb2.append("'"+f.get(o)+"',");
                    }
                    if(type == 1 && f.get(o)!=null){
                        System.out.println(f.get(o));
                        sql+=""+f.getName()+"='"+f.get(o)+"',";
                    }
                }else {
                    if(type==1){
                        id = (Integer) f.get(o);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(type==1){
            sql=sql.substring(0,sql.length()-1);
            sql+=" where id = "+id+" ";
            return sql;
        }
        sb1.setLength(sb1.length()-1);
        sb2.setLength(sb2.length()-1);
        sb2.append(")");
        sb1.append(")"+sb2.toString());
        sql+=sb1.toString();
        return sql;
    }

    public static List<GoodsReBack> savePOJOBatchSec(String data) {
        JSONArray array = JSONArray.fromObject(data);
        List<GoodsReBack> list = new LinkedList<>();
        for (int i = 0; i <array.size() ; i++) {
            Map map = (Map) array.get(i);
            GoodsReBack goods = new GoodsReBack();
            goods.setPrice(BigDecimal.valueOf(Double.parseDouble((String) map.get("price"))));
            goods.setName((String) map.get("title"));
            goods.setEnsembleTypeId(1);
            goods.setImgFir((String) ((List)map.get("imageUrls")).get(0));
            goods.setImgSec((String) ((List)map.get("imageUrls")).get(0));
            goods.setImgTir((String) ((List)map.get("imageUrls")).get(0));
            list.add(goods);
        }
        return list;
    }

    public static String savePOJOBatchTir(List list) {
        StringBuffer sb = new StringBuffer("declare @goodsId int ");
        list.stream().forEach(p->{
            GoodsReBack goods = (GoodsReBack) p;
            sb.append("insert into goods(name,price,goodsTypeId) values (#{name},#{price},#{ensembleTypeId})");
        });

        return sb.toString();
    }
}
