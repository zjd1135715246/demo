package com.zzz.demo.request;

import lombok.Data;

@Data
public class OrderGoodsBean {

    private Integer userId;
    private Integer pageSize;
    private Integer currentPage;
    private String search;
    private Integer status;

}
