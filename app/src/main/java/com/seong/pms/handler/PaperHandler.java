package com.seong.pms.handler;

import com.seong.pms.App;
import com.seong.pms.domain.Member;
import com.seong.pms.domain.Paper;
import com.seong.util.List;
import com.seong.util.Prompt;

public class PaperHandler {

  public List paperList = new List();
  private MemberHandler memberHandler;

  //  public PaperHandler() {
  //
  //  }

  public PaperHandler(MemberHandler memberHandler) {
    this.memberHandler = memberHandler;
  }

  void add(String menu) {
    while (true)
    {
      Prompt.println("");
      Prompt.println("문서를 작성할 직원의 사번 또는 이름을 입력해주세요.");

      memberHandler.memberList();
      String value = Prompt.inputString("0. 뒤로가기\n99. 종료\n> ");

      if (value.equals("0"))
      {
        // 뒤로가기
        Prompt.println("뒤로갑니다.");
        break;
      } else if (value.equals("99"))
      {
        // 종료
        Prompt.println("시스템을 종료합니다.");
        WriteMenuHandler.write = false; // 서류작성 while 종료
        App.company = false; // 시스템 while 종료
        break;
      } // if

      Member m = memberHandler.exist(value);

      if (m == null)
      {
        Prompt.println("없는 직원입니다. 다시 입력해주세요.");
        continue; // 현재 while 문을 반복하기 위해 아래 break를 건너뜀
      }

      Paper p = new Paper();

      if (menu.equals("2"))
      {
        // 휴가신청서 
        Prompt.println("");
        Prompt.println(String.format("%s직원의 휴가신청서를 입력을 시작합니다.", m.getName()));

        while (true)
        {
          int userChoice = Prompt.inputInt("휴가종류(1. 연차 2. 반차 3. 병가 4. 경조)> ");

          switch (userChoice)
          {
            case 1 :
              p.setHoliday("연차");
              break;
            case 2 :
              p.setHoliday("반차");
              break;
            case 3 :
              p.setHoliday("병가");
              break;
            case 4 :
              p.setHoliday("경조");
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

        p.setStartDate(Prompt.nextDate("휴가 시작 날짜(yyyy-MM-dd)> "));
        p.setEndDate(Prompt.nextDate("휴가 마지막 날짜(yyyy-MM-dd)> "));
        p.setHolidayReason(Prompt.inputString("사유> "));

        Prompt.println(String.format("%s직원의 휴가신청서가 작성되었습니다.", m.getName()));

      } else 
      {
        // 사직서
        Prompt.println("");
        Prompt.println(String.format("%s직원의 사직서를 입력을 시작합니다.", m.getName()));
        p.setOutDate(Prompt.nextDate("퇴사날짜(yyyy-MM-dd)> "));
        p.setOutReason(Prompt.inputString("퇴사사유> "));

        Prompt.println(String.format("%s직원의 사직서가 작성되었습니다.", m.getName()));
      } // if

      p.setName(m.getName());
      p.setId(m.getId());
      p.setHolidayReason("미승인");
      p.setOutReason("미승인");

      paperList.add(p);

      break;
    } // while
  }

  void printAll() {
    Object[] list = paperList.toArray();

    for (Object obj : list)
    {
      Paper p = (Paper) obj;

      Prompt.println(String.format("- %s(%d)직원의 정보", p.getName(), p.getId()));

      if (p.getHolidayOk() != null)
      {
        Prompt.println(String.format("- %s(%d)직원의 휴가신청서", p.getName(), p.getId()));
      }

      if (p.getOutOk() != null)
      {
        Prompt.println(String.format("- %s(%d)직원의 사직서", p.getName(), p.getId()));
      }
    }
  }

  void existPaper(String name) {
    Object[] list = paperList.toArray();

    for (Object obj : list)
    {
      Paper p = (Paper) obj;

      if (p.getName().equals(name))
      {
        Prompt.println(String.format("- %s(%d)직원의 정보\n", p.getName(), p.getId()));

        if (p.getHolidayOk() != null)
        {
          Prompt.println(String.format("- %s(%d)직원의 휴가신청서\n", p.getName(), p.getId()));
        }

        if (p.getOutOk() != null)
        {
          Prompt.println(String.format("- %s(%d)직원의 사직서\n", p.getName(), p.getId()));
        }

        return;
      }
    }
  }

  //  int exist() {
  //    for (int i = 0; i < MemberHandler.members.length; i++)
  //    {
  //      if (PaperHandler.papers[i].holidayOk.equals("승인") || PaperHandler.papers[i].outOk.equals("승인"))
  //      {
  //        return i;
  //      } // if
  //    } // for
  //
  //    return -1;
  //  }
  //
  //  int noneApprovalExist() {
  //    for (int i = 0; i < MemberHandler.members.length; i++)
  //    {
  //      if ((PaperHandler.papers[i].holidayReason != null && PaperHandler.papers[i].holidayOk.equals("미승인"))
  //          || (PaperHandler.papers[i].outReason != null && PaperHandler.papers[i].outOk.equals("미승인")))
  //      {
  //        return i;
  //      }
  //    }
  //
  //    return -1;
  //  }

}
