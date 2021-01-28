package com.seong.pms.handler;

import com.seong.pms.App;
import com.seong.pms.domain.Paper;
import com.seong.util.Prompt;

public class PaperHandler {

  static final int SIZE = 5;

  static Paper[] papers = new Paper[SIZE];
  MemberHandler memberHandler;

  public PaperHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  void add(String menu) {
    while (true)
    {
      if (MemberHandler.members[0] == null)
      {
        Prompt.println("저장된 직원이 없습니다. 직원의 정보를 먼저 입력해주세요.");
        break;
      }

      Prompt.println("");
      Prompt.println("문서를 작성할 직원의 사번 또는 이름을 입력해주세요.");

      for (int i = 0; i < MemberHandler.turn; i++)
      {
        // 입력된 직원 보여주기
        System.out.printf("- %s(%d)\n", MemberHandler.members[i].name, MemberHandler.members[i].id);
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
        WriteMenuHandler.write = false; // 서류작성 while 종료
        App.company = false; // 시스템 while 종료
        break;
      } // if

      // 사용자가 입력한 직원의 존재여부 확인을 위한 변수
      int checkIndex = memberHandler.exist(valuse);

      if (-1 == checkIndex)
      {
        Prompt.println("없는 직원입니다. 다시 입력해주세요.");

        continue; // 현재 while 문을 반복하기 위해 아래 break를 건너뜀
      } else
      {
        if (menu.equals("2"))
        {
          // 휴가신청서 
          Prompt.println("");
          System.out.printf("%s직원의 휴가신청서를 입력을 시작합니다.\n", MemberHandler.members[checkIndex].name);

          while (true)
          {
            int userChoice = Prompt.inputInt("휴가종류(1. 연차 2. 반차 3. 병가 4. 경조)> ");

            switch (userChoice)
            {
              case 1 :
                papers[checkIndex].holiday = "연차";
                break;
              case 2 :
                papers[checkIndex].holiday = "반차";
                break;
              case 3 :
                papers[checkIndex].holiday = "병가";
                break;
              case 4 :
                papers[checkIndex].holiday = "경조";
                break;
              default :
                Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");

                Prompt.println("");
            } // switch

            if (1 <= userChoice && userChoice <= 4)
            {
              break;
            }
          }

          papers[checkIndex].startDate = Prompt.nextDate("휴가 시작 날짜(yyyy-MM-dd)> ");
          papers[checkIndex].endDate = Prompt.nextDate("휴가 마지막 날짜(yyyy-MM-dd)> ");
          papers[checkIndex].holidayReason = Prompt.inputString("사유> ");

          System.out.printf("%s직원의 휴가신청서가 작성되었습니다.\n", MemberHandler.members[checkIndex].name);

        } else 
        {
          // 사직서
          Prompt.println("");
          System.out.printf("%s직원의 사직서를 입력을 시작합니다.\n", MemberHandler.members[checkIndex].name);
          papers[checkIndex].outDate = Prompt.nextDate("퇴사날짜(yyyy-MM-dd)> ");
          papers[checkIndex].outReason = Prompt.inputString("퇴사사유> ");

          System.out.printf("%s직원의 사직서가 작성되었습니다.\n",MemberHandler. members[checkIndex].name);
        } // if

      } // if

      break;
    } // while
  }

  int exist() {
    for (int i = 0; i < MemberHandler.turn; i++)
    {
      if (PaperHandler.papers[i].holidayOk.equals("승인") || PaperHandler.papers[i].outOk.equals("승인"))
      {
        return i;
      } // if
    } // for

    return -1;
  }

  int noneApprovalExist() {
    for (int i = 0; i < MemberHandler.turn; i++)
    {
      if ((PaperHandler.papers[i].holidayReason != null && PaperHandler.papers[i].holidayOk.equals("미승인"))
          || (PaperHandler.papers[i].outReason != null && PaperHandler.papers[i].outOk.equals("미승인")))
      {
        return i;
      }
    }

    return -1;
  }

}
