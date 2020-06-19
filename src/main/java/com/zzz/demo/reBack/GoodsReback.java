package com.zzz.demo.reBack;

import java.math.BigDecimal;

public class GoodsReback {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String imgFir;
    private String imgSec;
    private String imgTir;
    private Integer ensembleTypeId;
    private Integer saleTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImgSec() {
        return imgSec;
    }

    public void setImgSec(String imgSec) {
        this.imgSec = imgSec;
    }

    public String getImgTir() {
        return imgTir;
    }

    public void setImgTir(String imgTir) {
        this.imgTir = imgTir;
    }

    public Integer getEnsembleTypeId() {
        return ensembleTypeId;
    }

    public void setEnsembleTypeId(Integer ensembleTypeId) {
        this.ensembleTypeId = ensembleTypeId;
    }

    public Integer getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Integer saleTotal){
        this.saleTotal = saleTotal;
    }

    @Override
    public String toString() {
        return "GoodsReback{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgFir='" + imgFir + '\'' +
                ", imgSec='" + imgSec + '\'' +
                ", imgTir='" + imgTir + '\'' +
                ", ensembleTypeId=" + ensembleTypeId +
                ", saleTotal=" + saleTotal +
                '}';
    }
}
