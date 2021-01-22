package com.seong.pms.handler;

import com.seong.pms.App;
import com.seong.util.Prompt;

public class PapersHandler {

  public void paperMenu(WriteHandler writeHandler) {
    while (true)
    {
      Prompt.println("");
      int menu = Prompt.inputInt("=> 서류목록 메뉴입니다. <=\n1. 모든 문서 List\n2. 승인 된 문서 List\n3. 미승인 문서 List\n0. 뒤로가기\n99. 종료\n> ");

      if (menu == 1)
      {
        // 모든 문서 리스트
        allPaper(writeHandler);

      } else if (menu == 2)
      {
        // 승인 된 문서 리스트
        int check = 0;

        // 승인된 문서가 있는지 검사하는 for
        for (int i = 0; i < WriteHandler.turn; i++)
        {
          if (writeHandler.papers[i].holidayApproval.equals("미승인") && writeHandler.papers[i].outApproval.equals("미승인"))
          {
            // 휴가신청서와 사직서의 승인여부가 '미승인'일때 check++
            check++;
          } // if
        } // for

        if (check == WriteHandler.turn)
        {
          // check의 값이 입력된 배열의 값과 동일 => 모두 미승인 문서 => 승인된 문서가 없음
          Prompt.println("승인된 문서가 없습니다.");
          continue;
        } // if

        approvalPaper(writeHandler);

      } else if (menu == 3)
      {
        // 미승인 문서 리스트
        int check = 0;

        // 미승인 문서가 있는지 검사하는 for
        for (int i = 0; i < WriteHandler.turn; i++)
        {
          if (writeHandler.papers[i].holidayReason == null && writeHandler.papers[i].holidayApproval.equals("미승인")
              && writeHandler.papers[i].outReason == null && writeHandler.papers[i].outApproval.equals("미승인"))
          {
            // 휴가사유와 퇴사사유가 입력되지 않았고, 휴가승인과 퇴사승인이 안됬을 경우
            // => 직원의 기본정보만 입력된 상태
            // => 승인받을 문서가 없는 상태
            check++;
          }

          if (writeHandler.papers[i].holidayReason != null && writeHandler.papers[i].holidayApproval.equals("승인")
              && writeHandler.papers[i].outReason != null && writeHandler.papers[i].outApproval.equals("승인"))
          {
            // 승인받아야할 문서(미승인문서)가 승인으로 바뀐 경우
            // => 휴가사유와 퇴사사유의 값이 입력되어있고 휴가승인과 퇴사승인이 된 상태
            // => 이미 승인받은 상태이기 때문에 다시 승인받을 필요가 없음
            check++;
          }
        } // for

        if (check >= WriteHandler.turn)
        {
          Prompt.println("승인할 문서가 없습니다.");
          continue;
        }

        noneApprovalPaper(writeHandler);

      } else if (menu == 0)
      {
        Prompt.println("뒤로갑니다.");
        break;
      } else if (menu == 99)
      {
        Prompt.println("시스템을 종료합니다.");
        App.company = false;
        break;
      } else
      {
        Prompt.println("없는 메뉴 입니다. 다시 입력해주세요."); 
      }
    } // while
  }


  void allPaper(WriteHandler writeHandler) {
    Prompt.println("");
    Prompt.println("= 모든 문서 List =");

    for (int i = 0; i < WriteHandler.turn; i++)
    {
      if (writeHandler.members[i].name != null)
      {
        System.out.printf("- %s(%d)직원의 정보\n", writeHandler.members[i].name, writeHandler.members[i].id);

        if (writeHandler.papers[i].holidayReason != null)
        {
          System.out.printf("- %s(%d)직원의 휴가신청서\n", writeHandler.members[i].name, writeHandler.members[i].id);
        } // if

        if (writeHandler.papers[i].outReason != null)
        {
          System.out.printf("- %s(%d)직원의 사직서\n", writeHandler.members[i].name, writeHandler.members[i].id);
        } // if
      } // if
    } // for
  }


