package com.zzz.demo.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

  @ApiModelProperty(value = "工人的id",name = "id")
  private Integer id;
  @ApiModelProperty(value = "工人的id",name = "account")
  private String account;
  @ApiModelProperty(value = "工人的id",name = "nickname")
  private String nickname;
  @ApiModelProperty(value = "工人的id",name = "password")
  private String password;
  @ApiModelProperty(value = "工人的id",name = "headImg")
  private String headImg;
  @ApiModelProperty(value = "工人的id",name = "addtime")
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private Date addtime;
  @ApiModelProperty(value = "工人的id",name = "money")
  private BigDecimal money;
  @ApiModelProperty(value = "工人的id",name = "couponTotal")
  private Integer couponTotal;
  @ApiModelProperty(hidden = true)
  private List<com.zzz.demo.entity.Discuss> discusses;

}
