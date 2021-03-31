package com.seong.pms.domain;

import java.sql.Date;

public class Resignation {
  private int no;
  private int memberNo;
  private String name;
  private Date outDate; // 퇴사날짜
  private String reason; // 퇴사사유
  private int approval; // 퇴사승인여부

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
  public Date getOutDate() {
    return outDate;
  }
  public void setOutDate(Date outDate) {
    this.outDate = outDate;
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
