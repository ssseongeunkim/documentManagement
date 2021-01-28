package com.seong.pms.handler;

import com.seong.pms.App;
import com.seong.pms.domain.Member;
import com.seong.pms.domain.Paper;
import com.seong.util.Prompt;

public class UpdateHandler {
  MemberHandler memberHandler;

  boolean service = true;

  public UpdateHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  public void service() {

    while (service)
    {
      Prompt.println("");
      String menu = Prompt.inputString("=> 서류수정메뉴입니다. <=\n1. 내용 수정\n2. 문서 삭제\n0. 뒤로가기\n99. 종료\n> ");

      if (menu.equals("1"))
      {
        update();
      } else if (menu.equals("2"))
      {

      } else if (menu.equals("0"))
      {
        Prompt.println("뒤로갑니다.");
        break;
      } else if (menu.equals("99"))
      {
        Prompt.println("시스템을 종료합니다.");
        App.company = false; 
        break;
      } else if (Prompt.inputCheck(menu))
      {
        Prompt.println("입력형식이 맞지 않습니다. 숫자만 입력해 주세요.");
      } else
      {
        Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");
        continue;
      }

      //      break;
    }
  }

  void update() {
    while (true)
    {
      Prompt.println("");

      int holidayCheck = 0;
      int outCheck = 0;
      Member member;
      Paper paper;

      for (int i = 0; i < MemberHandler.turn; i++)
      {
        member = MemberHandler.members[i];
        paper = PaperHandler.papers[i];

        Prompt.println(String.format("%s(%d)직원의 정보", member.name, member.id));

        if (paper.holidayReason != null && paper.holidayOk.equals("미승인"))
        {
          Prompt.println(String.format("%s(%d)직원의 휴가신청서", member.name, member.id));
          holidayCheck++;
        }

        if (paper.outReason != null && paper.outOk.equals("미승인"))
        {
          Prompt.println(String.format("%s(%d)직원의 사직서", member.name, member.id));
          outCheck++;
        }
      }

      String valuse = Prompt.inputString("0. 뒤로가기\n99. 종료\n> ");

      if (valuse.equals("0"))
      {
        // 뒤로가기
        Prompt.println("뒤로갑니다.");
        break;
      } else if (valuse.equals("99"))
      {
        // 종료
        Prompt.println("시스템을 종료합니다.");
        service = false;
        App.company = false; // 시스템 while 종료
        break;
      } // if

      // 사용자가 입력한 직원의 존재여부 확인을 위한 변수
      int checkIndex = memberHandler.exist(valuse);

      if (-1 == checkIndex)
      {
        Prompt.println("없는 직원입니다. 다시 입력해주세요.");

        continue; 
      }

      Prompt.println("");

      member = MemberHandler.members[checkIndex];
      paper = PaperHandler.papers[checkIndex];

      Prompt.println(String.format("%s(%d)직원의 정보", member.name, member.id));

      if (holidayCheck > 0 && outCheck > 0)
      {
        Prompt.println(String.format("%s(%d)직원의 휴가신청서", member.name, member.id));
        Prompt.println(String.format("%s(%d)직원의 사직서", member.name, member.id));

        int userChoice = Prompt.inputInt(String.format("수정 할 문서를 선택하세요.(1. %s(%d)직원의 정보 2. 휴가신청서 3. 사직서)> ", member.name, member.id));

        if (userChoice == 1)
        {
          Prompt.println(String.format("%s(%d)직원의 정보문서 수정을 시작합니다.", member.name, member.id));

        }
      } else if (holidayCheck > 0)
      {
        Prompt.println(String.format("%s(%d)직원의 휴가신청서", member.name, member.id));
      } else if (outCheck > 0)
      {
        Prompt.println(String.format("%s(%d)직원의 사직서", member.name, member.id));
      }





      break;
    }
  }
}
