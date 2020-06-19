package com.zzz.demo.Enum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderEnum {
    ORDERSTATUS_TOPAID(1,"待支付"),
    ORDERSTATUS_SUCCESS(9,"支付成功");

    private Integer typeCode;
    private String typeName;

    public Integer getTypeCode() {
        return typeCode;
    }
    public String getTypeName() {
        return typeName;
    }
}
