package com.seong.pms.handler2;

import com.seong.pms.App2;
import com.seong.pms.domain.Vacation;
import com.seong.pms.domain.Member;
import com.seong.pms.domain.Resignation;
import com.seong.util.List;
import com.seong.util.Prompt;

public class PaperHandler {

  public List holidayList = new List();
  List outList = new List();
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
        App2.company = false; // 시스템 while 종료
        break;
      } // if

      Member m = memberHandler.exist(value);

      if (m == null)
      {
        Prompt.println("없는 직원입니다. 다시 입력해주세요.");
        continue; // 현재 while 문을 반복하기 위해 아래 break를 건너뜀
      }

      Vacation h = new Vacation();
      Resignation o = new Resignation();

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
              h.setHoliday("연차");
              break;
            case 2 :
              h.setHoliday("반차");
              break;
            case 3 :
              h.setHoliday("병가");
              break;
            case 4 :
              h.setHoliday("경조");
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

        h.setStartDate(Prompt.nextDate("휴가 시작 날짜(yyyy-MM-dd)> "));
        h.setEndDate(Prompt.nextDate("휴가 마지막 날짜(yyyy-MM-dd)> "));
        h.setHolidayReason(Prompt.inputString("사유> "));
        h.setHolidayOk("미승인");
        h.setName(m.getName());
        h.setId(m.getId());

        holidayList.add(h);

        Prompt.println(String.format("%s직원의 휴가신청서가 작성되었습니다.", m.getName()));

      } else 
      {
        // 사직서
        Prompt.println("");
        Prompt.println(String.format("%s직원의 사직서를 입력을 시작합니다.", m.getName()));
        o.setOutDate(Prompt.nextDate("퇴사날짜(yyyy-MM-dd)> "));
        o.setOutReason(Prompt.inputString("퇴사사유> "));
        o.setOutOk("미승인");
        o.setName(m.getName());
        o.setId(m.getId());

        outList.add(h);

        Prompt.println(String.format("%s직원의 사직서가 작성되었습니다.", m.getName()));
      } // if


      break;
    } // while
  }

  Vacation[] holidayTurn() {
    Object[] list = holidayList.toArray();

    Vacation[] holidays = new Vacation[holidayList.size()];

    for (int i = 0; i < holidayList.size(); i++)
    {
      holidays[i] = (Vacation) list[i];
    }

    for (int i = 0; i < holidayList.size(); i++)
    {
      Vacation temp;
      for (int j = 0; j < holidayList.size()-1; j++)
      {
        if (holidays[j].getId() > holidays[j+1].getId())
        {
          temp = holidays[j];
          holidays[j] = holidays[j+1];
          holidays[j+1] = temp;
        }
      }
    }

    return holidays;

  }

  Resignation[] outTurn() {
    Object[] list = outList.toArray();

    Resignation[] outs = new Resignation[outList.size()];

    for (int i = 0; i < outList.size(); i++)
    {
      outs[i] = (Resignation) list[i];
    }

    for (int i = 0; i < outList.size(); i++)
    {
      Resignation temp;
      for (int j = 0; j < outList.size()-1; j++)
      {
        if (outs[j].getId() > outs[j+1].getId())
        {
          temp = outs[j];
          outs[j] = outs[j+1];
          outs[j+1] = temp;
        }
      }
    }

    return outs;

  }

  //  HolidayPaper exist(int id) {
  //    Object[] list = holidayPaperList.toArray();
  //
  //    for (Object obj : list)
  //    {
  //      HolidayPaper p = (HolidayPaper) obj;
  //
  //      if (p.getId() == id)
  //      {
  //        return p;
  //      }
  //    }
  //
  //    return new HolidayPaper();
  //  }

  void printAll() {
    Vacation[] h = holidayTurn();
    Resignation[] o = outTurn();

    int size = holidayList.size();

    while (true)
    {
      if (holidayList.size() < outList.size())
      {
        size = outList.size();
      }

      Prompt.println(String.format("- %s(%d)직원의 정보", h.getName(), h.getId()));

      if (h.getHolidayOk() != null)
      {
        //        System.out.println("p.getHolidayReason() : " + p.getHolidayReason());
        Prompt.println(String.format("- %s(%d)직원의 휴가신청서", h.getName(), h.getId()));
      }

      size--;
    }

    //    for (HolidayPaper h : holidays)
    //    {
    //
    //      Prompt.println(String.format("- %s(%d)직원의 정보", h.getName(), h.getId()));
    //
    //      if (h.getHolidayOk() != null)
    //      {
    //        //        System.out.println("p.getHolidayReason() : " + p.getHolidayReason());
    //        Prompt.println(String.format("- %s(%d)직원의 휴가신청서", h.getName(), h.getId()));
    //      }
    //
    //    }
    //
    //    for (OutPaper o : outs)
    //    {
    //      if (o.getOutOk() != null)
    //      {
    //        //        System.out.println("p.getOutReason() : " + p.getOutReason());
    //        Prompt.println(String.format("- %s(%d)직원의 사직서", o.getName(), o.getId()));
    //      }
    //    }
  }

  void existPaper(String name) {
    Object[] list = holidayList.toArray();

    for (Object obj : list)
    {
      Vacation p = (Vacation) obj;

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