  void approvalPaper(WriteHandler writeHandler) {
    while (true)
    {
      Prompt.println("");
      Prompt.println("= 승인 된 문서 List =");

      for (int i = 0; i < WriteHandler.turn; i++)
      {
        if (writeHandler.papers[i].holidayApproval.equals("승인"))
        {
          System.out.printf("- %s(%d)직원의 휴가신청서\n", writeHandler.members[i].name, writeHandler.members[i].id);
        } // if

        if (writeHandler.papers[i].outApproval.equals("승인"))
        {
          System.out.printf("- %s(%d)직원의 사직서\n", writeHandler.members[i].name, writeHandler.members[i].id);
        } // if
      } // for

      String valuse = Prompt.inputString("승인할 문서의 작성자 사번 또는 이름을 입력하세요.> ");

      int check = 0;
      int holidayCheck = 0;
      int outCheck = 0;
      int index = 0;

      Prompt.println("");

      for (int i = 0; i < WriteHandler.turn; i++)
      {
        // 해당 직원이 있는지 검사 + 각 직원이 입력한 문서의 종류(개수)를 파악
        if ((valuse.equalsIgnoreCase(writeHandler.members[i].name) && writeHandler.papers[i].holidayReason != null && writeHandler.papers[i].holidayApproval.equals("승인"))
            || (valuse.equals((i+1) + "") && writeHandler.papers[i].holidayReason != null && writeHandler.papers[i].holidayApproval.equals("승인")))
        {
          // 해당 직원이 있고, 승인된 휴가신청서가 있을 때
          Prompt.println(writeHandler.members[i].name + "직원이 입력한 [휴가신청서]가 있습니다.");

          check++;
          holidayCheck++;
          index = i; // 현재 조건에 맞는 직원의 배열 인덱스 값을 저장
        } // if

        if ((valuse.equalsIgnoreCase(writeHandler.members[i].name) && writeHandler.papers[i].outReason != null && writeHandler.papers[i].outApproval.equals("승인"))
            || (valuse.equals((i+1) + "") && writeHandler.papers[i].outReason != null && writeHandler.papers[i].outApproval.equals("승인")))
        {
          // 해당 직원이 있고, 승인된 사직서가 있을 때
          Prompt.println(writeHandler.members[i].name + "직원이 입력한 [사직서]가 있습니다.");

          check++;
          outCheck++;
          index = i; // 현재 조건에 맞는 직원의 배열 인덱스 값을 저장
        } // if
      } // for

      if (check == 0)
      {
        Prompt.println("없는 결과입니다. 다시 입력해주세요.");
        continue;
      } // if

      while(true)
      {
        if (holidayCheck > 0 && outCheck > 0)
        {
          // 휴가신청서와 사직서 모두 있는 경우
          int userChoice = Prompt.inputInt("확인 할 문서를 선택하세요.(1. 휴가신청서 2. 사직서)> ");
          //                  Prompt.nextLine();

          if (userChoice == 1)
          {
            // 휴가신청서 확인 할 때
            Prompt.holiday(writeHandler, index);
          } else if (userChoice == 2)
          {
            // 사직서 확인할 떄
            Prompt.out(writeHandler, index);
          } else
          {
            Prompt.println("없는 메뉴입니다. 다시 입력해주세요.");
          }

        } else if (holidayCheck > 0)
        {
          // 사직서는 없이 휴가신청서만 있을 경우
          String answer = Prompt.inputString("[휴가신청서]를 확인하시겠습니까? (y/N)> ");

          if (answer.equalsIgnoreCase("y"))
          {
            // 휴가신청서 확인 할 때
            Prompt.holiday(writeHandler, index);
          } else
          {
            Prompt.println("휴가신청서를 확인하지 않습니다.");
          }

        } else if (outCheck > 0)
        {
          // 휴가신청서는 없고 사직서만 있을 경우
          String answer = Prompt.inputString("[사직서]를 확인하시겠습니까? (y/N)> ");

          if (answer.equalsIgnoreCase("y"))
          {
            // 사직서 확인 할 때
            Prompt.out(writeHandler, index);
          } else
          {
            Prompt.println("사직서를 확인하지 않습니다.");
          }
        } // if

        break;
      } // while

      break;

    } // while
  }


