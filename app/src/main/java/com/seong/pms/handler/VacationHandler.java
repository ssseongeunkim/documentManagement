package com.seong.pms.handler;

import com.seong.pms.domain.Member;
import com.seong.pms.domain.Vacation;
import com.seong.util.List;
import com.seong.util.Prompt;

public class VacationHandler {

  private int paperNo = 0;
  List vacationList = new List();
  MemberHandler memberHandler;

  public VacationHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    if (memberHandler.firstMember()) {
      System.out.println("\n입력된 직원의 정보가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/휴가신청서 작성]");

    Member m = memberHandler.findByMember("문서 작성자 : ");

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


  public void list() {
    if (firstPaper()) {
      System.out.println("\n작성된 휴가신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/휴가신청서 조회]");

    Object[] list = vacationList.toArray();

    Vacation v = new Vacation();

    for (Object arr : list) {
      v = (Vacation) arr;

      System.out.printf("%d. %s, %s, %s, %s, %s, %s\n",
          v.getNo(),
          v.getName(),
          Vacation.getVacation(v.getVacation()),
          v.getStartDate(),
          v.getEndDate(),
          v.getReason(),
          Vacation.getApproval(v.getApproval()));
    }

    System.out.println();

  }


  public void detail() {

  }


  public void update() {
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


  boolean firstPaper() {
    if (vacationList.size() == 0) {
      return true;
    }

    return false;
  }


  Object[] printVacation() {
    Object[] list = vacationList.toArray();

    Vacation v = new Vacation();

    for (Object arr : list) {
      v = (Vacation) arr;

      System.out.printf("%d. 휴가신청서(작성자 : %s(%d))\n", v.getNo(), v.getName(), v.getMemberNo());
    }

    return list;
  }


  Vacation findByVacation(String title) {
    Object[] list = printVacation();

    int no = Prompt.inputInt(title);

    Vacation v = new Vacation();

    for (Object arr : list) {
      v = (Vacation) arr;

      if (v.getNo() == no) {
        return v;
      }
    }

    return null;
  }


}
