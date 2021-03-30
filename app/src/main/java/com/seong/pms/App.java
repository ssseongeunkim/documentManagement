package com.seong.pms;

import com.seong.pms.handler.MemberHandler;
import com.seong.pms.handler.VacationHandler;
import com.seong.util.Prompt;

public class App {
  public static void main(String[] args) {

    MemberHandler memberHandler = new MemberHandler();
    VacationHandler vacationHandler = new VacationHandler(memberHandler);

    while (true) {
      int menu = Prompt.inputInt("[문서관리 시스템]\n 1. 사원정보 입력\n 2. 휴가신청서 작성\n 3. 퇴사신청서 작성\n 4. 문서목록\n 99. 종료\n >");

      switch (menu) {
        case 1:
          memberHandler.add();
          break;
        case 2:
          vacationHandler.add();
          break;
        case 3:
          System.out.println("퇴사신청서 메뉴입니다.");
          break;
        case 4:
          System.out.println("문서목록 메뉴입니다.");
          break;
        case 99:
          System.out.println("시스템을 종료합니다.");
          return;
        default:
          System.out.println("없는 메뉴입니다.");
      }
    }




  }
}