  void noneApprovalPaper(WriteHandler writeHandler) {
    while(true)
    {
      Prompt.println("");
      Prompt.println("= 미승인 문서 List =");

      for (int i = 0; i < WriteHandler.turn; i++)
      {
        if (writeHandler.papers[i].holidayReason != null && writeHandler.papers[i].holidayApproval.equals("미승인"))
        {
          System.out.printf("- %s(%d)직원의 휴가신청서\n", writeHandler.members[i].name, writeHandler.members[i].id);
        } // if

        if (writeHandler.papers[i].outReason != null &&  writeHandler.papers[i].outApproval.equals("미승인"))
        {
          System.out.printf("- %s(%d)직원의 사직서\n", writeHandler.members[i].name, writeHandler.members[i].id);
        } // if
      } // for

      String valuse = Prompt.inputString("승인할 문서의 작성자 사번 또는 이름을 입력하세요.> ");

      int check = 0;
      int holidayCheck = 0;
      int outCheck = 0;
      int index = 0;

      Prompt.println("");

      for (int i = 0; i < WriteHandler.turn; i++)
      {
        // 해당 직원이 있는지 검사 + 각 직원이 입력한 문서의 종류(개수)를 파악
        if ((valuse.equalsIgnoreCase(writeHandler.members[i].name) && writeHandler.papers[i].holidayReason != null && writeHandler.papers[i].holidayApproval.equals("미승인"))
            || (valuse.equals((i+1) + "") && writeHandler.papers[i].holidayReason != null && writeHandler.papers[i].holidayApproval.equals("미승인")))
        {
          // 해당 직원이 있고, 미승인된 휴가신청서가 있을 때
          Prompt.println(writeHandler.members[i].name + "직원이 입력한 [휴가신청서]가 있습니다.");

          check++;
          holidayCheck++;
          index = i; // 현재 조건에 맞는 직원의 배열 인덱스 값을 저장
        } // if

        if ((valuse.equalsIgnoreCase(writeHandler.members[i].name) && writeHandler.papers[i].outReason != null && writeHandler.papers[i].outApproval.equals("미승인"))
            || (valuse.equals((i+1) + "") && writeHandler.papers[i].outReason != null && writeHandler.papers[i].outApproval.equals("미승인")))
        {
          // 해당 직원이 있고, 미승인된 사직서가 있을 때
          Prompt.println(writeHandler.members[i].name + "직원이 입력한 [사직서]가 있습니다.");

          check++;
          outCheck++;
          index = i; // 현재 조건에 맞는 직원의 배열 인덱스 값을 저장
        } // if
      } // for

      if (check == 0)
      {
        Prompt.println("없는 결과입니다. 다시 입력해주세요.");
        continue;
      } // if

      while(true)
      {
        if (holidayCheck > 0 && outCheck > 0)
        {
          // 휴가신청서와 사직서 모두 있는 경우
          int userChoice = Prompt.inputInt("확인 할 문서를 선택하세요.(1. 휴가신청서 2. 사직서)> ");
          //                  Prompt.nextLine();

          if (userChoice == 1)
          {
            // 휴가신청서 확인 할 때
            Prompt.holiday(writeHandler, index);
            String answer = Prompt.inputString("휴가신청을 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              writeHandler.papers[index].holidayApproval = "승인";
            } else
            {
              Prompt.println("미승인하셨습니다.");
            }


          } else if (userChoice == 2)
          {
            // 사직서 확인할 떄
            Prompt.out(writeHandler, index);
            String answer = Prompt.inputString("사직서를 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              writeHandler.papers[index].outApproval = "승인";
            } else
            {
              Prompt.println("미승인하셨습니다.");
            }


          } else
          {
            Prompt.println("없는 메뉴입니다. 다시 입력해주세요.");
          }

        } else if (holidayCheck > 0)
        {
          // 사직서는 없이 휴가신청서만 있을 경우
          String answer = Prompt.inputString("[휴가신청서]를 확인하시겠습니까? (y/N)> ");

          if (answer.equalsIgnoreCase("y"))
          {
            // 휴가신청서 확인 할 때
            Prompt.holiday(writeHandler, index);
            answer = Prompt.inputString("휴가신청을 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              writeHandler.papers[index].holidayApproval = "승인";
            } else
            {
              Prompt.println("미승인하셨습니다.");
            }
          } else
          {
            Prompt.println("휴가신청서를 확인하지 않습니다.");
          }

        } else if (outCheck > 0)
        {
          // 휴가신청서는 없고 사직서만 있을 경우
          String answer = Prompt.inputString("[사직서]를 확인하시겠습니까? (y/N)> ");

          if (answer.equalsIgnoreCase("y"))
          {
            // 사직서 확인 할 때
            Prompt.out(writeHandler, index);
            answer = Prompt.inputString("사직서를 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              writeHandler.papers[index].outApproval = "승인";
            } else
            {
              Prompt.println("미승인하셨습니다.");
            }
          } else
          {
            Prompt.println("사직서를 확인하지 않습니다.");
          }
        } // if

        break;
      } // while

      break;
    } // while
  }
}
