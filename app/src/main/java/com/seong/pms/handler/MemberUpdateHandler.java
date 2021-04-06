package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;
import com.seong.util.Prompt;

public class MemberUpdateHandler extends AbstractMemberHandler {

  MemberValidatorHandler memberValidatorHandler;

  public MemberUpdateHandler(List<Member> memberList, MemberValidatorHandler memberValidatorHandler) {
    super(memberList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  public void update() {
    if (memberValidatorHandler.firstMember()) {
      System.out.println("\n입력된 직원이 없습니다.\n");
      return;
    }

    System.out.println("\n[문서관리 시스템/문서수정/사원정보 수정]");

    Member m = memberValidatorHandler.findByMember("수정할 사원 : ");

    if (m == null) {
      System.out.println("\n해당 번호의 직원이 없습니다.\n");
      return;
    }

    m.setName(Prompt.inputString("이름 : "));
    m.setDepartment(Prompt.inputInt("부서 (1. 개발팀 2. 디자인팀 3. 마케팅팀) : "));
    m.setPosition(Prompt.inputInt("직위 (1. 대표 2. 부장 3. 과장 4. 대리  5. 사원) : "));
    m.setPhoto(Prompt.inputString("사진 : "));

    System.out.printf("\n'%s'님의 정보가 수정되었습니다.\n\n", m.getName());

  }
}
