package com.zzz.demo.requestBean;

public class OrderQueryBean {

    private Integer userId;
    private Integer status;
    private Integer nowTotal;
    private Integer total;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNowTotal() {
        return nowTotal;
    }

    public void setNowTotal(Integer nowTotal) {
        this.nowTotal = nowTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public OrderQueryBean(Integer userId, Integer status, Integer nowTotal, Integer total) {
        this.userId = userId;
        this.status = status;
        this.nowTotal = nowTotal;
        this.total = total;
    }

    public OrderQueryBean() {
    }
}
