package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;

public class MemberDeleteHandler extends AbstractMemberHandler {

  MemberValidatorHandler memberValidatorHandler;

  public MemberDeleteHandler(List<Member> memberList, MemberValidatorHandler memberValidatorHandler) {
    super(memberList);
    this.memberValidatorHandler = memberValidatorHandler;

  }

  public void delete() {
    if (memberValidatorHandler.firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서삭제/사원정보 삭제]");

    int no = indexOf("삭제할 사원 : ");

    if (no == -1) {
      System.out.println("\n해당 번호의 사원이 없습니다.\n");
      return;
    }

    memberList.remove(no);

    System.out.println("\n사원의 정보가 삭제되었습니다.\n");

  }

}
