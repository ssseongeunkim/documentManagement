package com.seong.pms.handler;

import com.seong.pms.domain.Member;
import com.seong.pms.domain.Resignation;
import com.seong.util.List;
import com.seong.util.Prompt;

public class ResignationHandler {

  List resignationList = new List();
  MemberHandler memberHandler;

  public ResignationHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void add() {
    System.out.println("");

    if (memberHandler.firstMember()) {
      System.out.println("입력된 직원정보가 없습니다.\n");
      return;
    }

    Member m = memberHandler.findByMember("문서 작성자 : ");

    if (m == null) {
      System.out.println("\n해당 번호의 직원이 없습니다.\n");
      return;
    }

    Resignation resignation = new Resignation();

    resignation.setNo(m.getNo());
    resignation.setName(m.getName());
    resignation.setOutDate(Prompt.inputDate("퇴사날짜 : "));
    resignation.setReason(Prompt.inputString("퇴사사유 : "));
    resignation.setApproval(0);

    resignationList.add(resignation);

    System.out.printf("\n'%s'님의 퇴사신청서가 작성되었습니다.\n\n", resignation.getName());


  }


}
