package com.zzz.demo.back;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsReBack {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String imgFir;
    private String imgSec;
    private String imgTir;
    private Integer ensembleTypeId;
    private Integer saleTotal;

}
