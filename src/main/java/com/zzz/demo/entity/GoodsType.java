package com.zzz.demo.entity;


public class GoodsType {

  private Integer id;
  private String name;
  private Integer typeId;
  private Integer ensembleId;


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

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getEnsembleId() {
    return ensembleId;
  }

  public void setEnsembleId(Integer ensembleId) {
    this.ensembleId = ensembleId;
  }

  public GoodsType() {
  }

  public GoodsType(Integer id, String name, Integer typeId) {
    this.id = id;
    this.name = name;
    this.typeId = typeId;
  }

  public GoodsType(Integer id, String name, Integer typeId, Integer ensembleId) {
    this.id = id;
    this.name = name;
    this.typeId = typeId;
    this.ensembleId = ensembleId;
  }
}
