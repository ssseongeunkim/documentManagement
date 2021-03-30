package com.seong.pms.handler2;

import com.seong.pms.domain.Member;
import com.seong.util.List;
import com.seong.util.Prompt;

public class MemberHandler {

  private int employeeNo = 0; // 사번
  List memberList = new List();

  void add() {
    Prompt.println("");
    Prompt.println("직원정보 입력을 시작합니다.");

    Member m = new Member();

    m.setId(++this.employeeNo);
    m.setName(Prompt.inputString("이름> "));
    m.setAge(Prompt.inputInt("나이> "));
    m.setJoinDate(Prompt.nextDate("입사날짜(yyyy-MM-dd)> "));

    while (true)
    {
      int userChoice = Prompt.inputInt("부서(1. 개발팀 2. 디자인팀 3. 마케팅팀)> ");

      switch(userChoice)
      {
        case 1 :
          m.setDepartment("개발팀");
          break;
        case 2 :
          m.setDepartment("디자인팀");
          break;
        case 3 :
          m.setDepartment("마케팅팀");
          break;
        default :
          Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");
          Prompt.println("");
      } // switch

      if (1 <= userChoice && userChoice <= 3)
      {
        break;
      }

    } // while

    memberList.add(m);

    Prompt.println(String.format("%s직원의 정보가 입력되었습니다.", m.getName()));
  }

  Member exist(String value) {
    Object[] list = memberList.toArray();

    for (int i = 0; i < list.length; i++)
    {
      Member m = (Member) list[i];

      if (value.equalsIgnoreCase(m.getName()) || value.equals((i + 1) + ""))
      {
        return m;
      }
    }

    return null;
  }

  void memberList() {
    Object[] list = memberList.toArray();

    for (Object obj : list)
    {
      Member m = (Member) obj;

      Prompt.println(String.format("- %s(%d)", m.getName(), m.getId()));
    }
  }
}
