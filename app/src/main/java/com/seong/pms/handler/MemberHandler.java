package com.seong.pms.handler;

import com.seong.pms.domain.Member;
import com.seong.util.Prompt;

public class MemberHandler {

  static final int SIZE = 5;

  static int turn = 0; // 배열인덱스
  int employeeNo = 0; // 사번

  public static Member[] members = new Member[SIZE];

  void add() {
    Prompt.println("");
    Prompt.println("직원정보 입력을 시작합니다.");

    Member m = new Member();

    m.id = ++this.employeeNo;
    m.name = Prompt.inputString("이름> ");
    m.age = Prompt.inputInt("나이> ");
    m.joinDate = Prompt.nextDate("입사날짜(yyyy-MM-dd)> ");

    while (true)
    {
      int userChoice = Prompt.inputInt("부서(1. 개발팀 2. 디자인팀 3. 마케팅팀)> ");

      switch(userChoice)
      {
        case 1 :
          m.department = "개발팀";
          break;
        case 2 :
          m.department = "디자인팀";
          break;
        case 3 :
          m.department = "마케팅팀";
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

    System.out.printf("%s직원의 정보가 입력되었습니다.\n", m.name);

    members[turn++] = m;
  }

  int exist(String valuse) {
    for (int i = 0; i < turn; i++)
    {
      if (valuse.equalsIgnoreCase(members[i].name) || valuse.equals((i+1) + ""))
      {
        return i;
      }
    }

    return -1;
  }
}
