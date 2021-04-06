package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Resignation;

public class ResignationListHandler extends AbstractResignationHandler {

  public ResignationListHandler(List<Resignation> resignationList) {
    super(resignationList);
  }

  public void list() {
    if (firstPaper()) {
      System.out.println("\n작성된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/퇴사신청서 조회]");

    Object[] list = resignationList.toArray();

    Resignation r = new Resignation();

    for (Object arr : list) {
      r = (Resignation) arr;

      System.out.printf("%d. %s, %s, %s, %s\n",
          r.getNo(),
          r.getName(),
          r.getOutDate(),
          r.getReason(),
          Resignation.getApproval(r.getApproval()));
    }

    System.out.println();
  }
}
