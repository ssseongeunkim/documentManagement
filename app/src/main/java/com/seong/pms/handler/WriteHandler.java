package com.seong.pms.handler;

import java.sql.Date;
import com.seong.pms.App;
import com.seong.util.Prompt;

public class WriteHandler {

  public static class Member {
    public int id; // 직원번호
    public String name; // 직원이름
    public int age; // 직원나이
    public String department; // 부서
    public Date joinDate; // 입사날짜
  }

  public static class Paper {
    public String holiday; // 휴가종류
    public Date startDate; // 휴가시작날짜
    public Date endDate; // 휴가끝나는날짜
    public String holidayReason; // 휴가사유
    public String holidayApproval; // 휴가승인여부
    public Date outDate; // 퇴사날짜
    public String outReason; // 퇴사사유
    public String outApproval; // 퇴사승인여부
  }

  static final int SIZE = 5;

  static int employeeNo = 0; // 사번
  static boolean write = true;
  static int turn = 0; // 배열인덱스

  public static Member[] members = new Member[SIZE];
  public static Paper[] papers = new Paper[SIZE];





  public static void approval() {
    for (int i = 0; i < SIZE; i++)
    {
      members[i] = new Member();
      papers[i] = new Paper();
    }

    for (int i = 0; i < SIZE; i++)
    {
      papers[i].holidayApproval = "미승인";
      papers[i].outApproval = "미승인";
    }
  }


  public static void writeMenu() {
    while (write)
    {
      Prompt.println("");
      int menu = Prompt.inputInt("=> 서류작성 메뉴입니다. <=\n1. 직원정보\n2. 휴가신청서\n3. 사직서\n0. 뒤로가기\n99. 종료\n> ");

      if (menu == 1)
      {
        // 회사원 정보 입력
        if (turn == SIZE)
        {
          Prompt.println("입력란이 없어 더이상 추가입력이 불가능합니다.");
          continue;
        }

        addMember();

      } else if (menu == 2 || menu == 3)
      {
        // 휴가신청서, 사직서
        addPaper(menu);

      } else if (menu == 0)
      {
        // 뒤로가기
        Prompt.println("뒤로갑니다.");
        break;
      } else if (menu == 99)
      {
        // 종료
        Prompt.println("시스템을 종료합니다.");
        App.company = false; // 시스템 while 종료
        break;
      } else
      {
        Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");
      } // if
    } // while
  }


  static void addMember() {
    Prompt.println("");
    Prompt.println("직원정보 입력을 시작합니다.");

    Member m = new Member();

    m.id = ++employeeNo;
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


  static void addPaper(int menu) {
    while (true)
    {
      if (members[0].name == null)
      {
        Prompt.println("저장된 직원이 없습니다. 직원의 정보를 먼저 입력해주세요.");
        break;
      }

      Prompt.println("");
      Prompt.println("문서를 작성할 직원의 사번 또는 이름을 입력해주세요.");

      for (int i = 0; i < turn; i++)
      {
        // 입력된 직원 보여주기
        System.out.printf("- %s(%d)\n", members[i].name, members[i].id);
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
        write = false; // 서류작성 while 종료
        App.company = false; // 시스템 while 종료
        break;
      } // if

      // 사용자가 입력한 직원의 존재여부 확인을 위한 변수(있으면 1, 없으면 0)
      int check = 0;

      for (int i = 0; i < turn; i++)
      {
        // 해당 직원이 있는지 검사
        if (valuse.equalsIgnoreCase(members[i].name) || valuse.equals((i+1) + ""))
        {
          // 해당 직원이 있을 때
          if (menu == 2)
          {
            // 휴가신청서 
            Prompt.println("");
            System.out.printf("%s직원의 휴가신청서를 입력을 시작합니다.\n", members[i].name);
            int userChoice = Prompt.inputInt("휴가종류(1. 연차 2. 반차 3. 병가 4. 경조)> ");

            switch (userChoice)
            {
              case 1 :
                papers[i].holiday = "연차";
                break;
              case 2 :
                papers[i].holiday = "반차";
                break;
              case 3 :
                papers[i].holiday = "병가";
                break;
              case 4 :
                papers[i].holiday = "경조";
                break;
              default :
                Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");
                Prompt.println("");
                i--;
                continue;
            } // switch

            papers[i].startDate = Prompt.nextDate("휴가 시작 날짜(yyyy-MM-dd)> ");
            papers[i].endDate = Prompt.nextDate("휴가 마지막 날짜(yyyy-MM-dd)> ");
            papers[i].holidayReason = Prompt.inputString("사유> ");

            System.out.printf("%s직원의 휴가신청서가 작성되었습니다.\n", members[i].name);

          } else 
          {
            // 사직서
            Prompt.println("");
            System.out.printf("%s직원의 사직서를 입력을 시작합니다.\n", members[i].name);
            papers[i].outDate = Prompt.nextDate("퇴사날짜(yyyy-MM-dd)> ");
            papers[i].outReason = Prompt.inputString("퇴사사유> ");

            System.out.printf("%s직원의 사직서가 작성되었습니다.\n", members[i].name);
          } // if

          check = 1;

          break;
        } // if
      } // for

      if (check == 0)
      {
        Prompt.println("없는 직원입니다. 다시 입력해주세요.");
        continue; // 현재 while 문을 반복하기 위해 아래 break를 건너뜀
      } // if

      break;
    } // while
  }


}
