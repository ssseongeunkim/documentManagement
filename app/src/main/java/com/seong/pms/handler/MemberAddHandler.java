package com.seong.pms.handler;

import java.util.List;
import com.seong.pms.domain.Member;
import com.seong.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler {

  public MemberAddHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void service() {
    System.out.println("\n[문서관리 시스템/사원정보 입력]");

    Member member = new Member();

    member.setNo(++employeeNo);
    member.setName(Prompt.inputString("이름 : "));
    member.setAge(Prompt.inputInt("나이 : "));
    member.setDepartment(Prompt.inputInt("부서 (1. 개발팀 2. 디자인팀 3. 마케팅팀) : "));
    member.setPosition(Prompt.inputInt("직위 (1. 대표 2. 부장 3. 과장 4. 대리  5. 사원) : "));
    member.setJoinDate(Prompt.inputDate("입사 날짜 : "));
    member.setPhoto(Prompt.inputString("사진 : "));

    memberList.add(member);

    System.out.printf("%s님의 정보가 입력되었습니다.\n\n", member.getName());

  }

}
