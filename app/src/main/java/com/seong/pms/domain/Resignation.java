package com.seong.pms.domain;

import java.sql.Date;

public class Resignation {
  private int id;
  private String name;
  private Date outDate; // 퇴사날짜
  private String outReason; // 퇴사사유
  private String outOk; // 퇴사승인여부

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Date getOutDate() {
    return outDate;
  }
  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }
  public String getOutReason() {
    return outReason;
  }
  public void setOutReason(String outReason) {
    this.outReason = outReason;
  }
  public String getOutOk() {
    return outOk;
  }
  public void setOutOk(String outOk) {
    this.outOk = outOk;
  }



}
