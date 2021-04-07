package com.seong.pms;

import java.util.ArrayList;
import com.seong.pms.dao.MemberDao;
import com.seong.pms.dao.ResignationDao;
import com.seong.pms.dao.VacationDao;
import com.seong.pms.dao.mariadb.MemberDaoImpl;
import com.seong.pms.dao.mariadb.ResignationDaoImpl;
import com.seong.pms.dao.mariadb.VacationDaoImpl;
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

    ArrayList<Member> memberList = new ArrayList<>();
    ArrayList<Vacation> vacationList = new ArrayList<>();
    ArrayList<Resignation> resignationList = new ArrayList<>();

    //    HashMap<String,Object> commandMap = new HashMap<>();

    MemberDao memberDao = new MemberDaoImpl(memberList);
    ResignationDao resignationDao = new ResignationDaoImpl(resignationList);
    VacationDao vacationDao = new VacationDaoImpl(vacationList);

    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberDao);
    MemberAddHandler memberAddHandler = new MemberAddHandler(memberDao);
    MemberListHandler memberListHandler = new MemberListHandler(memberDao);
    MemberDetailHandler memberDetailHandler = new MemberDetailHandler(memberValidatorHandler, memberDao);
    MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberValidatorHandler, memberDao);
    MemberDeleteHandler memberDeleteHandler = new MemberDeleteHandler(memberDao);

    VacationAddHandler vacationAddHandler = new VacationAddHandler(memberValidatorHandler, vacationDao);
    VacationListHandler vacationListHandler = new VacationListHandler(vacationDao);
    VacationDetailHandler vacationDetailHandler = new VacationDetailHandler(vacationDao);
    VacationUpdateHandler vacationUpdateHandler = new VacationUpdateHandler(vacationDao);
    VacationDeleteHandler vacationDeleteHandler = new VacationDeleteHandler(vacationDao);

    ResignationAddHandler resignationAddHandler = new ResignationAddHandler(memberValidatorHandler, resignationDao);
    ResignationListHandler resignationListHandler = new ResignationListHandler(resignationDao);
    ResignationDetailHandler resignationDetailHandler = new ResignationDetailHandler(resignationDao);
    ResignationUpdateHandler resignationUpdateHandler = new ResignationUpdateHandler(resignationDao);
    ResignationDeleteHandler resignationDeleteHandler = new ResignationDeleteHandler(resignationDao);


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
                memberAddHandler.service();
                break;
              case 2:
                vacationAddHandler.service();
                break;
              case 3:
                resignationAddHandler.service();
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
                    memberListHandler.service();
                    break;
                  case 2:
                    vacationListHandler.service();
                    break;
                  case 3:
                    resignationListHandler.service();
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
                    memberDetailHandler.service();
                    break;
                  case 2:
                    vacationDetailHandler.service();
                    break;
                  case 3:
                    resignationDetailHandler.service();
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
                memberUpdateHandler.service();
                break;
              case 2:
                vacationUpdateHandler.service();
                break;
              case 3:
                resignationUpdateHandler.service();
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
                memberDeleteHandler.service();
                break;
              case 2:
                vacationDeleteHandler.service();
                break;
              case 3:
                resignationDeleteHandler.service();
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



//문서선택조회에서 사유는 빼기
//그러면 회원선택조회도 살펴보기
//업데이트, 딜리트 진행할때 정말 하시겠습니까 물어보기
