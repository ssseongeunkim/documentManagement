package com.seong.pms.domain;

import java.sql.Date;

public class Vacation {
  private int no;
  private int memberNo;
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
  public int getMemberNo() {
    return memberNo;
  }
  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
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

  public static String getVacation(int vacation) {
    String part = "";

    switch (vacation) {
      case 1:
        part = "연차";
        break;
      case 2:
        part = "반차";
        break;
      case 3:
        part = "병가";
        break;
      case 4:
        part = "경조";
        break;
    }

    return part;
  }

  public static String getApproval (int approval) {
    String part = "";

    switch (approval) {
      case 0:
        part = "미승인";
        break;
      case 1:
        part = "승인";
        break;
    }

    return part;
  }



}
