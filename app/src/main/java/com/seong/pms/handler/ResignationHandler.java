package com.seong.pms.handler;

import com.seong.pms.domain.Member;
import com.seong.pms.domain.Resignation;
import com.seong.util.List;
import com.seong.util.Prompt;

public class ResignationHandler {

  private int paperNo = 0;
  List resignationList = new List();
  MemberHandler memberHandler;

  public ResignationHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    if (memberHandler.firstMember()) {
      System.out.println("\n입력된 직원정보가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/퇴사신청서 작성]");

    Member m = memberHandler.findByMember("문서 작성자 : ");

    if (m == null) {
      System.out.println("\n해당 번호의 직원이 없습니다.\n");
      return;
    }

    Resignation resignation = new Resignation();

    resignation.setNo(++paperNo);
    resignation.setMemberNo(m.getNo());
    resignation.setName(m.getName());
    resignation.setOutDate(Prompt.inputDate("퇴사날짜 : "));
    resignation.setReason(Prompt.inputString("퇴사사유 : "));
    resignation.setApproval(0);

    resignationList.add(resignation);

    System.out.printf("\n'%s'님의 퇴사신청서가 작성되었습니다.\n\n", resignation.getName());

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


  public void detail() {

  }


  public void update() {
    if (firstPaper()) {
      System.out.println("\n작성된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서수정/퇴사신청서 수정]");

    Resignation r = findByResignation("수정할 퇴사신청서 : ");

    if (r == null) {
      System.out.println("\n해당 번호의 퇴사신청서가 없습니다.\n");
      return;
    }

    r.setOutDate(Prompt.inputDate("퇴사 날짜 : "));
    r.setReason(Prompt.inputString("퇴사사유 : "));

    System.out.printf("\n'%s(%d)'님이 작성한 퇴사신청서가 수정되었습니다.\n\n", r.getName(), r.getMemberNo());
  }


  boolean firstPaper() {
    if (resignationList.size() == 0) {
      return true;
    }

    return false;
  }


  Object[] printResignation() {
    Object[] list = resignationList.toArray();

    Resignation r = new Resignation();

    for (Object arr : list) {
      r = (Resignation) arr;

      System.out.printf("%d. 휴가신청서(작성자 : %s(%d))\n", r.getNo(), r.getName(), r.getMemberNo());
    }

    return list;
  }


  Resignation findByResignation(String title) {
    Object[] list = printResignation();

    int no = Prompt.inputInt(title);

    Resignation r = new Resignation();

    for (Object arr : list) {
      r = (Resignation) arr;

      if (r.getNo() == no) {
        return r;
      }
    }

    return null;
  }


}
