package com.seong.pms;

import java.util.ArrayList;
import com.seong.pms.domain.Member;
import com.seong.pms.domain.Resignation;
import com.seong.pms.domain.Vacation;
import com.seong.pms.handler.MemberAddHandler;
import com.seong.pms.handler.MemberDeleteHandler;
import com.seong.pms.handler.MemberDetailHandler;
import com.seong.pms.handler.MemberListHandler;
import com.seong.pms.handler.MemberUpdateHandler;
import com.seong.pms.handler.MemberValidatorHandler;
import com.seong.pms.handler.ResignationAddHandler;
import com.seong.pms.handler.ResignationDeleteHandler;
import com.seong.pms.handler.ResignationDetailHandler;
import com.seong.pms.handler.ResignationListHandler;
import com.seong.pms.handler.ResignationUpdateHandler;
import com.seong.pms.handler.VacationAddHandler;
import com.seong.pms.handler.VacationDeleteHandler;
import com.seong.pms.handler.VacationDetailHandler;
import com.seong.pms.handler.VacationListHandler;
import com.seong.pms.handler.VacationUpdateHandler;
import com.seong.util.Prompt;

public class App {
  public static void main(String[] args) {

    //    MemberHandler memberHandler = new MemberHandler();
    ArrayList<Member> memberList = new ArrayList<>();
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);
    MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
    MemberListHandler memberListHandler = new MemberListHandler(memberList, memberValidatorHandler);
    MemberDetailHandler memberDetailHandler = new MemberDetailHandler(memberList, memberValidatorHandler);
    MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberList, memberValidatorHandler);
    MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(memberList, memberValidatorHandler);

    ArrayList<Vacation> vacationList = new ArrayList<>();
    VacationAddHandler vacationAddHandler = new VacationAddHandler(vacationList, memberValidatorHandler);
    VacationListHandler vacationListHandler = new VacationListHandler(vacationList);
    VacationDetailHandler vacationDetailHandler = new VacationDetailHandler(vacationList);
    VacationUpdateHandler vacationUpdateHandler = new VacationUpdateHandler(vacationList);
    VacationDeleteHandler vacationDeleteHandler = new VacationDeleteHandler(vacationList);

    ArrayList<Resignation> resignationList = new ArrayList<>();
    ResignationAddHandler resignationAddHandler = new ResignationAddHandler(resignationList, memberValidatorHandler);
    ResignationListHandler resignationListHandler = new ResignationListHandler(resignationList);
    ResignationDetailHandler resignationDetailHandler = new ResignationDetailHandler(resignationList);
    ResignationUpdateHandler resignationUpdateHandler = new ResignationUpdateHandler(resignationList);
    ResignationDeleteHandler resignationDeleteHandler = new ResignationDeleteHandler(resignationList);


    while (true) {
      int menu = Prompt.inputInt("[문서관리 시스템]\n 1. 문서입력\n 2. 문서조회\n 3. 문서수정\n 4. 문서삭제\n "
          + "99. 종료\n > ");

      try {
        switch (menu) {
          case 1:
            menu = Prompt.inputInt("\n[문서관리 시스템/문서입력]\n "
                + "1. 사원정보 입력\n 2. 휴가신청서 작성\n 3. 퇴사신청서 작성\n 90. 뒤로가기\n 99. 종료\n > ");

            switch (menu) {
              case 1:
                memberAddHandler.add();
                break;
              case 2:
                vacationAddHandler.add();
                break;
              case 3:
                resignationAddHandler.add();
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
            menu = Prompt.inputInt("\n[문서관리 시스템/문서조회]\n "
                + "1. 전체조회\n 2. 선택조회\n 90. 뒤로가기\n 99. 종료\n > ");

            switch (menu) {
              case 1:
                menu = Prompt.inputInt("\n[문서관리 시스템/문서조회/전체조회]\n "
                    + "1. 사원정보 조회\n 2. 휴가신청서 조회\n 3. 퇴사신청서 조회\n 90. 뒤로가기\n 99. 종료\n > ");

                switch (menu) {
                  case 1:
                    memberListHandler.list();
                    break;
                  case 2:
                    vacationListHandler.list();
                    break;
                  case 3:
                    resignationListHandler.list();
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
                    memberDetailHandler.detail();
                    break;
                  case 2:
                    vacationDetailHandler.detail();
                    break;
                  case 3:
                    resignationDetailHandler.detail();
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
          case 3:
            menu = Prompt.inputInt("\n[문서관리 시스템/문서수정]\n "
                + "1. 사원정보 수정\n 2. 휴가신청서 수정\n 3. 퇴사신청서 수정\n 90. 뒤로가기\n 99. 종료\n > ");

            switch (menu) {
              case 1:
                memberUpdateHandler.update();
                break;
              case 2:
                vacationUpdateHandler.update();
                break;
              case 3:
                resignationUpdateHandler.update();
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
          case 4:
            menu = Prompt.inputInt("\n[문서관리 시스템/문서삭제]\n "
                + "1. 사원정보 삭제\n 2. 휴가신청서 삭제\n 3. 퇴사신청서 삭제\n 90. 뒤로가기\n 99. 종료\n > ");

            switch (menu) {
              case 1:
                memberDeleteHandler.delete();
                break;
              case 2:
                vacationDeleteHandler.delete();
                break;
              case 3:
                resignationDeleteHandler.delete();
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
          case 99:
            System.out.println("\n시스템을 종료합니다.");
            return;
          default:
            System.out.println("\n없는 메뉴입니다.\n");
        }
      } catch (Exception e) {
        System.out.println("------------------------------------------");
        System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
            e.getClass().getName(), e.getMessage());
        System.out.println("------------------------------------------");
      }

      //      Prompt.close();
    }






  }
}
