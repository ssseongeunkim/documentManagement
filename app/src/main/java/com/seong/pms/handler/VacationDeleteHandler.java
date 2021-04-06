package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Vacation;

public class VacationDeleteHandler extends AbstractVacationHandler {

  public VacationDeleteHandler(List<Vacation> vacationHandler) {
    super(vacationHandler);
  }

  public void delete() {
    if (firstPaper()) {
      System.out.println("\n입력된 휴가신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서삭제/휴가신청서 삭제]");

    int no = indexOf("삭제할 휴가신청서 : ");

    if (no == -1) {
      System.out.println("\n해당 번호의 휴가신청서가 없습니다.\n");
      return;
    }

    vacationList.remove(no);

    System.out.println("\n휴가신청서가 삭제되었습니다.\n");
  }
}
