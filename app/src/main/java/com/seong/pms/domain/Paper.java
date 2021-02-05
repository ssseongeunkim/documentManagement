package com.seong.pms.domain;

import java.sql.Date;

public class Paper {
  private int id;
  private String name;
  private String holiday; // 휴가종류
  private Date startDate; // 휴가시작날짜
  private Date endDate; // 휴가끝나는날짜
  private String holidayReason; // 휴가사유
  private String holidayOk; // 휴가승인여부
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
  public String getHoliday() {
    return holiday;
  }
  public void setHoliday(String holiday) {
    this.holiday = holiday;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public String getHolidayReason() {
    return holidayReason;
  }
  public void setHolidayReason(String holidayReason) {
    this.holidayReason = holidayReason;
  }
  public String getHolidayOk() {
    return holidayOk;
  }
  public void setHolidayOk(String holidayOk) {
    this.holidayOk = holidayOk;
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
