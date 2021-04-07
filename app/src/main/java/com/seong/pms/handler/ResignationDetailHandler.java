package com.seong.pms.handler;

import com.seong.pms.dao.ResignationDao;
import com.seong.pms.domain.Resignation;

public class ResignationDetailHandler implements Command {

  ResignationDao resignationDao;

  public ResignationDetailHandler(ResignationDao resignationDao) {
    this.resignationDao = resignationDao;
  }

  @Override
  public void service() {
    if (resignationDao.firstPaper()) {
      System.out.println("\n입력된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/선택조회/퇴사신청서 조회]");

    Resignation r = resignationDao.findByResignation("조회할 퇴사신청서 : ");

    if (r == null) {
      System.out.println("\n해당 번호의 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n==================================");
    System.out.println("[퇴사신청서]");
    System.out.printf("\n문서번호 : %d "
        + "\n사번 : %d "
        + "\n이름 : %s "
        + "\n퇴사날짜 : %s "
        + "\n\n상기 %s(본인)은 %s로 인하여 사직서를 제출하오니\n허락하여 주시기 바랍니다."
        + "\n\n승인여부 : %s\n",
        r.getNo(), r.getMemberNo(), r.getName(), r.getOutDate(), 
        r.getName(), r.getReason(), 
        Resignation.getApproval(r.getApproval()));
    System.out.println("==================================\n");
  }
}
