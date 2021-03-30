package com.seong.pms.domain;

import java.sql.Date;

public class Vacation {
  private int no;
  private String name;
  private int vacation; // 휴가종류
  private Date startDate; // 휴가시작날짜
  private Date endDate; // 휴가끝나는날짜
  private String reason; // 휴가사유
  private int approval; // 휴가승인여부

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getVacation() {
    return vacation;
  }
  public void setVacation(int vacation) {
    this.vacation = vacation;
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
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }
  public int getApproval() {
    return approval;
  }
  public void setApproval(int approval) {
    this.approval = approval;
  }



}
