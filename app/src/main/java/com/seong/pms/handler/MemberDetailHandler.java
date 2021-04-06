package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;

public class MemberDetailHandler extends AbstractMemberHandler {

  MemberValidatorHandler memberValidatorHandler;

  public MemberDetailHandler(List<Member> memberList, MemberValidatorHandler memberValidatorHandler) {
    super(memberList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  public void detail() {
    if (memberValidatorHandler.firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서조회/선택조회/사원정보 조회]");

    Member m = memberValidatorHandler.findByMember("조회할 직원 : ");

    if (m == null) {
      System.out.println("\n해당 번호의 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n==================================");
    System.out.println("[사원정보]");
    System.out.printf("\n사번 : %d "
        + "\n이름 : %s "
        + "\n나이 : %d "
        + "\n부서 : %s "
        + "\n직급 : %s "
        + "\n입사날짜 : %s "
        + "\n사진 : %s\n",
        m.getNo(), m.getName(), m.getAge(), 
        Member.getDepartment(m.getDepartment()), 
        Member.getPosition(m.getPosition()), 
        m.getJoinDate(), m.getPhoto());
    System.out.println("==================================\n");
  }
}
