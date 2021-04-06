package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;
import com.seong.pms.domain.Resignation;
import com.seong.util.Prompt;

public class ResignationAddHandler extends AbstractResignationHandler {

  MemberValidatorHandler memberValidatorHandler;

  public ResignationAddHandler(List<Resignation> resignationList, MemberValidatorHandler memberValidatorHandler) {
    super(resignationList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  public void add() {
    if (memberValidatorHandler.firstMember()) {
      System.out.println("\n입력된 직원정보가 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/퇴사신청서 작성]");

    Member m = memberValidatorHandler.findByMember("문서 작성자 : ");

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
}
