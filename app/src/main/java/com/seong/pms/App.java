package com.seong.pms;

import com.seong.pms.handler.MemberHandler;
import com.seong.pms.handler.ResignationHandler;
import com.seong.pms.handler.VacationHandler;
import com.seong.util.Prompt;

public class App {
  public static void main(String[] args) {

    MemberHandler memberHandler = new MemberHandler();
    VacationHandler vacationHandler = new VacationHandler(memberHandler);
    ResignationHandler resignationHandler = new ResignationHandler(memberHandler);

    loop : while (true) {
      int menu = Prompt.inputInt("[문서관리 시스템]\n 1. 사원정보 입력\n 2. 휴가신청서 작성\n 3. 퇴사신청서 작성\n "
          + "4. 문서조회\n 5. 문서수정\n 6. 문서삭제\n 99. 종료\n > ");

      switch (menu) {
        case 1:
          memberHandler.add();
          break;
        case 2:
          vacationHandler.add();
          break;
        case 3:
          resignationHandler.add();
          break;
        case 4:
          menu = Prompt.inputInt("\n[문서관리 시스템/문서조회]\n "
              + "1. 전체조회\n 2. 선택조회\n 90. 뒤로가기\n 99. 종료\n > ");

          switch (menu) {
            case 1:
              menu = Prompt.inputInt("\n[문서관리 시스템/문서조회/전체조회]\n "
                  + "1. 사원정보 조회\n 2. 휴가신청서 조회\n 3. 퇴사신청서 조회\n 90. 뒤로가기\n 99. 종료\n > ");

              switch (menu) {
                case 1:
                  memberHandler.list();
                  break;
                case 2:
                  vacationHandler.list();
                  break;
                case 3:
                  resignationHandler.list();
                  break;
                case 90:
                  System.out.println("\n뒤로갑니다.\n");
                  continue;
                case 99:
                  System.out.println("\n시스템을 종료합니다.");
                  return;
                default:
                  System.out.println("\n없는 메뉴입니다.\n");
              }

              break;
            case 2:
              menu = Prompt.inputInt("\n[문서관리 시스템/문서조회/선택조회]\n "
                  + "1. 사원정보 조회\n 2. 휴가신청서 조회\n 3. 퇴사신청서 조회\n 90. 뒤로가기\n 99. 종료\n > ");

              switch (menu) {
                case 1:
                  memberHandler.detail();
                  break;
                case 2:
                  //                  vacationHandler.list();
                  break;
                case 3:
                  //                  resignationHandler.list();
                  break;
                case 90:
                  System.out.println("\n뒤로갑니다.\n");
                  continue;
                case 99:
                  System.out.println("\n시스템을 종료합니다.");
                  return;
                default:
                  System.out.println("\n없는 메뉴입니다.\n");
              }

              break;
            case 90:
              System.out.println("\n뒤로갑니다.\n");
              continue;
            case 99:
              System.out.println("\n시스템을 종료합니다.");
              return;
            default:
              System.out.println("\n없는 메뉴입니다.\n");
          }

          break;
        case 5:
          menu = Prompt.inputInt("\n[문서관리 시스템/문서수정]\n "
              + "1. 사원정보 수정\n 2. 휴가신청서 수정\n 3. 퇴사신청서 수정\n 90. 뒤로가기\n 99. 종료\n > ");

          switch (menu) {
            case 1:
              memberHandler.update();
              break;
            case 2:
              vacationHandler.update();
              break;
            case 3:
              resignationHandler.update();
              break;
            case 90:
              System.out.println("\n뒤로갑니다.\n");
              continue;
            case 99:
              System.out.println("\n시스템을 종료합니다.");
              return;
            default:
              System.out.println("\n없는 메뉴입니다.\n");
          }

          break;
        case 6:

          break;
        case 99:
          System.out.println("\n시스템을 종료합니다.");
          return;
        default:
          System.out.println("\n없는 메뉴입니다.\n");
      }
    }

    //    Prompt.close();





  }
}
