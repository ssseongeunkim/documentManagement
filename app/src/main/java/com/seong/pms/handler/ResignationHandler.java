package com.seong.pms.handler;

import java.util.ArrayList;
import com.seong.pms.domain.Member;
import com.seong.pms.domain.Resignation;
import com.seong.util.Prompt;

public class ResignationHandler {

  private int paperNo = 0;
  private ArrayList<Resignation> resignationList = new ArrayList<>();
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
    if (firstPaper()) {
      System.out.println("\n입력된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/선택조회/퇴사신청서 조회]");

    Resignation r = findByResignation("조회할 퇴사신청서 : ");

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
        + "\n\n상기 %s(본인)은 %s로 인하여 사직서를 제출하오니 허락하여 주시기 바랍니다."
        + "\n\n승인여부 : %s\n",
        r.getNo(), r.getMemberNo(), r.getName(), r.getOutDate(), 
        r.getName(), r.getReason(), 
        Resignation.getApproval(r.getApproval()));
    System.out.println("==================================\n");
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


  public void delete() {
    if (firstPaper()) {
      System.out.println("\n입력된 퇴사신청서가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서삭제/퇴사신청서 삭제]");

    int no = indexOf("삭제할 퇴사신청서 : ");

    if (no == -1) {
      System.out.println("\n해당 번호의 퇴사신청서가 없습니다.\n");
      return;
    }

    resignationList.remove(no);

    System.out.println("\n퇴사신청서가 삭제되었습니다.\n");
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

      System.out.printf("%d. 퇴사신청서(작성자 : %s(%d))\n", r.getNo(), r.getName(), r.getMemberNo());
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


  int indexOf(String title) {
    Object[] list = printResignation();

    int no = Prompt.inputInt(title);

    Resignation r = new Resignation();

    for (int i=0; i<list.length; i++) {
      r = (Resignation) list[i];

      if (r.getNo() == no) {
        return i;
      }
    }

    return -1;
  }


}
