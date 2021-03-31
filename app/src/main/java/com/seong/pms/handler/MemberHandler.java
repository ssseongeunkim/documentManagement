package com.seong.pms.handler;

import com.seong.pms.domain.Member;
import com.seong.util.List;
import com.seong.util.Prompt;

public class MemberHandler {

  private int employeeNo = 0;
  List memberList = new List();

  public void add() {
    System.out.println();

    Member member = new Member();

    member.setNo(++employeeNo);
    member.setName(Prompt.inputString("이름 : "));
    member.setAge(Prompt.inputInt("나이 : "));
    member.setDepartment(Prompt.inputInt("부서(1. 개발팀 2. 디자인팀 3. 마케팅팀) : "));
    member.setPosition(Prompt.inputInt("직위 (1. 대표 2. 부장 3. 과장 4. 대리  5. 사원) : "));
    member.setJoinDate(Prompt.inputDate("입사 날짜 : "));
    member.setPhoto(Prompt.inputString("사진 : "));

    memberList.add(member);

    System.out.printf("%s님의 정보가 입력되었습니다.\n\n", member.getName());

  }


  boolean firstMember() {

    if (memberList.size() == 0) {
      return true;
    }

    return false;
  }


  void printMember() {
    Object[] list = memberList.toArray();

    Member m = new Member();

    for (Object arr : list) {
      m = (Member) arr;

      System.out.printf("%d. %s\n", m.getNo(), m.getName());
    }

  }


  Member findByMember(String title) {
    Object[] list = memberList.toArray();

    printMember();

    int no = Prompt.inputInt(title);

    Member m = new Member();

    for (Object arr : list) {
      m = (Member) arr;

      if (m.getNo() == no) {
        return m;
      }
    }

    return null;
  }




}
