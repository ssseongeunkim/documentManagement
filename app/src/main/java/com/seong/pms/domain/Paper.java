package com.seong.pms.domain;

import java.sql.Date;

public class Paper {
  public String holiday; // 휴가종류
  public Date startDate; // 휴가시작날짜
  public Date endDate; // 휴가끝나는날짜
  public String holidayReason; // 휴가사유
  public String holidayApproval; // 휴가승인여부
  public Date outDate; // 퇴사날짜
  public String outReason; // 퇴사사유
  public String outApproval; // 퇴사승인여부

}
