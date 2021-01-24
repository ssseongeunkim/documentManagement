package com.seong.pms.handler;

import com.seong.pms.App;
import com.seong.util.Prompt;

public class ListHandler {

  static boolean paper = true;

  PaperHandler paperHandler = new PaperHandler();
  MemberHandler memberHandler = new MemberHandler();

  public void paperMenu() {
    while (paper)
    {
      Prompt.println("");
      int menu = Prompt.inputInt("=> 서류목록 메뉴입니다. <=\n1. 모든 문서 List\n2. 승인 된 문서 List\n3. 미승인 문서 List\n0. 뒤로가기\n99. 종료\n> ");

      if (menu == 1)
      {
        // 모든 문서 리스트
        allPaper();

      } else if (menu == 2)
      {
        // 승인 된 문서 리스트
        int check = paperHandler.exist();

        if (-1 < check)
        {
          approvalPaper();
        } else
        {
          Prompt.println("승인된 문서가 없습니다.");

          continue;
        }
      } else if (menu == 3)
      {
        // 미승인 문서 리스트
        int check = paperHandler.noneApprovalExist();

        if (-1 < check)
        {
          noneApprovalPaper();
        } else
        {
          Prompt.println("승인할 문서가 없습니다.");

          continue;
        }
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


  void allPaper() {
    while (true)
    {
      Prompt.println("");
      Prompt.println("= 모든 문서 List =");

      for (int i = 0; i < MemberHandler.turn; i++)
      {
        System.out.printf("- %s(%d)직원의 정보\n", MemberHandler.members[i].name, MemberHandler.members[i].id);

        if (PaperHandler.papers[i].holidayReason != null)
        {
          System.out.printf("- %s(%d)직원의 휴가신청서\n", MemberHandler.members[i].name, MemberHandler.members[i].id);
        } // if

        if (PaperHandler.papers[i].outReason != null)
        {
          System.out.printf("- %s(%d)직원의 사직서\n", MemberHandler.members[i].name, MemberHandler.members[i].id);
        } // if
      } // for

      String valuse = Prompt.inputString("확인할 문서의 작성자 사번 또는 이름을 입력하세요.\n0. 뒤로가기\n99. 종료\n> ");

      if (valuse.equals("0"))
      {
        Prompt.println("뒤로 갑니다.");
        break;
      } else if (valuse.equals("99"))
      {
        Prompt.println("시스템을 종료합니다.");
        paper = false; // 서류작성 while 종료
        App.company = false; // 시스템 while 종료
        break;
      }

      int holidayCheck = 0;
      int outCheck = 0;
      int index = memberHandler.exist(valuse);

      Prompt.println("");

      if (-1 < index)
      {
        System.out.printf("- %s(%d)직원의 정보\n", MemberHandler.members[index].name, MemberHandler.members[index].id);

        if (PaperHandler.papers[index].holidayReason != null)
        {
          System.out.printf("- %s(%d)직원의 휴가신청서\n", MemberHandler.members[index].name, MemberHandler.members[index].id);
          holidayCheck++;
        } // if

        if (PaperHandler.papers[index].outReason != null)
        {
          System.out.printf("- %s(%d)직원의 사직서\n", MemberHandler.members[index].name, MemberHandler.members[index].id);
          outCheck++;
        } // if
      } else
      {
        Prompt.println("없는 결과입니다. 다시 입력해주세요.");
        continue;
      }

      while (true)
      {
        if (!(holidayCheck > 0 && outCheck > 0))
        {
          String answer = Prompt.inputString(MemberHandler.members[index].name + "의 정보를 확인하시겠습니까? (y/N)> ");

          if (answer.equalsIgnoreCase("y"))
          {
            // 휴가신청서 확인 할 때
            member(index);
          } else
          {
            Prompt.println(MemberHandler.members[index].name + "의 정보를 확인하지 않습니다.");
          }
        }
        if (holidayCheck > 0 && outCheck > 0)
        {
          int userChoice = Prompt.inputInt("확인 할 문서를 선택하세요.(1. " + MemberHandler.members[index].name + "의 정보 2. 휴가신청서 3. 사직서)> ");

          if (userChoice == 1)
          {
            // 직원의 정보 확인 할 때
            member(index);
          } else if (userChoice == 2)
          {
            // 휴가신청서 확인 할 때
            holiday(index);
          } else if (userChoice == 3)
          {
            // 사직서 확인할 떄
            out(index);
          }
          else
          {
            Prompt.println("없는 메뉴입니다. 다시 입력해주세요.");
          }
        } else if (holidayCheck > 0)
        {
          int userChoice = Prompt.inputInt("확인 할 문서를 선택하세요.(1. " + MemberHandler.members[index].name + "의 정보 2. 휴가신청서)> ");

          if (userChoice == 1)
          {
            // 직원의 정보 확인 할 때
            member(index);
          } else if (userChoice == 2)
          {
            // 휴가신청서 확인 할 때
            holiday(index);
          } else
          {
            Prompt.println("없는 메뉴입니다. 다시 입력해주세요.");
          }
        } else if (outCheck > 0)
        {
          int userChoice = Prompt.inputInt("확인 할 문서를 선택하세요.(1. " + MemberHandler.members[index].name + "의 정보 2. 사직서)> ");

          if (userChoice == 1)
          {
            // 직원의 정보 확인 할 때
            member(index);
          } else if (userChoice == 2)
          {
            // 사직서 확인할 떄
            out(index);
          }
          else
          {
            Prompt.println("없는 메뉴입니다. 다시 입력해주세요.");
          }
        }

        break;

      } // while

      break;
    } // while

  }


  void approvalPaper() {
    while (true)
    {
      Prompt.println("");
      Prompt.println("= 승인 된 문서 List =");

      for (int i = 0; i < MemberHandler.turn; i++)
      {
        if (PaperHandler.papers[i].holidayApproval.equals("승인"))
        {
          System.out.printf("- %s(%d)직원의 휴가신청서\n", MemberHandler.members[i].name, MemberHandler.members[i].id);
        } // if

        if (PaperHandler.papers[i].outApproval.equals("승인"))
        {
          System.out.printf("- %s(%d)직원의 사직서\n", MemberHandler.members[i].name, MemberHandler.members[i].id);
        } // if
      } // for

      String valuse = Prompt.inputString("승인된 문서의 작성자 사번 또는 이름을 입력하세요.\n0. 뒤로가기\n99. 종료\n> ");

      if (valuse.equals("0"))
      {
        Prompt.println("뒤로 갑니다.");
        break;
      } else if (valuse.equals("99"))
      {
        Prompt.println("시스템을 종료합니다.");
        paper = false; // 서류작성 while 종료
        App.company = false; // 시스템 while 종료
        break;
      }

      int holidayCheck = 0;
      int outCheck = 0;
      int index = memberHandler.exist(valuse);

      Prompt.println("");

      if (-1 < index)
      {
        if (PaperHandler.papers[index].holidayApproval.equals("승인"))
        {
          Prompt.println(MemberHandler.members[index].name + "직원이 입력한 [휴가신청서]가 있습니다.");
          holidayCheck++;
        }

        if (PaperHandler.papers[index].outApproval.equals("승인"))
        {
          Prompt.println(MemberHandler.members[index].name + "직원이 입력한 [사직서]가 있습니다.");
          outCheck++;
        }
      } else
      {
        Prompt.println("없는 결과입니다. 다시 입력해주세요.");
        continue;
      }

      while(true)
      {
        if (holidayCheck > 0 && outCheck > 0)
        {
          // 휴가신청서와 사직서 모두 있는 경우
          int userChoice = Prompt.inputInt("확인 할 문서를 선택하세요.(1. 휴가신청서 2. 사직서)> ");

          if (userChoice == 1)
          {
            // 휴가신청서 확인 할 때
            holiday(index);
          } else if (userChoice == 2)
          {
            // 사직서 확인할 떄
            out(index);
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
            holiday(index);
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
            out(index);
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


  void noneApprovalPaper() {
    while(true)
    {
      Prompt.println("");
      Prompt.println("= 미승인 문서 List =");

      for (int i = 0; i < MemberHandler.turn; i++)
      {
        if (PaperHandler.papers[i].holidayReason != null && PaperHandler.papers[i].holidayApproval.equals("미승인"))
        {
          System.out.printf("- %s(%d)직원의 휴가신청서\n", MemberHandler.members[i].name, MemberHandler.members[i].id);
        } // if

        if (PaperHandler.papers[i].outReason != null &&  PaperHandler.papers[i].outApproval.equals("미승인"))
        {
          System.out.printf("- %s(%d)직원의 사직서\n", MemberHandler.members[i].name, MemberHandler.members[i].id);
        } // if
      } // for

      String valuse = Prompt.inputString("승인할 문서의 작성자 사번 또는 이름을 입력하세요.\n0. 뒤로가기\n99. 종료\n> ");

      if (valuse.equals("0"))
      {
        Prompt.println("뒤로 갑니다.");
        break;
      } else if (valuse.equals("99"))
      {
        Prompt.println("시스템을 종료합니다.");
        paper = false; // 서류작성 while 종료
        App.company = false; // 시스템 while 종료
        break;
      }

      int holidayCheck = 0;
      int outCheck = 0;
      int index = memberHandler.exist(valuse);

      Prompt.println("");

      if (-1 < index)
      {
        if (PaperHandler.papers[index].holidayReason == null && PaperHandler.papers[index].outReason == null)
        {
          Prompt.println("입력하신 직원은 문서를 작성하지 않았습니다. 다시 입력해주세요.");
          continue;
        }

        if (PaperHandler.papers[index].holidayReason != null && PaperHandler.papers[index].holidayApproval.equals("미승인"))
        {
          Prompt.println(MemberHandler.members[index].name + "직원이 입력한 [휴가신청서]가 있습니다.");
          holidayCheck++;
        }

        if (PaperHandler.papers[index].outReason != null && PaperHandler.papers[index].outApproval.equals("미승인"))
        {
          Prompt.println(MemberHandler.members[index].name + "직원이 입력한 [사직서]가 있습니다.");
          outCheck++;
        }
      } else
      {
        Prompt.println("없는 결과입니다. 다시 입력해주세요.");
        continue;
      }

      while(true)
      {
        if (holidayCheck > 0 && outCheck > 0)
        {
          // 휴가신청서와 사직서 모두 있는 경우
          int userChoice = Prompt.inputInt("확인 할 문서를 선택하세요.(1. 휴가신청서 2. 사직서)> ");

          if (userChoice == 1)
          {
            // 휴가신청서 확인 할 때
            holiday(index);
            String answer = Prompt.inputString("휴가신청을 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              PaperHandler.papers[index].holidayApproval = "승인";
            } else
            {
              Prompt.println("미승인하셨습니다.");
            }


          } else if (userChoice == 2)
          {
            // 사직서 확인할 떄
            out(index);
            String answer = Prompt.inputString("사직서를 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              PaperHandler.papers[index].outApproval = "승인";
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
            holiday(index);
            answer = Prompt.inputString("휴가신청을 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              PaperHandler.papers[index].holidayApproval = "승인";
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
            out(index);
            answer = Prompt.inputString("사직서를 승인하시겠습니까? (y/N)> ");

            if (answer.equalsIgnoreCase("y"))
            {
              Prompt.println("승인하셨습니다.");
              PaperHandler.papers[index].outApproval = "승인";
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

  void member(int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[" + MemberHandler.members[index].name + "의 정보]");
    System.out.printf("사번 : %d\n", MemberHandler.members[index].id);
    System.out.printf("서명 : %s\n", MemberHandler.members[index].name);
    System.out.printf("나이 : %s\n", MemberHandler.members[index].age);
    System.out.printf("부서 : %s\n", MemberHandler.members[index].department);
    System.out.printf("입사날짜 : %s\n", MemberHandler.members[index].joinDate);
    Prompt.println("--------------------------------------------------------");
  }

  void holiday(int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[휴가신청서]");
    System.out.printf("사번 : %d\n", MemberHandler.members[index].id);
    System.out.printf("서명 : %s\n", MemberHandler.members[index].name);
    System.out.printf("부서 : %s\n", MemberHandler.members[index].department);
    System.out.printf("휴가종류 : %s\n", PaperHandler.papers[index].holiday);
    System.out.printf("휴가날짜 : %s ~ %s\n", PaperHandler.papers[index].startDate, PaperHandler.papers[index].endDate);
    System.out.printf("상기 %s(본인)은 %s로 인하여 휴가신청서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", MemberHandler.members[index].name, PaperHandler.papers[index].holidayReason);
    System.out.printf("[승인여부] : %s\n", PaperHandler.papers[index].holidayApproval);
    Prompt.println("--------------------------------------------------------");
  }

  void out(int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[사직서]");
    System.out.printf("사번 : %d\n", MemberHandler.members[index].id);
    System.out.printf("서명 : %s\n", MemberHandler.members[index].name);
    System.out.printf("부서 : %s\n", MemberHandler.members[index].department);
    System.out.printf("입사날짜 : %s\n", MemberHandler.members[index].joinDate);
    System.out.printf("퇴사날짜 : %s\n", PaperHandler.papers[index].outDate);
    System.out.printf("상기 %s(본인)은 %s로 인하여 사직서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", MemberHandler.members[index].name, PaperHandler.papers[index].outReason);
    System.out.printf("[승인여부] : %s\n", PaperHandler.papers[index].outApproval);
    Prompt.println("--------------------------------------------------------");
  }
}
