package com.zzz.demo.back;

import java.math.BigDecimal;

public class OrderGoodsReback {

    private Integer id;
    private Integer goodsId;
    private Integer quantity;
    private BigDecimal price;
    private String name;
    private String imgFir;
    private String popOne;
    private String popTwo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgFir() {
        return imgFir;
    }

    public void setImgFir(String imgFir) {
        this.imgFir = imgFir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopOne() {
        return popOne;
    }

    public void setPopOne(String popOne) {
        this.popOne = popOne;
    }

    public String getPopTwo() {
        return popTwo;
    }

    public void setPopTwo(String popTwo) {
        this.popTwo = popTwo;
    }
}
