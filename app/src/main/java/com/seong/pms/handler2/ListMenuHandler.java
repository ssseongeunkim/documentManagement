package com.seong.pms.handler2;

import com.seong.pms.App2;
import com.seong.util.Prompt;

public class ListMenuHandler {

  static boolean paper = true;

  //  private MemberHandler memberHandler = new MemberHandler();
  //  private PaperHandler paperHandler = new PaperHandler(memberHandler);
  //  private SelectPaperHandler selectPaperHandler = new SelectPaperHandler(this.memberHandler, this.paperHandler);

  private MemberHandler memberHandler;
  private PaperHandler paperHandler;
  private SelectPaperHandler selectPaperHandler;

  public ListMenuHandler(MemberHandler memberHandler, PaperHandler paperHandler) {
    this.memberHandler = memberHandler;
    this.paperHandler = paperHandler;
    this.selectPaperHandler = new SelectPaperHandler(this.memberHandler, this.paperHandler);
  }

  //  public ListMenuHandler(MemberHandler memberHandler, PaperHandler paperHandler) {
  //    this.memberHandler = memberHandler;
  //    this.paperHandler = paperHandler;
  //    this.selectPaperHandler = new SelectPaperHandler(this.memberHandler, this.paperHandler);
  //  }

  public void paperMenu() {
    while (paper)
    {
      Prompt.println("");
      String menu = Prompt.inputString("[문서관리 시스템 / 서류목록]\n1. 모든 문서 List\n2. 승인 된 문서 List\n3. 미승인 문서 List\n0. 뒤로가기\n99. 종료\n> ");

      if (menu.equals("1"))
      {
        // 모든 문서 리스트
        selectPaperHandler.allPaper();

      } else if (menu.equals("2"))
      {
        // 승인 된 문서 리스트
        //        int check = paperHandler.exist();
        //
        //        if (-1 < check)
        //        {
        //          selectPaperHandler.approvalPaper();
        //        } else
        //        {
        //          Prompt.println("승인된 문서가 없습니다.");
        //
        //          continue;
        //        }
      } else if (menu.equals("3"))
      {
        // 미승인 문서 리스트
        //        int check = paperHandler.noneApprovalExist();
        //
        //        if (-1 < check)
        //        {
        //          selectPaperHandler.noneApprovalPaper();
        //        } else
        //        {
        //          Prompt.println("승인할 문서가 없습니다.");
        //
        //          continue;
        //        }
      } else if (menu.equals("0"))
      {
        Prompt.println("뒤로갑니다.");

        break;
      } else if (menu.equals("99"))
      {
        Prompt.println("시스템을 종료합니다.");

        App2.company = false;
        break;
      } else if (Prompt.inputCheck(menu))
      {
        Prompt.println("입력형식이 맞지 않습니다. 숫자만 입력해 주세요.");
      } else
      {
        Prompt.println("없는 메뉴 입니다. 다시 입력해주세요."); 
      }
    } // while
  }



}
