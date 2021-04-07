package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Vacation;
import com.seong.util.Prompt;

public class VacationUpdateHandler extends AbstractVacationHandler {

  public VacationUpdateHandler(List<Vacation> vacationHandler) {
    super(vacationHandler);
  }

  @Override
  public void service() {
    if (firstPaper()) {
      System.out.println("\n작성된 휴가신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서수정/휴가신청서 수정]");

    Vacation v = findByVacation("수정할 휴가신청서 : ");

    if (v == null) {
      System.out.println("\n해당 번호의 휴가신청서가 없습니다.\n");
      return;
    }

    v.setVacation(Prompt.inputInt("휴가종류 (1. 반차 2. 연차 3. 병가 4. 경조) : "));
    v.setStartDate(Prompt.inputDate("휴가 시작 날짜 : "));
    v.setEndDate(Prompt.inputDate("휴가 마지막 날짜 : "));
    v.setReason(Prompt.inputString("휴가사유 : "));

    System.out.printf("\n'%s(%d)'님이 작성한 휴가신청서가 수정되었습니다.\n\n", v.getName(), v.getMemberNo());
  }
}
