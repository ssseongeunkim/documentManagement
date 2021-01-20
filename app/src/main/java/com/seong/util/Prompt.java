package com.seong.util;

import java.sql.Date;
import java.util.Scanner;
import com.seong.pms.handler.WriteHandler;
import com.seong.pms.handler.PapersHandler;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  public static String inputString(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }

  public static int inputInt(String title) {
    return Integer.parseInt(inputString(title));
  }

  public static Date nextDate(String title) {
    return Date.valueOf(inputString(title));
  }

  public static void println(String title) {
    System.out.println(title);
  }

  public static String nextLine() {
    return scanner.nextLine();
  }

  public static void close() {
    scanner.close();
  }

  public static void holiday(int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[휴가신청서]");
    System.out.printf("사번 : %d\n", WriteHandler.members[index].id);
    System.out.printf("서명 : %s\n", WriteHandler.members[index].name);
    System.out.printf("부서 : %s\n", WriteHandler.members[index].department);
    System.out.printf("휴가종류 : %s\n", WriteHandler.papers[index].holiday);
    System.out.printf("휴가날짜 : %s ~ %s\n", WriteHandler.papers[index].startDate, WriteHandler.papers[index].endDate);
    System.out.printf("상기 %s(본인)은 %s로 인하여 휴가신청서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", WriteHandler.members[index].name, WriteHandler.papers[index].holidayReason);
    System.out.printf("[승인여부] : %s\n", WriteHandler.papers[index].holidayApproval);
    Prompt.println("--------------------------------------------------------");
  }

  public static void out(int index) {
    Prompt.println("--------------------------------------------------------");
    Prompt.println("[사직서]");
    System.out.printf("사번 : %d\n", WriteHandler.members[index].id);
    System.out.printf("서명 : %s\n", WriteHandler.members[index].name);
    System.out.printf("부서 : %s\n", WriteHandler.members[index].department);
    System.out.printf("입사날짜 : %s\n", WriteHandler.members[index].joinDate);
    System.out.printf("퇴사날짜 : %s\n", WriteHandler.papers[index].outDate);
    System.out.printf("상기 %s(본인)은 %s로 인하여 사직서를 제출하오니\n"
        + "허락하여 주시기 바랍니다.\n", WriteHandler.members[index].name, WriteHandler.papers[index].outReason);
    System.out.printf("[승인여부] : %s\n", WriteHandler.papers[index].outApproval);
    Prompt.println("--------------------------------------------------------");
  }
}
