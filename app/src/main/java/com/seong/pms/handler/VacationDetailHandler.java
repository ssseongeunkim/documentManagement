package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Vacation;

public class VacationDetailHandler extends AbstractVacationHandler {

  public VacationDetailHandler(List<Vacation> vacationHandler) {
    super(vacationHandler);
  }

  public void detail() {
    if (firstPaper()) {
      System.out.println("\n입력된 휴가신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/선택조회/휴가신청서 조회]");

    Vacation v = findByVacation("조회할 휴가신청서 : ");

    if (v == null) {
      System.out.println("\n해당 번호의 휴가신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n==================================");
    System.out.println("[휴가신청서]");
    System.out.printf("\n문서번호 : %d "
        + "\n사번 : %d "
        + "\n이름 : %s "
        + "\n휴가구분 : %s "
        + "\n휴가기간 : %s ~ %s, "
        + "\n\n상기 %s(본인)은 %s로 인하여 휴가신청서를 제출하오니\n 허락하여 주시기 바랍니다. "
        + "\n\n승인여부 : %s\n",
        v.getNo(), v.getMemberNo(), v.getName(), Vacation.getVacation(v.getVacation()), v.getStartDate(), v.getEndDate(), 
        v.getName(), v.getReason(), Vacation.getApproval(v.getApproval()));
    System.out.println("==================================\n");
  }
}
