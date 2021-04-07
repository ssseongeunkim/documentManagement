package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;
import com.seong.pms.domain.Vacation;
import com.seong.util.Prompt;

public class VacationAddHandler extends AbstractVacationHandler {

  MemberValidatorHandler memberValidatorHandler;

  public VacationAddHandler(List<Vacation> vacationHandler, MemberValidatorHandler memberValidatorHandler) {
    super(vacationHandler);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    if (memberValidatorHandler.firstMember()) {
      System.out.println("\n입력된 직원의 정보가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/휴가신청서 작성]");

    Member m = memberValidatorHandler.findByMember("문서 작성자 : ");

    if (m == null) {
      System.out.println("\n해당 번호의 직원이 없습니다.\n");
      return;
    }

    Vacation vacation = new Vacation();

    vacation.setNo(++paperNo);
    vacation.setMemberNo(m.getNo());
    vacation.setName(m.getName());
    vacation.setVacation(Prompt.inputInt("휴가종류 (1. 반차 2. 연차 3. 병가 4. 경조) : "));
    vacation.setStartDate(Prompt.inputDate("휴가 시작 날짜 : "));
    vacation.setEndDate(Prompt.inputDate("휴가 마지막 날짜 : "));
    vacation.setReason(Prompt.inputString("휴가사유 : "));
    vacation.setApproval(0);

    vacationList.add(vacation);

    System.out.printf("\n'%s'님의 휴가신청서가 작성되었습니다.\n\n", vacation.getName());

  }

}
