package com.zzz.demo.back;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderGoodsReBack {

    private Integer id;
    private Integer goodsId;
    private Integer quantity;
    private BigDecimal price;
    private String name;
    private String imgFir;
    private String popOne;
    private String popTwo;


}
