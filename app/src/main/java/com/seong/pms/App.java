/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.seong.pms;

import com.seong.pms.handler.PapersHandler;
import com.seong.pms.handler.WriteHandler;
import com.seong.util.Prompt;

public class App {

  public static boolean company = true;

  public static void main(String[] args) {

    WriteHandler writeHandler = new WriteHandler();
    PapersHandler papersHandler = new PapersHandler();

    writeHandler.approval();

    while (company)
    {
      int menu = Prompt.inputInt("[문서관리 시스템]\n1. 서류작성\n2. 서류목록\n99. 종료\n> ");

      if (menu == 1)
      {
        // 서류작성메뉴 반복
        writeHandler.writeMenu();

      } else if (menu == 2) 
      {
        // 서류목록
        if (writeHandler.members[0].name == null)
        {
          Prompt.println("작성된 문서가 없습니다.");
          Prompt.println("");
          continue;
        }

        papersHandler.paperMenu(writeHandler);

      } else if (menu == 99)
      {
        // 종료
        Prompt.println("시스템을 종료합니다.");
        break;
      } else
      {
        Prompt.println("없는 메뉴 입니다. 다시 입력해주세요.");
      } // if

      Prompt.println("");
    } // while



    Prompt.close();
  } // main
}
