package com.seong.pms.handler;

import com.seong.pms.App;
import com.seong.util.Prompt;

public class WriteMenuHandler {

  static boolean write = true;

  public MemberHandler memberHandler = new MemberHandler();
  public PaperHandler paperHandler = new PaperHandler(memberHandler);

  //  public WriteMenuHandler() {
  //    for (int i = 0; i < SIZE; i++)
  //    {
  //      //      MemberHandler.members[i] = new Member();
  //      PaperHandler.papers[i] = new Paper();
  //      PaperHandler.papers[i].holidayOk = "미승인";
  //      PaperHandler.papers[i].outOk = "미승인";
  //    }
  //  }

  public void writeMenu() {
    while (write)
    {
      Prompt.println("");
      String menu = Prompt.inputString("[문서관리 시스템 / 서류입력]\n1. 직원정보\n2. 휴가신청서\n3. 사직서\n0. 뒤로가기\n99. 종료\n> ");

      if (menu.equals("1"))
      {
        memberHandler.add();
      } else if (menu.equals("2") || menu.equals("3"))
      {
        //        휴가신청서, 사직서
        if (memberHandler.memberList.exist())
        {
          Prompt.println("저장된 직원이 없습니다. 직원의 정보를 먼저 입력해주세요.");
          continue;
        }

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
        App.company = false; 
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
