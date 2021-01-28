package com.seong.pms.handler;

import com.seong.pms.App;
import com.seong.pms.domain.Paper;
import com.seong.util.Prompt;

public class WriteMenuHandler {

  static final int SIZE = 5;

  static boolean write = true;

  MemberHandler memberHandler = new MemberHandler();
  PaperHandler paperHandler = new PaperHandler(memberHandler);

  public WriteMenuHandler() {
    for (int i = 0; i < SIZE; i++)
    {
      //      MemberHandler.members[i] = new Member();
      PaperHandler.papers[i] = new Paper();
      PaperHandler.papers[i].holidayOk = "미승인";
      PaperHandler.papers[i].outOk = "미승인";
    }
  }

  public void writeMenu() {
    while (write)
    {
      Prompt.println("");
      String menu = Prompt.inputString("=> 서류작성 메뉴입니다. <=\n1. 직원정보\n2. 휴가신청서\n3. 사직서\n0. 뒤로가기\n99. 종료\n> ");

      if (menu.equals("1"))
      {
        // 회사원 정보 입력
        if (MemberHandler.turn == SIZE)
        {
          Prompt.println("입력란이 없어 더이상 추가입력이 불가능합니다.");
          continue;
        }

        memberHandler.add();

      } else if (menu.equals("2") || menu.equals("3"))
      {
        // 휴가신청서, 사직서
        paperHandler.add(menu);

      } else if (menu.equals("0"))
      {
        // 뒤로가기
        Prompt.println("뒤로갑니다.");
        break;
      } else if (menu.equals("99"))
      {
        // 종료
        Prompt.println("시스템을 종료합니다.");
        App.company = false; // 시스템 while 종료
        break;
      } else if (Prompt.inputCheck(menu))
      {
        Prompt.println("입력형식이 맞지 않습니다. 숫자만 입력해 주세요.");
      } else
      {
        Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");
      } // if
    } // while
  }

}
