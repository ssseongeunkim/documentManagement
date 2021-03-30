package com.seong.pms.handler;

import com.seong.pms.domain.Member;
import com.seong.pms.domain.Vacation;
import com.seong.util.List;
import com.seong.util.Prompt;

public class VacationHandler {

  List vacationList = new List();
  MemberHandler memberHandler;

  public VacationHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    System.out.println("");

    if (memberHandler.firstMember()) {
      System.out.println("입력된 직원의 정보가 없습니다.");
      return;
    }

    Member m = memberHandler.findByMember("문서 작성자 : ");

    if (m == null) {
      System.out.println("해당 번호의 직원이 없습니다.");
      return;
    }

    Vacation vacation = new Vacation();

    vacation.setNo(m.getNo());
    vacation.setName(m.getName());
    vacation.setVacation(Prompt.inputInt("휴가종류 (1. 반차 2. 연차 3. 병가 4. 경조) : "));
    vacation.setStartDate(Prompt.inputDate("휴가 시작 날짜 : "));
    vacation.setEndDate(Prompt.inputDate("휴가 마지막 날짜 : "));
    vacation.setReason(Prompt.inputString("휴가사유 : "));
    vacation.setApproval(0);

    vacationList.add(vacation);

    System.out.printf("%s님의 휴가신청서가 작성되었습니다./n", vacation.getName());

  }

}
