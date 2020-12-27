package com.zzz.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryBean {

    private Integer userId;
    private Integer status;
    private Integer nowTotal;
    private Integer total;

}
