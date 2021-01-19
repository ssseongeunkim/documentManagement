package com.seong.pms;

import java.sql.Date;

public class WriteHandler {

  static final int SIZE = 5;

  static int employeeNo = 0; // 사번
  static boolean write = true;
  static int turn = 0; // 배열인덱스

  static int[] id = new int[SIZE]; // 직원번호
  static String[] name = new String[SIZE]; // 직원이름
  static int[] age = new int[SIZE]; // 직원나이
  static String[] department = new String[SIZE]; // 부서
  static Date[] joinDate = new Date[SIZE]; // 입사날짜

  static String[] holiday = new String[SIZE]; // 휴가종류
  static Date[] startDate = new Date[SIZE]; // 휴가시작날짜
  static Date[] endDate = new Date[SIZE]; // 휴가끝나는날짜
  static String[] holidayReason = new String[SIZE]; // 휴가사유
  static String[] holidayApproval = new String[SIZE]; // 휴가승인여부
  static Date[] outDate = new Date[SIZE]; // 퇴사날짜
  static String[] outReason = new String[SIZE]; // 퇴사사유
  static String[] outApproval = new String[SIZE]; // 퇴사승인여부

  static void approval() {
    for (int i = 0; i < SIZE; i++)
    {
      holidayApproval[i] = "미승인";
      outApproval[i] = "미승인";
    }
  }


  static void writeMenu() {
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

    id[turn] = ++employeeNo;
    name[turn] = Prompt.inputString("이름> ");
    age[turn] = Prompt.inputInt("나이> ");
    joinDate[turn] = Prompt.nextDate("입사날짜(yyyy-MM-dd)> ");

    while (true)
    {
      int userChoice = Prompt.inputInt("부서(1. 개발팀 2. 디자인팀 3. 마케팅팀)> ");

      switch(userChoice)
      {
        case 1 :
          department[turn] = "개발팀";
          break;
        case 2 :
          department[turn] = "디자인팀";
          break;
        case 3 :
          department[turn] = "마케팅팀";
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

    System.out.printf("%s직원의 정보가 입력되었습니다.\n", name[turn]);

    turn++;
  }


  static void addPaper(int menu) {
    while (true)
    {
      if (name[0] == null)
      {
        Prompt.println("저장된 직원이 없습니다. 직원의 정보를 먼저 입력해주세요.");
        break;
      }

      Prompt.println("");
      Prompt.println("문서를 작성할 직원의 사번 또는 이름을 입력해주세요.");

      for (int i = 0; i < turn; i++)
      {
        // 입력된 직원 보여주기
        System.out.printf("- %s(%d)\n", name[i], id[i]);
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
        if (valuse.equalsIgnoreCase(name[i]) || valuse.equals((i+1) + ""))
        {
          // 해당 직원이 있을 때
          if (menu == 2)
          {
            // 휴가신청서 
            Prompt.println("");
            System.out.printf("%s직원의 휴가신청서를 입력을 시작합니다.\n", name[i]);
            int userChoice = Prompt.inputInt("휴가종류(1. 연차 2. 반차 3. 병가 4. 경조)> ");

            switch (userChoice)
            {
              case 1 :
                holiday[i] = "연차";
                break;
              case 2 :
                holiday[i] = "반차";
                break;
              case 3 :
                holiday[i] = "병가";
                break;
              case 4 :
                holiday[i] = "경조";
                break;
              default :
                Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");
                Prompt.println("");
                i--;
                continue;
            } // switch

            startDate[i] = Prompt.nextDate("휴가 시작 날짜(yyyy-MM-dd)> ");
            endDate[i] = Prompt.nextDate("휴가 마지막 날짜(yyyy-MM-dd)> ");
            holidayReason[i] = Prompt.inputString("사유> ");

            System.out.printf("%s직원의 휴가신청서가 작성되었습니다.\n", name[i]);

          } else 
          {
            // 사직서
            Prompt.println("");
            System.out.printf("%s직원의 사직서를 입력을 시작합니다.\n", name[i]);
            outDate[i] = Prompt.nextDate("퇴사날짜(yyyy-MM-dd)> ");
            outReason[i] = Prompt.inputString("퇴사사유> ");

            System.out.printf("%s직원의 사직서가 작성되었습니다.\n", name[i]);
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
